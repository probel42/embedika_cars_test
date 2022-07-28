package ru.ibelan.embedika_cars_test_backend.services.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.ibelan.embedika_cars_test_backend.entities.Car;
import ru.ibelan.embedika_cars_test_backend.entities.CarBrand;
import ru.ibelan.embedika_cars_test_backend.entities.CarModel;
import ru.ibelan.embedika_cars_test_backend.graphql.io.CarInput;
import ru.ibelan.embedika_cars_test_backend.graphql.io.CarOutput;
import ru.ibelan.embedika_cars_test_backend.ref.CarColor;
import ru.ibelan.embedika_cars_test_backend.ref.CarColorRef;
import ru.ibelan.embedika_cars_test_backend.repos.CarBrandRepository;
import ru.ibelan.embedika_cars_test_backend.repos.CarColorRepository;
import ru.ibelan.embedika_cars_test_backend.repos.CarModelRepository;
import ru.ibelan.embedika_cars_test_backend.repos.CarRepository;
import ru.ibelan.embedika_cars_test_backend.services.CarService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
	private final CarRepository carRepository;
	private final CarBrandRepository carBrandRepository;
	private final CarModelRepository carModelRepository;
	private final CarColorRepository carColorRepository;

	public CarServiceImpl(
			CarRepository carRepository,
			CarBrandRepository carBrandRepository,
			CarModelRepository carModelRepository, CarColorRepository carColorRepository) {
		this.carRepository = carRepository;
		this.carBrandRepository = carBrandRepository;
		this.carModelRepository = carModelRepository;
		this.carColorRepository = carColorRepository;
	}

	@Override
	public List<CarOutput> getCars(int page, int size) {
		Map<Short, CarColor> colors = getCarColors().stream().collect(Collectors.toMap(CarColor::getCode, Function.identity()));
		return carRepository.findAll(PageRequest.of(page, size)).getContent().stream()
				.map(car -> createCarOutput(car, colors)).collect(Collectors.toList());
	}

	@Override
	public CarOutput getCar(String id) {
		Map<Short, CarColor> colors = getCarColors().stream().collect(Collectors.toMap(CarColor::getCode, Function.identity()));
		return carRepository.findById(UUID.fromString(id)).map(car -> createCarOutput(car, colors)).orElse(null);
	}

	@Override
	public String addCar(CarInput carInput) {
		if (carRepository.findCarByRegPlate(carInput.getRegPlate()) != null) {
			throw new CarAppException("Автомобиль с таким номером уже существует");
		}
		Car car = new Car();
		car.setRegPlate(carInput.getRegPlate());
		CarBrand brand = Optional.ofNullable(carBrandRepository.findCarBrandByName(carInput.getBrand()))
				.orElseGet(() -> createCarBrand(carInput.getBrand()));
		CarModel model = Optional.ofNullable(carModelRepository.findCarModelByBrandAndName(brand, carInput.getModel()))
				.orElseGet(() -> createCarModel(brand, carInput.getModel()));
		car.setModel(model);
		car.setColorCode(carInput.getColorCode());
		car.setYear(carInput.getYear());
		car.setCreateTime(LocalDateTime.now());
		carRepository.saveAndFlush(car);
		return Objects.requireNonNull(car.getId()).toString();
	}

	@Override
	public long getCarsNumber() {
		return carRepository.count();
	}

	@Override
	public void removeCar(String id) throws CarAppException {
		Car car = carRepository.findById(UUID.fromString(id))
				.orElseThrow(() -> new CarAppException(String.format("Не найден автомобиль с id \"%s\"", id)));
		carRepository.delete(car);
	}

	@Override
	public List<CarBrand> getCarBrands() {
		return carBrandRepository.findAll();
	}

	@Override
	public CarBrand getCarBrand(String name) {
		return carBrandRepository.findCarBrandByName(name);
	}

	@Override
	public List<CarColor> getCarColors() {
		return getCarColorsRef().map(CarColorRef::getCarColors).orElse(Collections.emptyList());
	}

	private CarBrand createCarBrand(String name) {
		CarBrand carBrand = new CarBrand();
		carBrand.setName(name);
		return carBrandRepository.saveAndFlush(carBrand);
	}

	private CarModel createCarModel(CarBrand brand, String name) {
		CarModel carModel = new CarModel();
		carModel.setName(name);
		carModel.setBrand(brand);
		return carModelRepository.saveAndFlush(carModel);
	}

	private Optional<CarColorRef> getCarColorsRef() {
		// в базе должен быть один документ в коллекции "car_colors"
		return carColorRepository.findAll().stream().findAny();
	}

	private CarOutput createCarOutput(Car car, Map<Short, CarColor> colors) {
		CarOutput carOutput = new CarOutput();
		carOutput.setId(car.getId());
		carOutput.setRegPlate(car.getRegPlate());
		carOutput.setBrand(car.getModel().getBrand().getName());
		carOutput.setModel(car.getModel().getName());
		carOutput.setColor(colors.get(car.getColorCode()));
		carOutput.setYear(car.getYear());
		carOutput.setCreateTime(car.getCreateTime());
		return carOutput;
	}
}
