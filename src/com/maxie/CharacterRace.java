package com.maxie;
/**
 * Predefined races which the character object can select
 * @author Maxie
 *
 */
public enum CharacterRace {
	DWARF, ELF, HALFLING, HUMAN, DRAGONBORN, GNOME, HALFELF, HALFORC, TIEFLING;
	/**
	 * Returns enumeration value formatted to lower case
	 */
	public String toString() {
		return name().toLowerCase();
	}

}
