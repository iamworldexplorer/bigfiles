package com.randomnumbers.app.randomgenerator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DoubleRandomGenerator implements RandomGenerator {
	
	@Override
	public List<Number> generate() {
		return new Random().doubles(RandomGeneratorConstants.RANDOM_GENERATOR_LIMIT)
				.boxed()
				.collect(Collectors.toList());
	}
}