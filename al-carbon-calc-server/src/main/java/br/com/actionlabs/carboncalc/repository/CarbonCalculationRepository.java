package br.com.actionlabs.carboncalc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.actionlabs.carboncalc.model.CarbonCalculation;

@Repository
public interface CarbonCalculationRepository extends MongoRepository<CarbonCalculation, String> {
}
