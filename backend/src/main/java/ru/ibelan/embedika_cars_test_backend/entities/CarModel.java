package ru.ibelan.embedika_cars_test_backend.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "car_model")
@Getter
@Setter
public class CarModel extends AbstractPersistable<UUID> {
	/**
	 * Бренд
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id", nullable = false)
	private CarBrand brand;

	/**
	 * Наименование
	 */
	@Column(name = "name", nullable = false)
	private String name;
}
