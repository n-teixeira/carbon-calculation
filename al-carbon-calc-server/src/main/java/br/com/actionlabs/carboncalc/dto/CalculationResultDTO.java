package br.com.actionlabs.carboncalc.dto;

public record CalculationResultDTO(
    Double totalEmission,
    EmissionBreakdownDTO breakdown
) {}
