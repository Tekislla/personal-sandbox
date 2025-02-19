package rpg;

import java.util.List;

public abstract class Hero {
	protected String name;
	protected int totalHp;
	protected int totalMp;
	protected int currentHp;
	protected int currentMp;
	protected int str;
	protected int dex;
	protected int intel;
	protected int cons;
	protected boolean alive = true;
	protected Faction faction;
	
	public Hero() {
		
	}

	public String getName() {
		return name;
	}

	public int getTotalHp() {
		return totalHp;
	}

	public int getTotalMp() {
		return totalMp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public int getCurrentMp() {
		return currentMp;
	}

	public boolean isAlive() {
		return alive;
	}

	public Faction getFaction() {
		return faction;
	}
	
	// Verifying if the target is full life
	public boolean fullLife() {
		return this.currentHp == this.totalHp;
	}

	// Creating a method to take damage
	public void takeDamage(int damage) {
		if (this.alive) {
			this.currentHp -= damage;
			if (this.currentHp <= 0) {
				this.currentHp = 0;
				this.alive = false;
			}
		}
	}

	// Creating a method to recover HP
	public void recoverHp(int heal) {
		if (this.alive) {
			this.currentHp += heal;
			if (this.currentHp > this.totalHp) {
				this.currentHp = this.totalHp;
			}	
		}
	}

	// Creating a method to start the game
	public int rollInit() {
		int init = Dice.roll("1d20");
		return init;
	}

	// Abstract perform turn
	public abstract void performTurn(List<Hero> targets);

}
