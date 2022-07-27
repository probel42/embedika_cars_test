package ru.ibelan.embedika_cars_test_backend.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "car_brand")
@Getter
@Setter
public class CarBrand extends AbstractPersistable<UUID> {
	/**
	 * Наименование
	 */
	@Column(name = "name", nullable = false)
	private String name;
}
