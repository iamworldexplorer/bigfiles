package com.randomnumbers.app.randomgenerator;

import java.util.List;

public class RandomGeneratorConfig {
	
	public List<Number> generateRandomNumbers(String numbersType) {
		RandomGenerator randomNumberGenerator = getRandomGenerator(numbersType);
		return randomNumberGenerator.generate();
	}
	
	public RandomGenerator getRandomGenerator(String randomGeneratorType) {
		if(randomGeneratorType != null) {
			randomGeneratorType = randomGeneratorType.toUpperCase();
		} else {
			return new IntegerRandomGenerator();
		}
		if(RandomGeneratorConstants.RANDOM_GENERATOR_TYPE_DOUBLE.equals(randomGeneratorType)) {
			return new DoubleRandomGenerator(); 
		} else if(RandomGeneratorConstants.RANDOM_GENERATOR_TYPE_LONG.equals(randomGeneratorType)) {
			return new LongRandomGenerator();
		} else {
			return new IntegerRandomGenerator();
		}
	}
}
