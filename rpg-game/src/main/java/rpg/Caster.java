package rpg;

import java.util.HashMap;

public abstract class Caster extends Hero {
	// Creating an hash map spell book, with key and value
	protected HashMap<Integer, Spell> spellBook = new HashMap<Integer, Spell>();
	
	// Creating an method to add spells into spell book
	public void addSpell(int slot, Spell spell) {
		spellBook.put(slot, spell);
	}
	
	// Creating an method to recover MP
	public void recoverMp() {
		if (this.alive) {
			this.currentMp  += (this.totalMp * 0.25);
			if (this.currentMp > this.totalMp) {
				this.currentMp = this.totalMp;
			} 
		}
	}

}
