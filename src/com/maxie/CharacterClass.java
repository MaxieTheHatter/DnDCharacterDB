package com.maxie;

/**
 * Predefined selections of classes the character can select
 * @author Maxie
 *
 */
public enum CharacterClass {
	BARBARIAN, BARD, CLERIC, DRUID, FIGHTER, MONK, PALADIN, RANGER, ROGUE, SORCERER, WARLOCK, WIZARD;

	/**
	 * Returns enumeration value formatted to lower case
	 */
	public String toString() {
		return name().toLowerCase();
	}
}
