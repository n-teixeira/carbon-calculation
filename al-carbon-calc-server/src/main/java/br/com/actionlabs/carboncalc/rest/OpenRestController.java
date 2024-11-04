package br.com.actionlabs.carboncalc.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.actionlabs.carboncalc.dto.CalculationInfoDTO;
import br.com.actionlabs.carboncalc.dto.CalculationResponseDTO;
import br.com.actionlabs.carboncalc.dto.CalculationResultDTO;
import br.com.actionlabs.carboncalc.dto.UserInfoDTO;
import br.com.actionlabs.carboncalc.service.CarbonCalculatorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/open")
@Validated
public class OpenRestController {

    private final CarbonCalculatorService service;

    public OpenRestController(CarbonCalculatorService service) {
        this.service = service;
    }

    @PostMapping("/start-calc")
    public ResponseEntity<CalculationResponseDTO> startCalculation(
            @Valid @RequestBody UserInfoDTO userInfo) {
        CalculationResponseDTO response = service.startCalculation(userInfo);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    @PutMapping("/info")
    public ResponseEntity<CalculationResponseDTO> updateCalculationInfo(
            @RequestParam String id,
            @Valid @RequestBody CalculationInfoDTO info) {
        CalculationResponseDTO response = service.updateCalculationInfo(id, info);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<CalculationResultDTO> getResult(@PathVariable String id) {
        CalculationResultDTO result = service.getResult(id);
        return ResponseEntity.ok(result);
    }
}
