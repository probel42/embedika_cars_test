package ru.ibelan.embedika_cars_test_backend.ref;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Данные по цвету автомобиля из справочника
 */
@Document(collection = "car_colors_ref")
@Getter
@Setter
@RequiredArgsConstructor
public class CarColorRef {
	@Id
	private final String id;

	private List<CarColor> carColors = new ArrayList<>();
}
