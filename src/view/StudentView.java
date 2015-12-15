package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import javax.swing.*;

import orm.PersonObject;
import orm.ProfessorObject;
import orm.StudentObject;

@SuppressWarnings("serial")
public class StudentView extends JFrame {

	private static StudentObject studentObject;
	
	public StudentView(StudentObject studentObject) throws SQLException, IOException {
		
		StudentView.studentObject =  studentObject;
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		PanelHeader panelHeader = new PanelHeader();
		add(panelHeader);

		JLabel lblHeader = new JLabel("Signup for new account.");
		lblHeader.setFont(new Font("Courier New", Font.BOLD, 40));
		lblHeader.setPreferredSize(new Dimension(1700, 40));
		lblHeader.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeader.setForeground(Color.LIGHT_GRAY);
		add(lblHeader);

		JPanel panelBody = new JPanel();
		panelBody.setPreferredSize(new Dimension(1500, 500));
		panelBody.setLayout(new FlowLayout(FlowLayout.CENTER));

		JPanel panelLeft = new JPanel();
		panelLeft.setPreferredSize(new Dimension(500, 500));
		panelLeft.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JPanel panelRight = new JPanel();
		panelRight.setPreferredSize(new Dimension(430, 500));
		panelRight.setLayout(new FlowLayout(FlowLayout.LEFT));

		panelBody.add(panelLeft);
		panelBody.add(panelRight);
		add(panelBody);

		JLabel lblSpace = new JLabel("Account Type:");
		lblSpace.setPreferredSize(new Dimension(300, 35));
		lblSpace.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpace.setFont(new Font("Courier New", Font.BOLD, 20));
		lblSpace.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblSpace);

		JLabel lblStudentID = new JLabel("ID:");
		lblStudentID.setPreferredSize(new Dimension(300, 35));
		lblStudentID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentID.setFont(new Font("Courier New", Font.BOLD, 20));
		lblStudentID.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblStudentID);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setPreferredSize(new Dimension(300, 40));
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblFirstName.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setPreferredSize(new Dimension(300, 35));
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblLastName.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblLastName);

		JLabel lblGender = new JLabel(studentObject.getGender());
		lblGender.setPreferredSize(new Dimension(300, 35));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Courier New", Font.BOLD, 20));
		lblGender.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblGender);

		JLabel lblDOB = new JLabel("Date Of Birth:");
		lblDOB.setPreferredSize(new Dimension(300, 35));
		lblDOB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDOB.setFont(new Font("Courier New", Font.BOLD, 20));
		lblDOB.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblDOB);

		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setPreferredSize(new Dimension(300, 35));
		lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		lblNationality.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblNationality);

		JLabel combo = new JLabel("Account Type:2");
		combo.setPreferredSize(new Dimension(300, 35));
		combo.setHorizontalAlignment(SwingConstants.LEFT);
		combo.setFont(new Font("Courier New", Font.BOLD, 20));
		combo.setForeground(Color.DARK_GRAY);
		panelRight.add(combo);

		JLabel txtID = new JLabel("ID:2");
		txtID.setPreferredSize(new Dimension(300, 35));
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setFont(new Font("Courier New", Font.BOLD, 20));
		txtID.setForeground(Color.DARK_GRAY);
		panelRight.add(txtID);

		JLabel txtFirstName = new JLabel("First Name:2");
		txtFirstName.setPreferredSize(new Dimension(300, 35));
		txtFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		txtFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtFirstName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtFirstName);

		JLabel txtLastName = new JLabel("Last Name:2");
		txtLastName.setPreferredSize(new Dimension(300, 35));
		txtLastName.setHorizontalAlignment(SwingConstants.LEFT);
		txtLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtLastName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtLastName);

		JLabel Gender = new JLabel("Gender:2");
		Gender.setPreferredSize(new Dimension(300, 35));
		Gender.setHorizontalAlignment(SwingConstants.LEFT);
		Gender.setFont(new Font("Courier New", Font.BOLD, 20));
		Gender.setForeground(Color.DARK_GRAY);
		panelRight.add(Gender);

		JLabel DateOfBirth = new JLabel("Date Of Birth:2");
		DateOfBirth.setPreferredSize(new Dimension(300, 35));
		DateOfBirth.setHorizontalAlignment(SwingConstants.LEFT);
		DateOfBirth.setFont(new Font("Courier New", Font.BOLD, 20));
		DateOfBirth.setForeground(Color.DARK_GRAY);
		panelRight.add(DateOfBirth);


		JLabel txtNationality = new JLabel("Nationality");
		txtNationality.setPreferredSize(new Dimension(400, 35));
		txtNationality.setHorizontalAlignment(SwingConstants.LEFT);
		txtNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		txtNationality.setForeground(Color.DARK_GRAY);
		panelRight.add(txtNationality);

		JButton viewgrade = new JButton("View  Grade");
		viewgrade.setPreferredSize(new Dimension(150, 35));
		viewgrade.setFont(new Font("Calibri", Font.BOLD, 20));
		panelRight.add(viewgrade);

		JButton Logout = new JButton("Logout");
		Logout.setPreferredSize(new Dimension(150, 35));
		Logout.setFont(new Font("Calibri", Font.BOLD, 20));
		panelRight.add(Logout);

		JButton register = new JButton("Register for Course");
		register.setFont(new Font("Calibri", Font.BOLD, 20));
		register.setPreferredSize(new Dimension(305, 35));
		register.setForeground(Color.DARK_GRAY);
		panelRight.add(register);

		
		
		
		
		
		
		viewgrade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		Logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
		
			}
		});

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					}
		});
		
		

		
		
		
		
		
		PanelFooter panelFooter = new PanelFooter();
		add(panelFooter);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {
		
		
		StudentView.studentObject = new StudentObject("FirstName", "LastName", new GregorianCalendar(2011,1,1));
		StudentView.studentObject.setID(984946);
		StudentView.studentObject.setGender("Male");
		

		StudentView studentView = new StudentView(StudentView.studentObject);
	}

}
