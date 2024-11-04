package br.com.actionlabs.carboncalc.service.component;

import org.springframework.stereotype.Component;

import br.com.actionlabs.carboncalc.exception.ResourceNotFoundException;
import br.com.actionlabs.carboncalc.model.CarbonCalculation;
import br.com.actionlabs.carboncalc.repository.EnergyEmissionFactorRepository;
import br.com.actionlabs.carboncalc.service.interfaces.EmissionCalculator;

@Component
public class EnergyEmissionCalculator implements EmissionCalculator {
    private final EnergyEmissionFactorRepository repository;

    public EnergyEmissionCalculator(EnergyEmissionFactorRepository repository) {
        this.repository = repository;
    }

    @Override
    public double calculate(CarbonCalculation calc) {
        return repository.findByUf(calc.getUf())
            .map(factor -> calc.getEnergyConsumption() * factor.getFactor())
            .orElseThrow(() -> new ResourceNotFoundException("Fator de energia não encontrado"));
    }
}

