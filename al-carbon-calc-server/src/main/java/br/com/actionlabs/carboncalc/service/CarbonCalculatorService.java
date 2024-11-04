package br.com.actionlabs.carboncalc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.actionlabs.carboncalc.dto.CalculationInfoDTO;
import br.com.actionlabs.carboncalc.dto.CalculationResponseDTO;
import br.com.actionlabs.carboncalc.dto.CalculationResultDTO;
import br.com.actionlabs.carboncalc.dto.EmissionBreakdownDTO;
import br.com.actionlabs.carboncalc.dto.UserInfoDTO;
import br.com.actionlabs.carboncalc.exception.ResourceNotFoundException;
import br.com.actionlabs.carboncalc.model.CarbonCalculation;
import br.com.actionlabs.carboncalc.repository.CarbonCalculationRepository;
import br.com.actionlabs.carboncalc.service.interfaces.EmissionCalculator;

@Service
public class CarbonCalculatorService {
    private final Map<String, EmissionCalculator> calculators;
    private final CarbonCalculationRepository repository;

    public CarbonCalculatorService(
        List<EmissionCalculator> calculatorsList,
        CarbonCalculationRepository repository
    ) {
        this.calculators = calculatorsList.stream()
            .collect(Collectors.toMap(
                calc -> calc.getClass().getSimpleName(),
                calc -> calc
            ));
        this.repository = repository;
    }

    public CalculationResponseDTO startCalculation(UserInfoDTO userInfo) {
        CarbonCalculation calc = new CarbonCalculation();
        BeanUtils.copyProperties(userInfo, calc);
        calc.setCreatedAt(LocalDateTime.now());
        calc.setStatus("STARTED");

        calc = repository.save(calc);

        return new CalculationResponseDTO(
            calc.getId(),
            calc.getStatus(),
            calc.getCreatedAt()
        );
    }

    public CalculationResponseDTO updateCalculationInfo(String id, CalculationInfoDTO info) {
        CarbonCalculation calc = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cálculo não encontrado"));

        BeanUtils.copyProperties(info, calc);

        double energyEmission = calculators.get("EnergyEmissionCalculator").calculate(calc);
        double transportEmission = calculators.get("TransportEmissionCalculator").calculate(calc);
        double wasteEmission = calculators.get("WasteEmissionCalculator").calculate(calc);

        calc.setEnergyEmission(energyEmission);
        calc.setTransportEmission(transportEmission);
        calc.setWasteEmission(wasteEmission);
        calc.setTotalEmission(energyEmission + transportEmission + wasteEmission);
        calc.setStatus("COMPLETED");

        calc = repository.save(calc);

        return new CalculationResponseDTO(
            calc.getId(),
            calc.getStatus(),
            calc.getCreatedAt()
        );
    }

    public CalculationResultDTO getResult(String id) {
        CarbonCalculation calc = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cálculo não encontrado"));

        if (!"COMPLETED".equals(calc.getStatus())) {
            throw new IllegalStateException("Cálculo ainda não concluído");
        }

        return new CalculationResultDTO(
            calc.getTotalEmission(),
            new EmissionBreakdownDTO(
                calc.getEnergyEmission(),
                calc.getTransportEmission(),
                calc.getWasteEmission()
            )
        );
    }
}
