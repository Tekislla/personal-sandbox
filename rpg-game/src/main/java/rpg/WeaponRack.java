package rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeaponRack {
	// Creating an weapon rack
	protected List<Weapon> weaponRack = new ArrayList<Weapon>();

	// Adding weapons on weapon rack
	public WeaponRack() {
		weaponRack.add(new Weapon("Profaned Greatsword", "1d20", WeaponType.BERSEKER));
		weaponRack.add(new Weapon("Death Hammer", "1d16", WeaponType.BERSEKER));
		weaponRack.add(new Weapon("Demon Greataxe", "1d20", WeaponType.BERSEKER));
		weaponRack.add(new Weapon("Ice Rapier", "1d8", WeaponType.BERSEKER));
		weaponRack.add(new Weapon("Knight's Warhammer", "1d8", WeaponType.BERSEKER));
		weaponRack.add(new Weapon("Black Knight Sword", "1d16", WeaponType.BRUISER));
		weaponRack.add(new Weapon("Dark Claws", "1d16", WeaponType.BRUISER));
		weaponRack.add(new Weapon("Flaming Katana", "1d20", WeaponType.BRUISER));
		weaponRack.add(new Weapon("Elderwood Axe", "1d20", WeaponType.BRUISER));
		weaponRack.add(new Weapon("War Hammer", "1d8", WeaponType.BRUISER));
	}
	
	// Selecting an random weapon from weapon rack
	public Weapon selectWeapon(WeaponType weaponType) {
		Random random = new Random();
		boolean weaponFound = false;
		Weapon weapon = null;

		do {
			int weaponResult = random.nextInt(weaponRack.size());
			weapon = weaponRack.get(weaponResult);

			if (weaponType == weapon.getType()) {
				weaponFound = true;
			}
			
		} while (!weaponFound);
		
		return weapon;
	}

}