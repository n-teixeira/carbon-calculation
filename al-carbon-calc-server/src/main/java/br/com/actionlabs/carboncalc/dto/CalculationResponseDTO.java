package br.com.actionlabs.carboncalc.dto;

import java.time.LocalDateTime;

public record CalculationResponseDTO(
    String id,
    String status,
    LocalDateTime createdAt
) {}