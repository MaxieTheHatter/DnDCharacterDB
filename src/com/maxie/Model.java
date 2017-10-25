package com.maxie;

import java.util.LinkedList;
/**
 * Model containing necessary method for creating and listing character objects
 * @author Maxie
 *
 */

public class Model {
	private static LinkedList<Character> characters = new LinkedList<Character>();

	public Model() {

	}

	/**
	 * Creates a new character, prompts the user for attributes and adds it to a
	 * linked list
	 */
	public void createCharacter() {
		Character character = new Character(true);
		characters.addLast(character); // add new character to list
	}

	/**
	 * Selects a character object and updates experience gain, leveling up when
	 * necessary
	 * 
	 * @param character
	 *            instance of the character object
	 * @param exp
	 *            experience gained
	 */
	public void updateCharExp(Character character, int exp) {
		character.updateExp(exp);
	}

	/**
	 * Get all instances of the object Character
	 * 
	 * @return list of all created character objects
	 */
	public static LinkedList<Character> getCharacters() {
		return characters;
	}

}
