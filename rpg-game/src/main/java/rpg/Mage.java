package rpg;

import java.util.List;

public class Mage extends Caster {
	// Constructing a mage
	public Mage(String name, Faction faction) {
		this.name = name;
		this.faction = faction;
		this.totalHp = Dice.roll("1d20") + this.cons + 10;
		this.totalMp = Dice.roll("2d20") + this.intel + 5;
		this.currentHp = this.totalHp;
		this.currentMp = this.totalMp;
		this.str = 0;
		this.dex = Dice.roll("1d12");
		this.intel = Dice.roll("1d20");
		this.cons = Dice.roll("1d12");
		SpellBook spellBook = new SpellBook();
		addSpell(1, spellBook.getFirstSpell(SpellType.DAMAGE));
		addSpell(2, spellBook.getSecondSpell(SpellType.DAMAGE));
		addSpell(3, spellBook.getThirdSpell(SpellType.DAMAGE));
	}

	// Performing mage turn
	@Override
	public void performTurn(List<Hero> targets) {
		// Searching for a target
		Hero currentTarget = null;
		for (Hero target: targets) {
			if (currentTarget == null && target.alive) {
				currentTarget = target;
			} else if (currentTarget != null &&  target.getCurrentHp() > currentTarget.getCurrentHp() && target.alive) {
				currentTarget = target;
			}
		}
		
		int damageDeal = 0;
		// Selecting a spell to use and calculating the damage
		if (this.spellBook.get(3).getCost() <= this.currentMp) {
			damageDeal = this.spellBook.get(3).calculateDamage(intel);
			currentTarget.takeDamage(damageDeal);
			this.currentMp -= this.spellBook.get(3).getCost();
			System.out.println("The " + this.faction + " mage, " + this.name + ", deal " + damageDeal + " damage to " + currentTarget.getName() + " with " + this.spellBook.get(3).getName());	
		} else if (this.spellBook.get(2).getCost() <= this.currentMp) {
			damageDeal = this.spellBook.get(2).calculateDamage(intel);
			currentTarget.takeDamage(damageDeal);
			this.currentMp -= this.spellBook.get(2).getCost();
			System.out.println("The " + this.faction + " mage, " + this.name + ", deal " + damageDeal + " damage to " + currentTarget.getName() + " with " + this.spellBook.get(2).getName());
		} else if (this.spellBook.get(1).getCost() <= this.currentMp) {
			damageDeal = this.spellBook.get(1).calculateDamage(intel);
			currentTarget.takeDamage(damageDeal);
			this.currentMp -= this.spellBook.get(1).getCost();
			System.out.println("The " + this.faction + " mage, " + this.name + ", deal " + damageDeal + " damage to " + currentTarget.getName() + " with " + this.spellBook.get(1).getName());
		} else {
			// Recovering MP if have no mana to cast a spell
			this.recoverMp();
			System.out.println(this.faction + " mage, " + this.name + ", have no mana to cast a spell. Recovering MP...");
		}
	}
	
}
