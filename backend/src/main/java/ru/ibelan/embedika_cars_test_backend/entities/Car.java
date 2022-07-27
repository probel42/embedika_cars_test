package ru.ibelan.embedika_cars_test_backend.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.UUID;

/**
 * Автомобиль
 */
@Entity
@Table(name = "car")
@Getter
@Setter
public class Car extends AbstractPersistable<UUID> {
	/**
	 * Номер
	 */
	@Column(name = "reg_plate", nullable = false)
	private String regPlate;

	/**
	 * Модель
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "car_model_id", nullable = false)
	private CarModel model;

	/**
	 * Код цвета из справочника
	 */
	@Column(name = "color_code", nullable = false)
	private Short colorCode;

	/**
	 * Год выпуска
	 */
	@Column(name = "year", length = 4, nullable = false)
	private Short year;
}
