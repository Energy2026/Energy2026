package org.energy;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnergyReadingRepository extends MongoRepository<EnergyReading, String> {
}
