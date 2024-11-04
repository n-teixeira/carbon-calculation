package br.com.actionlabs.carboncalc.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.actionlabs.carboncalc.enums.TransportationType;
import br.com.actionlabs.carboncalc.model.TransportationEmissionFactor;

@Repository
public interface TransportationEmissionFactorRepository
    extends MongoRepository<TransportationEmissionFactor, TransportationType> {
        Optional<TransportationEmissionFactor> findByType(TransportationType valueOf);
    }
