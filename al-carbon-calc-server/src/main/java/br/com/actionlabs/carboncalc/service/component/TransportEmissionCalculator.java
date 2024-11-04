package br.com.actionlabs.carboncalc.service.component;

import org.springframework.stereotype.Component;

import br.com.actionlabs.carboncalc.enums.TransportationType;
import br.com.actionlabs.carboncalc.exception.ResourceNotFoundException;
import br.com.actionlabs.carboncalc.model.CarbonCalculation;
import br.com.actionlabs.carboncalc.repository.TransportationEmissionFactorRepository;
import br.com.actionlabs.carboncalc.service.interfaces.EmissionCalculator;

@Component
public class TransportEmissionCalculator implements EmissionCalculator {
    private final TransportationEmissionFactorRepository repository;

    public TransportEmissionCalculator(TransportationEmissionFactorRepository repository) {
        this.repository = repository;
    }

    @Override
    public double calculate(CarbonCalculation calc) {
        return repository.findByType(TransportationType.valueOf(calc.getTransportationType()))
            .map(factor -> calc.getTransportationDistance() * factor.getFactor())
            .orElseThrow(() -> new ResourceNotFoundException("Fator de transporte não encontrado"));
    }
}