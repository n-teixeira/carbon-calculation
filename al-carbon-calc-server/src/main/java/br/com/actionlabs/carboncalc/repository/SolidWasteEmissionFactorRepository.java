package br.com.actionlabs.carboncalc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.actionlabs.carboncalc.model.SolidWasteEmissionFactor;

@Repository
public interface SolidWasteEmissionFactorRepository
    extends MongoRepository<SolidWasteEmissionFactor, String> {
        Optional<SolidWasteEmissionFactor> findByUf(String uf);
    }
