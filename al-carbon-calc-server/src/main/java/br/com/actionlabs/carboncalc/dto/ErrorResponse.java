package br.com.actionlabs.carboncalc.dto;

import java.util.List;

public record ErrorResponse(String message, List<String> details) {}