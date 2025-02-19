package rpg;

public class TurnOrder implements Comparable<TurnOrder> {
	protected int diceRoll;
	protected Hero hero;

	public TurnOrder(int diceRoll, Hero hero) {
		this.diceRoll = diceRoll;
		this.hero = hero;
	}

	public int getDiceRoll() {
		return diceRoll;
	}

	public Hero getHero() {
		return hero;
	}

	// Comparation module to turn order
	@Override
	public int compareTo(TurnOrder order) {
		if (this.diceRoll == order.diceRoll) {
			return 0;
		} else if (this.diceRoll > order.diceRoll) {
			return 1;
		} else {
			return -1;
		}		
	}

}