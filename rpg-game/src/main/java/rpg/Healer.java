package rpg;

import java.util.List;

public class Healer extends Caster {
	protected boolean isProtected = false;
	protected int protectDelay = 2;
	protected int protectCooldown = 0;
	
	// Constructing a healer
	public Healer(String name, Faction faction) {
		this.name = name;
		this.faction = faction;
		this.totalHp = Dice.roll("2d20") + this.cons + 15;
		this.totalMp = Dice.roll("1d20") + this.intel + 5;
		this.str = 0;
		this.dex = Dice.roll("1d8");
		this.intel = Dice.roll("1d20");
		this.cons = Dice.roll("1d10");
		this.isProtected = false;
		this.currentHp = this.totalHp;
		this.currentMp = this.totalMp;
		SpellBook spellBook = new SpellBook();
		addSpell(1, spellBook.getFirstSpell(SpellType.HEAL));
		addSpell(2, spellBook.getSecondSpell(SpellType.HEAL));
		addSpell(3, spellBook.getThirdSpell(SpellType.HEAL));
	}
	
	// Creating a method to protect healer from take damage
	public void protect() {
		this.isProtected = true;
	}
	
	// Taking damage when protected
	@Override
	public void takeDamage(int damage) {
		if (this.isProtected == false) {
			super.takeDamage(damage);
		} else {
			this.isProtected = false;
		}
	}

	// Perform healer turn
	@Override
	public void performTurn(List<Hero> targets) {
		// Searching for a target
		Hero currentTarget = null;
		for (Hero target: targets) {
			if (currentTarget == null && target.alive && !target.fullLife()) {
				currentTarget = target;
			} else if (currentTarget != null &&  target.getCurrentHp() < currentTarget.getCurrentHp() && target.alive && !target.fullLife()) {
				currentTarget = target;
			}
		}
		
		int healing = 0;
		
		// Implementing the protect mechanic and cooldown
		if (this.protectCooldown > 0) {
			this.protectCooldown--;
		}
		
		if (currentTarget == null) {
			if (this.protectCooldown == 0) {
				this.protect();
				System.out.println(this.faction + " healer, " + this.name + ", is protected. 0 damage received.");
				this.protectCooldown = this.protectDelay;
			}
		// Selecting a spell to use and calculating the heal
		} else if (this.spellBook.get(3).getCost() <= this.currentMp) {
			healing = this.spellBook.get(3).calculateDamage(intel);
			currentTarget.recoverHp(healing);
			this.currentMp -= this.spellBook.get(3).getCost();
			System.out.println("The " + this.faction + " healer, " + this.name + ", heal " + healing + " HP to " + currentTarget.getName() + " with " + this.spellBook.get(3).getName());	
		} else if (this.spellBook.get(2).getCost() <= this.currentMp) {
			healing = this.spellBook.get(2).calculateDamage(intel);
			currentTarget.recoverHp(healing);
			this.currentMp -= this.spellBook.get(2).getCost();
			System.out.println("The " + this.faction + " healer, " + this.name + ", heal " + healing + " HP to " + currentTarget.getName() + " with " + this.spellBook.get(2).getName());
		} else if (this.spellBook.get(1).getCost() <= this.currentMp) {
			healing = this.spellBook.get(1).calculateDamage(intel);
			currentTarget.recoverHp(healing);
			this.currentMp -= this.spellBook.get(1).getCost();
			System.out.println("The " + this.faction + " healer, " + this.name + ", heal " + healing + " HP to " + currentTarget.getName() + " with " + this.spellBook.get(1).getName());
		} else {
			// Recovering MP if have no mana to cast a spell
			this.recoverMp();
			System.out.println(this.faction + " healer, " + this.name + ", have no mana to cast a spell. Recovering MP...");
		}
	}
}