package br.com.actionlabs.carboncalc.service.interfaces;

import br.com.actionlabs.carboncalc.model.CarbonCalculation;

public interface EmissionCalculator {
    double calculate(CarbonCalculation calculation);
}
