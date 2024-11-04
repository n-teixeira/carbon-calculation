package br.com.actionlabs.carboncalc.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document("carbonCalculation")
public class CarbonCalculation {
    @Id
    private String id;
    private String name;
    private String email;
    private String uf;
    private String phoneNumber;
    private Double energyConsumption;
    private Double transportationDistance;
    private String transportationType;
    private Double solidWasteProduction;
    private Double recyclePercentage;
    private Double totalEmission;
    private Double energyEmission;
    private Double transportEmission;
    private Double wasteEmission;
    private String status;
    private LocalDateTime createdAt;

}