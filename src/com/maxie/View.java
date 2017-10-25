package com.maxie;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

/**
 * View class, creates all graphical elements used in the application
 * @author Maxie
 *
 */

public class View {
	private JFrame frame;
	private JLabel centerLabel, topLabel, bottomLabel;
	private JPanel leftPanel;
	private JButton listButton, searchButton, searchNameBtn, addButton, expButton, deleteButton, exitButton, saveButton;

	public View() {
		createWindow();
	}

	/**
	 * Creates all graphical elements and populates them
	 */
	private void createWindow() {
		// create frame
		frame = new JFrame("DnD Characters");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create labels
		topLabel = new JLabel(new ImageIcon("images/d20.png"));
		centerLabel = new JLabel("<html>Welcome to my simple DnD character organizer."
				+ "<br>Please choose an option from the left</html>", SwingConstants.CENTER);
		leftPanel = new JPanel();
		leftPanel.setLayout(new GridBagLayout());
		String currentDate = new SimpleDateFormat("EEEE MMM d, yyyy").format(new Date());
		bottomLabel = new JLabel("Current date: " + currentDate);

		// create scrollpane
		JScrollPane scroller = new JScrollPane(centerLabel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroller.setPreferredSize(new Dimension(600, 400));

		// add components to frame
		createButtons(leftPanel);
		frame.getContentPane().add(topLabel, BorderLayout.NORTH);
		frame.getContentPane().add(scroller, BorderLayout.CENTER);
		frame.getContentPane().add(bottomLabel, BorderLayout.SOUTH);
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);

		// add styling
		centerLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		centerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		leftPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

		// configure frame
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * Creates all needed buttons and adds them to specified panel
	 * 
	 * @param panel
	 *            specifies element to populate with buttons
	 */
	private void createButtons(JPanel panel) {
		JPanel buttonPanel = new JPanel(new GridLayout(8, 1));
		listButton = new JButton("List all Characters");
		searchButton = new JButton("Search by class/race");
		searchNameBtn = new JButton("Search by name");
		addButton = new JButton("Add new Character");
		expButton = new JButton("Add experience to Character");
		deleteButton = new JButton("Delete Character");
		saveButton = new JButton("Save to DataBase");
		exitButton = new JButton("Exit");
		buttonPanel.add(listButton);
		buttonPanel.add(searchButton);
		buttonPanel.add(searchNameBtn);
		buttonPanel.add(addButton);
		buttonPanel.add(expButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(saveButton);
		buttonPanel.add(exitButton);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weighty = 1;
		panel.add(buttonPanel, gbc);
	}

	/**
	 * Updates the text within the central panel
	 * 
	 * @param text
	 *            String containing the text to display
	 */
	public void updateCenterLabel(String text) {
		centerLabel.setText(text);
	}

	public JButton getListButton() {
		return listButton;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JButton getExpButton() {
		return expButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JButton getSearchNameBtn() {
		return searchNameBtn;
	}

}
