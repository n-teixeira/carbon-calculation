package br.com.actionlabs.carboncalc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserInfoDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,
        @NotBlank(message = "O número de telefone é obrigatório")
        @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Número de telefone inválido")
        String phoneNumber,
        @NotBlank(message = "A UF é obrigatória")
        @Pattern(regexp = "^[A-Z]{2}$", message = "Formato de UF inválido")
        String uf) {

}