package view;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;

import orm.PersonObject;
import orm.StudentObject;

@SuppressWarnings("serial")
public class StudentView extends JFrame {

	private static PersonObject personObject;
	
	public StudentView(PersonObject personObject) throws SQLException, IOException {

		StudentView.personObject =  personObject;
		
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		 PanelHeader panelHeader = new PanelHeader();
		 add(panelHeader);
		
		
		 
		 add(new JLabel("Welcome back " + personObject.getFirstName() + " " + personObject.getLastName()));
		// JPanel labelPanel = new JPanel();
		// labelPanel.setPreferredSize(new Dimension(250, 100));
		// labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//
		// JLabel titleLabel = new JLabel("Student View");
		// titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		// titleLabel.setForeground(Color.LIGHT_GRAY);
		//
		// labelPanel.add(titleLabel);
		// add(labelPanel, BorderLayout.NORTH);
		//
		// JPanel tablePanel = new JPanel();
		//
		//
		// JPanel buttonPanel = new JPanel();
		// buttonPanel.setPreferredSize(new Dimension(100, 50));
		// buttonPanel.setLayout(new FlowLayout());
		// ((FlowLayout) buttonPanel.getLayout()).setHgap(30);
		//
		// JButton buttonSubmit = new JButton("");
		// JButton buttonBack = new JButton("Back");
		// buttonSubmit.setPreferredSize(new Dimension(150, 50));
		// buttonBack.setPreferredSize(new Dimension(150, 50));
		// buttonPanel.add(buttonSubmit);
		// buttonPanel.add(buttonBack);
		// add(buttonPanel, BorderLayout.SOUTH);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {
		StudentView studentView = new StudentView(StudentView.personObject);
	}

}
