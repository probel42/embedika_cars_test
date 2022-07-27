package ru.ibelan.embedika_cars_test_backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ibelan.embedika_cars_test_backend.entities.CarBrand;

import java.util.UUID;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, UUID> {
	CarBrand findCarBrandByName(String name);
}
