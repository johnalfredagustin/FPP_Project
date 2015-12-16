package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;

import controller.ProfessorController;
import model.Roles;
import orm.ProfessorObject;
import orm.ProfessorStudentCourseMetricObject;

@SuppressWarnings("serial")
public class ProfessorView extends JFrame {

	private static ProfessorObject professorObject;

	public ProfessorView(ProfessorObject professorObject) throws SQLException, IOException {

		ProfessorView.professorObject = professorObject;

		setSize(1700, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		PanelHeader panelHeader = new PanelHeader();
		add(panelHeader);

		JLabel lblHeader = new JLabel("Professor View.");
		lblHeader.setFont(new Font("Courier New", Font.BOLD, 40));
		lblHeader.setPreferredSize(new Dimension(1700, 40));
		lblHeader.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeader.setForeground(Color.LIGHT_GRAY);
		add(lblHeader);

		JPanel panelBody = new JPanel();
		panelBody.setPreferredSize(new Dimension(1700, 570));
		panelBody.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panelLeft = new JPanel();
		panelLeft.setPreferredSize(new Dimension(800, 500));
		panelLeft.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JPanel panelRight = new JPanel();
		panelRight.setPreferredSize(new Dimension(500, 500));
		panelRight.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panelBodyFooter = new JPanel();
		panelBodyFooter.setPreferredSize(new Dimension(1700, 100));
		panelBodyFooter.setLayout(new FlowLayout(FlowLayout.CENTER));

		
		panelBody.add(panelLeft);
		panelBody.add(panelRight);
		panelBody.add(panelBodyFooter);
		add(panelBody);
	

		JLabel lblSpace = new JLabel("Account Type:");
		lblSpace.setPreferredSize(new Dimension(800, 35));
		lblSpace.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpace.setFont(new Font("Courier New", Font.BOLD, 20));
		lblSpace.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblSpace);

		JLabel lblStudentID = new JLabel("ID:");
		lblStudentID.setPreferredSize(new Dimension(800, 35));
		lblStudentID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentID.setFont(new Font("Courier New", Font.BOLD, 20));
		lblStudentID.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblStudentID);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setPreferredSize(new Dimension(800, 35));
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblFirstName.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setPreferredSize(new Dimension(800, 35));
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblLastName.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblLastName);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setPreferredSize(new Dimension(800, 35));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Courier New", Font.BOLD, 20));
		lblGender.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblGender);

		JLabel lblDOB = new JLabel("Date Of Birth:");
		lblDOB.setPreferredSize(new Dimension(800, 35));
		lblDOB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDOB.setFont(new Font("Courier New", Font.BOLD, 20));
		lblDOB.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblDOB);

		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setPreferredSize(new Dimension(800, 35));
		lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		lblNationality.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblNationality);
		

		JLabel lblAccountType = new JLabel(Roles.Professor.toString());
		lblAccountType.setPreferredSize(new Dimension(400, 35));
		lblAccountType.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountType.setFont(new Font("Courier New", Font.BOLD, 20));
		lblAccountType.setForeground(Color.DARK_GRAY);
		panelRight.add(lblAccountType);
		
		JLabel lblTextID = new JLabel(String.valueOf(professorObject.getID()));
		lblTextID.setPreferredSize(new Dimension(400, 35));
		lblTextID.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextID.setFont(new Font("Courier New", Font.BOLD, 20));
		lblTextID.setForeground(Color.DARK_GRAY);
		panelRight.add(lblTextID);

		JLabel lblTextFirstName = new JLabel(professorObject.getFirstName());
		lblTextFirstName.setPreferredSize(new Dimension(400, 35));
		lblTextFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblTextFirstName.setForeground(Color.DARK_GRAY);
		panelRight.add(lblTextFirstName);

		JLabel lblTextLastName = new JLabel(professorObject.getLastName());
		lblTextLastName.setPreferredSize(new Dimension(400, 35));
		lblTextLastName.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblTextLastName.setForeground(Color.DARK_GRAY);
		panelRight.add(lblTextLastName);

		JLabel lblTextGender = new JLabel(professorObject.getGender());
		lblTextGender.setPreferredSize(new Dimension(400, 35));
		lblTextGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextGender.setFont(new Font("Courier New", Font.BOLD, 20));
		lblTextGender.setForeground(Color.DARK_GRAY);
		panelRight.add(lblTextGender);

		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		JLabel lblTextDOB = new JLabel(fmt.format(professorObject.getDOB().getTime()));
		lblTextDOB.setPreferredSize(new Dimension(400, 35));
		lblTextDOB.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextDOB.setFont(new Font("Courier New", Font.BOLD, 20));
		lblTextDOB.setForeground(Color.DARK_GRAY);
		panelRight.add(lblTextDOB);

		JLabel lblTextNationality = new JLabel(professorObject.getNationality());
		lblTextNationality.setPreferredSize(new Dimension(400, 35));
		lblTextNationality.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		lblTextNationality.setForeground(Color.DARK_GRAY);
		panelRight.add(lblTextNationality);
		
		JButton btnGrades = new JButton("View / Send Grades");
		btnGrades.setPreferredSize(new Dimension(200, 35));
		btnGrades.setFont(new Font("Calibri", Font.BOLD, 20));
		panelBodyFooter.add(btnGrades);

		JButton btnRegister = new JButton("Register To Course");
		btnRegister.setPreferredSize(new Dimension(200, 35));
		btnRegister.setFont(new Font("Calibri", Font.BOLD, 20));
		panelBodyFooter.add(btnRegister);

		JButton btnLogout = new JButton("Logout");
		btnLogout.setPreferredSize(new Dimension(200, 35));
		btnLogout.setFont(new Font("Calibri", Font.BOLD, 20));
		btnLogout.setForeground(Color.DARK_GRAY);
		panelBodyFooter.add(btnLogout);

		
		btnGrades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new ProfessorGradeView(professorObject);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new LoginView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		PanelFooter panelFooter = new PanelFooter();
		add(panelFooter);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {

		ProfessorView.professorObject = new ProfessorObject("FirstName", "LastName", new GregorianCalendar(2011,1,1));
		ProfessorView.professorObject.setID(10001);
		
		ProfessorView profView = new ProfessorView(ProfessorView.professorObject);
	}


}
