package ru.ibelan.embedika_cars_test_backend.ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarColor {
	private final Short code;
	private final String hex;
	private final String name;
	private final String description;
	private final boolean metallic;
}
