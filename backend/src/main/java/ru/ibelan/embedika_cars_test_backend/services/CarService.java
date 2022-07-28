package ru.ibelan.embedika_cars_test_backend.services;

import ru.ibelan.embedika_cars_test_backend.entities.CarBrand;
import ru.ibelan.embedika_cars_test_backend.graphql.io.CarInput;
import ru.ibelan.embedika_cars_test_backend.graphql.io.CarOutput;
import ru.ibelan.embedika_cars_test_backend.ref.CarColor;

import java.util.List;

public interface CarService {
	/**
	 * @return список автомобилей (пагинабельный)
	 */
	List<CarOutput> getCars(int page, int size);

	/**
	 * Получить данные автомобиля по идентификатору
	 *
	 * @param id идентификатор
	 * @return автомобиль
	 */
	CarOutput getCar(String id);

	/**
	 * Добавить автомобиль
	 *
	 * @return идентификатор созданной записи
	 */
	String addCar(CarInput carInput);

	/**
	 * @return количество автомобилей в базе
	 */
	long getCarsNumber();

	/**
	 * Удалить автомобиль
	 *
	 * @param id идентификатор
	 */
	void removeCar(String id) throws CarAppException;

	/**
	 * @return все бренды машин
	 */
	List<CarBrand> getCarBrands();

	/**
	 * @return конкретный бренд
	 */
	CarBrand getCarBrand(String name);

	/**
	 * @return все возможные цвета машин
	 */
	List<CarColor> getCarColors();

	class CarAppException extends RuntimeException {
		public CarAppException(String message) {
			super(message);
		}
	}
}
