package ru.ibelan.embedika_cars_test_backend.graphql.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarInput {
	private String regPlate;
	private String model;
	private String brand;
	private Short colorCode;
	private Short year;
}
