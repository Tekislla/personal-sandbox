package rpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Game {
	
	public static void main(String[] args) throws InterruptedException {
		
		battle();
	}
	
	// Battle module
	public static void battle() throws InterruptedException {
		List<Hero> alliance = new ArrayList<Hero>();
		List<Hero> horde = new ArrayList<Hero>();
		
		alliance.add(new Fighter("Fireson", Faction.ALLIANCE));
		alliance.add(new Mage("Aguilashine", Faction.ALLIANCE));
		alliance.add(new Healer("Smithlaf", Faction.ALLIANCE));
		alliance.add(new Berseker("Crookedby", Faction.ALLIANCE));
		
		horde.add(new Fighter("Tareek", Faction.HORDE));
		horde.add(new Mage("Negmagog", Faction.HORDE));
		horde.add(new Healer("Mephistophcook", Faction.HORDE));
		horde.add(new Berseker("Fordeus", Faction.HORDE));
		
		List<TurnOrder> turnOrderList = new ArrayList<TurnOrder>();
		
		turnOrderList.add(new TurnOrder(alliance.get(0).rollInit(), alliance.get(0)));
		turnOrderList.add(new TurnOrder(alliance.get(1).rollInit(), alliance.get(1)));
		turnOrderList.add(new TurnOrder(alliance.get(2).rollInit(), alliance.get(2)));
		turnOrderList.add(new TurnOrder(alliance.get(3).rollInit(), alliance.get(3)));
		
		turnOrderList.add(new TurnOrder(horde.get(0).rollInit(), horde.get(0)));
		turnOrderList.add(new TurnOrder(horde.get(1).rollInit(), horde.get(1)));
		turnOrderList.add(new TurnOrder(horde.get(2).rollInit(), horde.get(2)));
		turnOrderList.add(new TurnOrder(horde.get(3).rollInit(), horde.get(3)));
		
		Collections.sort(turnOrderList, Comparator.reverseOrder());
		
		boolean endBattle = false;
		int round = 1;
		
		do {
			System.out.println("\nRound " + round + "\n");
			for (TurnOrder turnOrder : turnOrderList) {
				boolean isHealer = Healer.class.equals(turnOrder.getHero().getClass());
				
				if (turnOrder.getHero().alive) {
					if (isHealer) {
						if (turnOrder.getHero().getFaction() == Faction.ALLIANCE) {
							turnOrder.getHero().performTurn(alliance);
						}else {
							turnOrder.getHero().performTurn(horde);
						}
					} else {
						if (turnOrder.getHero().getFaction() == Faction.ALLIANCE) {
			
							turnOrder.getHero().performTurn(horde);
						} else {
							turnOrder.getHero().performTurn(alliance);
						}	
					}
				}
				
				if (!alliance.get(0).alive && !alliance.get(1).alive && !alliance.get(2).alive && !alliance.get(3).alive) {
					endBattle = true;
					break;
				} else if (!horde.get(0).alive && !horde.get(1).alive && !horde.get(2).alive && !horde.get(3).alive) {
					endBattle = true;
					break;
				}
			}
			System.out.println("\n");
			printStatus(turnOrderList);
			
			if (!alliance.get(0).alive && !alliance.get(1).alive && !alliance.get(2).alive && !alliance.get(3).alive) {
				System.out.println("\nHorde wins!");
			} else if (!horde.get(0).alive && !horde.get(1).alive && !horde.get(2).alive && !horde.get(3).alive) {
				System.out.println("\nAlliance wins!");
			}
			round++;
			Thread.sleep(1500);
		} while (!endBattle);
	}
	
	// Module to print hero status
	public static void printStatus(List<TurnOrder> heroList) {
		String statusLine = "%s - %s: %s/%s HP, %s/%s MP";
		for (TurnOrder turnOrder : heroList) {
			Hero hero = turnOrder.getHero();
			if (hero.getCurrentHp() > 0) {
				System.out.println(String.format(statusLine, hero.getFaction(), hero.getName(), hero.getCurrentHp(), hero.getTotalHp(), hero.getCurrentMp(), hero.getTotalMp()));
			} else {
				System.out.println(hero.getFaction() + " - " + hero.getName() + " is dead.");
			}
		}
	}
}