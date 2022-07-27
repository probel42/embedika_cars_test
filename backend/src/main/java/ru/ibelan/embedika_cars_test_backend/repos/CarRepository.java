package ru.ibelan.embedika_cars_test_backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ibelan.embedika_cars_test_backend.entities.Car;

import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
	Car findCarByRegPlate(String code);
}
