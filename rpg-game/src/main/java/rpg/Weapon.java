package rpg;

public class Weapon implements Damage {
	protected String name;
	protected String damageDice;
	protected WeaponType weaponType;

	public Weapon() {

	}

	// Constructing a weapon
	public Weapon(String name, String damageDice, WeaponType weaponType) {
		this.name = name;
		this.damageDice = damageDice;
		this.weaponType = weaponType;
	}

	public String getName() {
		return name;
	}
	
	public WeaponType getType() {
		return weaponType;
	}

	// Calculating weapon damage
	public int calculateDamage(int modifier) {
		int damage = Dice.roll(damageDice);
		damage += damage * (modifier / 100);
		return damage;
	}
		
}