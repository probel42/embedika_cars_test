package ru.ibelan.embedika_cars_test_backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ibelan.embedika_cars_test_backend.entities.CarBrand;
import ru.ibelan.embedika_cars_test_backend.entities.CarModel;

import java.util.UUID;

public interface CarModelRepository extends JpaRepository<CarModel, UUID> {
	CarModel findCarModelByBrandAndName(CarBrand brand, String name);
}
