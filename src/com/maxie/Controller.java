package com.maxie;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;

/**
 * Controller class that handles all interaction between the view and model
 * classes
 * 
 * @author Maxie
 *
 */

public class Controller {

	public Controller() {
		View view = new View();
		Model model = new Model();
		DatabaseConnector connector = new DatabaseConnector();
		/*
		 * Creates a temporary character and adds it to a linked list
		 */
		view.getAddButton().addActionListener(e -> {
			model.createCharacter();
		});

		/*
		 * Lists all character in the database
		 */
		view.getListButton().addActionListener(e -> {
			view.updateCenterLabel(buildList(connector.read(), "Characters"));
		});

		/*
		 * Lets user search database by specific race or class
		 */
		view.getSearchButton().addActionListener(e -> {
			String[] searchOptions = { "race", "class" };

			String type = (String) JOptionPane.showInputDialog(null, "Choose search type", "choose",
					JOptionPane.QUESTION_MESSAGE, null, searchOptions, searchOptions[1]).toString();

			if (type.equals("class")) {
				CharacterClass[] options = CharacterClass.values();
				String query = (String) JOptionPane.showInputDialog(null, "Select Character Class:", "choose",
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]).toString();
				try {
					view.updateCenterLabel(buildList(connector.search(type, query), "Characters"));
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			} else if (type.equals("race")) {
				CharacterRace[] options = CharacterRace.values();
				String query = (String) JOptionPane.showInputDialog(null, "Select Character Race:", "choose",
						JOptionPane.QUESTION_MESSAGE, null, options, options[1]).toString();
				try {
					view.updateCenterLabel(buildList(connector.search(type, query), "Characters"));
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
			}

		});

		/*
		 * Let's user search for character by name
		 */
		view.getSearchNameBtn().addActionListener(e -> {
			String query = JOptionPane.showInputDialog(null, "Enter search query");
			view.updateCenterLabel(buildList(connector.search("name", query), "Characters"));
		});

		/*
		 * Prompts user for input and updates exp of one character
		 */
		view.getExpButton().addActionListener(e -> {
			view.updateCenterLabel(buildList(connector.read(), "Characters"));
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2, 2, 2));
			JTextField charField = new JTextField(5);
			JTextField expField = new JTextField(5);
			panel.add(new JLabel("Enter id of character"));
			panel.add(charField);
			panel.add(new JLabel("Enter experience gained"));
			panel.add(expField);
			int option = JOptionPane.showConfirmDialog(null, panel, "Choose character to Update",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				String character = charField.getText();
				String experience = expField.getText();
				try {
					Integer charId = Integer.parseInt(character);
					int exp = Integer.parseInt(experience);
					connector.updateExp(charId, exp);
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Experience added!");
			}
		});

		/*
		 * Prompts user for input and deletes chosen character from the database
		 */
		view.getDeleteButton().addActionListener(e -> {
			view.updateCenterLabel(buildList(connector.read(), "Characters"));
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0, 1, 1, 1));
			JTextField charField = new JTextField(5);
			panel.add(new JLabel("Enter id of character"));
			panel.add(charField);
			int option = JOptionPane.showConfirmDialog(null, panel, "Choose character to Update",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.YES_OPTION) {
				String character = charField.getText();
				try {
					Integer charId = Integer.parseInt(character);
					connector.delete(charId);
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Character deleted!");
			}
		});

		/*
		 * Retrieves all objects from the linked list and saves them to the database
		 */
		view.getSaveButton().addActionListener(e -> {
			StringBuilder sb = new StringBuilder();
			connector.create(Model.getCharacters());
			JOptionPane.showMessageDialog(null, "Characters saved to database!");
		});

		/*
		 * Exits the application
		 */
		view.getExitButton().addActionListener(e -> {
			int confirmExit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", null,
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (confirmExit == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		});

	}

	/**
	 * takes a list and builds it into a string, no matter how many objects it
	 * contains. use String entity to create a title showing what's listed
	 * 
	 * @param list
	 *            The list which contains the objects you want to print
	 * @param entity
	 *            Specifies what the title should say, by default "List of " +
	 *            entity
	 * @return predefined String containing objects of the list, uses toString() of
	 *         the object
	 */
	public String buildList(List list, String entity) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>List of " + entity + ":<br>");
		for (Object c : list) {
			sb.append(c).append("   ");
			sb.append("<br><br>");
		}
		sb.append("</html>");
		return sb.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Controller();
	}

}
