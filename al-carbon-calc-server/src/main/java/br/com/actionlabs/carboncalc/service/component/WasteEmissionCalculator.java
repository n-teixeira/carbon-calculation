package br.com.actionlabs.carboncalc.service.component;

import org.springframework.stereotype.Component;

import br.com.actionlabs.carboncalc.exception.ResourceNotFoundException;
import br.com.actionlabs.carboncalc.model.CarbonCalculation;
import br.com.actionlabs.carboncalc.model.SolidWasteEmissionFactor;
import br.com.actionlabs.carboncalc.repository.SolidWasteEmissionFactorRepository;
import br.com.actionlabs.carboncalc.service.interfaces.EmissionCalculator;

@Component
public class WasteEmissionCalculator implements EmissionCalculator {
    private final SolidWasteEmissionFactorRepository repository;

    public WasteEmissionCalculator(SolidWasteEmissionFactorRepository repository) {
        this.repository = repository;
    }

    @Override
    public double calculate(CarbonCalculation calc) {
        SolidWasteEmissionFactor factor = repository.findByUf(calc.getUf())
            .orElseThrow(() -> new ResourceNotFoundException("Fator de resíduos não encontrado"));

        double recyclableEmission = calc.getSolidWasteProduction() * calc.getRecyclePercentage() * factor.getRecyclableFactor();
        double nonRecyclableEmission = calc.getSolidWasteProduction() * (1 - calc.getRecyclePercentage()) * factor.getNonRecyclableFactor();

        return recyclableEmission + nonRecyclableEmission;
    }
}