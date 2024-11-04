package br.com.actionlabs.carboncalc.dto;

public record EmissionBreakdownDTO(
    Double energyEmission,
    Double transportEmission,
    Double wasteEmission
) {}
