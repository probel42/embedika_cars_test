package ru.ibelan.embedika_cars_test_backend.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.ibelan.embedika_cars_test_backend.entities.CarBrand;
import ru.ibelan.embedika_cars_test_backend.graphql.io.CarInput;
import ru.ibelan.embedika_cars_test_backend.graphql.io.CarOutput;
import ru.ibelan.embedika_cars_test_backend.ref.CarColor;
import ru.ibelan.embedika_cars_test_backend.services.CarService;

import java.util.List;

@Controller
public class CarController {
	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@QueryMapping
	public CarOutput car(@Argument String id) {
		return carService.getCar(id);
	}

	@QueryMapping
	public List<CarOutput> cars(@Argument int page, @Argument int size) {
		return carService.getCars(page, size);
	}

	@QueryMapping
	public long carsNumber() {
		return carService.getCarsNumber();
	}

	@QueryMapping
	public List<CarBrand> carBrands() {
		return carService.getCarBrands();
	}

	@QueryMapping
	public CarBrand carBrand(@Argument String name) {
		return carService.getCarBrand(name);
	}

	@QueryMapping
	public List<CarColor> carColors() {
		return carService.getCarColors();
	}

	@MutationMapping
	public String addCar(@Argument CarInput car) {
		return carService.addCar(car);
	}

	@MutationMapping
	public void removeCar(@Argument String id) throws CarService.CarAppException {
		carService.removeCar(id);
	}
}
