package br.com.actionlabs.carboncalc.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CalculationInfoDTO(
        @NotNull(message = "O consumo de energia é obrigatório")
        @Positive(message = "O consumo de energia deve ser positivo")
        Double energyConsumption,
        
        @NotNull(message = "A distância de transporte é obrigatória")
        @PositiveOrZero(message = "A distância de transporte deve ser não negativa")
        Double transportationDistance,
        
        @NotBlank(message = "O tipo de transporte é obrigatório")
        @Pattern(regexp = "CAR|MOTORCYCLE|PUBLIC_TRANSPORT|BICYCLE", 
                 message = "O tipo de transporte deve ser um dos seguintes: CAR, MOTORCYCLE, PUBLIC_TRANSPORT, BICYCLE")
        String transportationType,
        
        @NotNull(message = "A produção de resíduos sólidos é obrigatória")
        @PositiveOrZero(message = "A produção de resíduos sólidos deve ser não negativa")
        Double solidWasteProduction,
        
        @NotNull(message = "A porcentagem de reciclagem é obrigatória")
        @Min(value = 0, message = "A porcentagem de reciclagem deve estar entre 0 e 1")
        @Max(value = 1, message = "A porcentagem de reciclagem deve estar entre 0 e 1")
        Double recyclePercentage) {
}
