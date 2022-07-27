package ru.ibelan.embedika_cars_test_backend.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.ibelan.embedika_cars_test_backend.ref.CarColorRef;

@Repository
public interface CarColorRepository extends MongoRepository<CarColorRef, String> {
}
