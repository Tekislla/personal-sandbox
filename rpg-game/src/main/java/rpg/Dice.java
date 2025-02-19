package rpg;

import java.util.Random;

public class Dice {
	public static int roll(String dice) {
		String roll = dice;

		String[] rollParts = roll.split("d");
		String dices = rollParts[0];
		String diceSides = rollParts[1];

		int maxDices = Integer.parseInt(dices);
		int maxNumber = Integer.parseInt(diceSides);
		int diceResult = 0;

		for (int i = 0; i < maxDices; i++) {
			Random random = new Random();
			int diceRoll = random.nextInt(maxNumber) + 1;
			diceResult += diceRoll;
		}

		return diceResult;

	}
}
