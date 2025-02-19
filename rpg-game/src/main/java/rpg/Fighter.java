package rpg;

import java.util.List;

public class Fighter extends Hero {
	protected Weapon weapon;
	
	// Constructing a fighter
	public Fighter(String name, Faction faction) {
		this.name = name;
		this.faction = faction;
		this.totalHp = Dice.roll("3d20") + this.cons + 20;
		this.totalMp = 0;
		this.currentHp = this.totalHp;
		this.currentMp = this.totalMp;
		this.str = Dice.roll("1d12");
		this.dex = Dice.roll("1d8");
		this.intel = 0;
		this.cons = Dice.roll("1d20");
		WeaponRack weaponRack = new WeaponRack();
		this.weapon = weaponRack.selectWeapon(WeaponType.BRUISER);
	}

	// Perform fighter turn
	@Override
	public void performTurn(List<Hero> targets) {
		// Searching for a target
		Hero currentTarget = null;
		for (Hero target: targets) {
			if (currentTarget == null && target.alive) {
				currentTarget = target;
			} else if (currentTarget != null &&  target.getCurrentHp() < currentTarget.getCurrentHp() && target.alive) {
				currentTarget = target;
			}	
		}
		// Calculating the damage and dealing it
		int damageDeal = this.weapon.calculateDamage(str);
		currentTarget.takeDamage(damageDeal);
		
		// Printing the result
		System.out.println("The " + this.faction + " fighter, " + this.name + ", deal " + damageDeal + " damage to " + currentTarget.getName() + " with " + this.weapon.getName());
	}
}
