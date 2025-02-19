package rpg;

public class Spell {
	public String name;
	public String damageDice;
	public int cost;
	public SpellType type;

	// Constructing a spell
	public Spell(String name, String damageDice, int cost, SpellType type) {
		this.name = name;
		this.damageDice = damageDice;
		this.cost = cost;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getDamageDice() {
		return damageDice;
	}

	public int getCost() {
		return cost;
	}

	public SpellType getType() {
		return type;
	}

	// Calculating spell damage
	public int calculateDamage(int modifier) {
		int damage = Dice.roll(damageDice);
		damage += damage * (modifier / 100);
		return damage;
	}
}
