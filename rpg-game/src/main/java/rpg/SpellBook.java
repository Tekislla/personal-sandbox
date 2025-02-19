package rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpellBook {
	// Creating three different tiers spell books
	protected List<Spell> tier1 = new ArrayList<Spell>();
	protected List<Spell> tier2 = new ArrayList<Spell>();
	protected List<Spell> tier3 = new ArrayList<Spell>();

	// Adding different tiers of spells on spell books
	public SpellBook() {
		// Tier 1 spells
		tier1.add(new Spell("Fire Thrower", "1d6", 10, SpellType.DAMAGE));
		tier1.add(new Spell("Fire Pillar", "1d6", 10, SpellType.DAMAGE));
		tier1.add(new Spell("Dark Dart", "1d6", 10, SpellType.DAMAGE));
		tier1.add(new Spell("Dark Bomb", "1d6", 10, SpellType.DAMAGE));
		tier1.add(new Spell("Lightning Strike", "1d6", 10, SpellType.DAMAGE));
		tier1.add(new Spell("Light Arrow", "1d6", 10, SpellType.DAMAGE));
		tier1.add(new Spell("Minor Healing", "1d6", 10, SpellType.HEAL));
		tier1.add(new Spell("Heal Circle", "1d6", 10, SpellType.HEAL));
		tier1.add(new Spell("Healing Dart", "1d6", 10, SpellType.HEAL));
		tier1.add(new Spell("Healing Arrow", "1d6", 10, SpellType.HEAL));

		// Tier 2 spells
		tier2.add(new Spell("Fire Turret", "2d8", 17, SpellType.DAMAGE));
		tier2.add(new Spell("Fire Ball", "2d8", 17, SpellType.DAMAGE));
		tier2.add(new Spell("Dark Tempest", "2d8", 17, SpellType.DAMAGE));
		tier2.add(new Spell("Night Lotus", "2d8", 17, SpellType.DAMAGE));
		tier2.add(new Spell("Light Storm", "2d8", 17, SpellType.DAMAGE));
		tier2.add(new Spell("Light Spear", "2d8", 17, SpellType.DAMAGE));
		tier2.add(new Spell("Healing Altar", "2d8", 17, SpellType.HEAL));
		tier2.add(new Spell("Blessing", "2d8", 17, SpellType.HEAL));
		tier2.add(new Spell("Divine Kiss", "2d8", 17, SpellType.HEAL));

		// Tier 3 spells
		tier3.add(new Spell("Phoenix's Fury", "2d16", 25, SpellType.DAMAGE));
		tier3.add(new Spell("Dark Cemetery", "2d16", 25, SpellType.DAMAGE));
		tier3.add(new Spell("Light Judgement", "2d16", 25, SpellType.DAMAGE));
		tier3.add(new Spell("God's Altar", "2d16", 25, SpellType.HEAL));
		tier3.add(new Spell("Divine Blessing", "2d16", 25, SpellType.HEAL));
	}
	
	// Selecting tier 1 spell
	public Spell getFirstSpell(SpellType spellType) {
		Random random = new Random();
		boolean spellFound = false;
		Spell spell = null;

		do {
			int spellResult = random.nextInt(tier1.size());
			spell = tier1.get(spellResult);

			if (spellType == spell.getType()) {
				spellFound = true;
			}
			
		} while (!spellFound);
		
		return spell;
	}

	// Selecting tier 2 spell
	public Spell getSecondSpell(SpellType spellType) {
		Random random = new Random();
		boolean spellFound = false;
		Spell spell = null;

		do {
			int spellResult = random.nextInt(tier2.size());
			spell = tier2.get(spellResult);

			if (spellType == spell.getType()) {
				spellFound = true;
			}

		} while (!spellFound);

		return spell;
	}
	
	// Selecting tier 3 spell
	public Spell getThirdSpell(SpellType spellType) {
		Random random = new Random();
		boolean spellFound = false;
		Spell spell = null;

		do {
			int spellResult = random.nextInt(tier3.size());
			spell = tier3.get(spellResult);

			if (spellType == spell.getType()) {
				spellFound = true;
			}

		} while (!spellFound);

		return spell;
	}

}