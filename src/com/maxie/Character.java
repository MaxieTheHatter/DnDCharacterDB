package com.maxie;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import javax.persistence.*;

/**
 * Model class regarding the character object, it also has information regarding
 * database mapping through hibernate annotations
 * 
 * @author Maxie
 *
 */
@Entity(name = "playercharacter")
public class Character {
	@Transient
	Scanner keyboard = new Scanner(System.in);
	@Transient
	SpinnerNumberModel sModel = new SpinnerNumberModel(1, 1, 20, 1);
	@Transient
	JSpinner spinner = new JSpinner(sModel);
	@Transient
	Utilities util = new Utilities();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "charId")
	private int charId;
	@Column(name = "level")
	private int level;
	@Column(name = "experience")
	private int exp;
	@Column(name = "name")
	private String name;
	@Column(name = "class")
	private String charClass;
	@Column(name = "race")
	private String charRace;

	/**
	 * Empty constructor, used by hibernate when creating a database session
	 */
	public Character() {

	}

	/**
	 * Constructor used when adding a new character to the list.
	 * 
	 * @param addToList
	 *            if true, prompt user to set character attributes
	 */
	public Character(boolean addToList) {
		if (addToList = true) {
			this.setName();
			this.setCharClass();
			this.setCharRace();
			this.setStartLevel();
			this.setExp(calculateExp(this.level));
		}
	}

	public int getCharId() {
		return charId;
	}

	public void setCharId(int id) {
		this.charId = id;
	}

	public int getLevel() {
		return level;
	}

	/**
	 * Prompts user to input a starting level, typically when creating a character
	 * object
	 */
	public void setStartLevel() {
		int option = JOptionPane.showOptionDialog(null, spinner, "Enter Character Level", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (option == JOptionPane.OK_OPTION) {
			Integer value = (Integer) spinner.getValue();
			this.level = value;
		}
	}

	/**
	 * When experience goes over the level up point, raise the character level by
	 * one
	 */
	private void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	private void setExp(int exp) {
		this.exp = exp;
	}

	public String getName() {
		return name;
	}

	/**
	 * Prompts user to enter character name, typically used when creating a
	 * character object
	 */
	private void setName() {
		String message = "Enter Character Name:";
		do {
			this.name = JOptionPane.showInputDialog(message);
			message = "<html><b style='color:red'>Enter Character Name:</b><br>";
		} while (name != null && !name.matches("[a-zA-Z]+"));
	}

	public String getCharClass() {
		return charClass;
	}

	/**
	 * Creates dialog which prompts user to select a class
	 */
	private void setCharClass() {
		CharacterClass[] options = CharacterClass.values();
		this.charClass = (String) JOptionPane.showInputDialog(null, "Select Character Class:", "choose",
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]).toString();
	}

	public String getCharRace() {
		return charRace;
	}

	/**
	 * Creates dialog which prompts user to select character race
	 */
	private void setCharRace() {
		CharacterRace[] options = CharacterRace.values();
		this.charRace = (String) JOptionPane.showInputDialog(null, "Select Character Race:", "choose",
				JOptionPane.QUESTION_MESSAGE, null, options, options[1]).toString();
	}

	/**
	 * Calculates current experience based on character level from the formula
	 * experience at start of level = level x 1000
	 * 
	 * @param level
	 *            current character level entered by user when creating character
	 *            object
	 * @return current experience at the beginning of entered level
	 */
	private int calculateExp(int level) {
		return level * 1000;
	}

	/**
	 * Updates experience points for character, when it reaches a threshold it
	 * levels up
	 * 
	 * @param exp
	 *            the experience gained from the last game
	 */
	public void updateExp(int exp) {
		int oldExp = this.getExp();
		this.setExp(oldExp + exp);
		if (util.firstDigit(this.getLevel()) != util.firstDigit(this.getExp())) {
			this.setLevel(util.firstDigit(this.getExp()));
		}
	}

	/**
	 * Returns a formated string with all the character attributes
	 */
	public String toString() {
		return ("<b>Character ID:</b> " + this.getCharId() + "\n<b>Name:</b> " + this.getName() + "\n<b>Race:</b> "
				+ this.getCharRace() + "\n<b>Class:</b> " + this.getCharClass() + "\n<b>Level:</b> " + this.getLevel()
				+ "\n<b>Exp:</b> " + this.getExp());
	}

	/*
	 * Set of getters and setters used by hibernate (not to be accessed otherwise)
	 */
	private void setName(String name) {
		this.name = name;
	}

	private void setCharClass(String charClass) {
		this.charClass = charClass;
	}

	private void setCharRace(String charRace) {
		this.charRace = charRace;
	}

}
