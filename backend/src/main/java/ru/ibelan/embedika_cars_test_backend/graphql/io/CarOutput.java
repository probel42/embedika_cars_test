package ru.ibelan.embedika_cars_test_backend.graphql.io;

import lombok.Getter;
import lombok.Setter;
import ru.ibelan.embedika_cars_test_backend.ref.CarColor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CarOutput {
	private UUID id;
	private String regPlate;
	private String model;
	private String brand;
	private CarColor color;
	private Short year;
	private LocalDateTime createTime;
}
