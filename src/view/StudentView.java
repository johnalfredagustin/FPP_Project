package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.*;

import model.Roles;
import orm.StudentObject;

@SuppressWarnings("serial")
public class StudentView extends JFrame {

	private static StudentObject studentObject;
	
	public StudentView(StudentObject studentObject) throws SQLException, IOException {
		
		StudentView.studentObject =  studentObject;
		
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Course Registration System > STUDENT HOME ");
		setLayout(new FlowLayout(FlowLayout.LEFT));

		PanelHeader panelHeader = new PanelHeader(String.valueOf(Roles.Student));
		add(panelHeader);

		JLabel lblHeader = new JLabel("Course Registration System > STUDENT HOME ");
		lblHeader.setFont(new Font("Courier New", Font.BOLD, 40));
		lblHeader.setPreferredSize(new Dimension(1700, 40));
		lblHeader.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeader.setForeground(Color.LIGHT_GRAY);
		add(lblHeader);


		
		JPanel panelBody = new JPanel();
		panelBody.setPreferredSize(new Dimension(1700, 540));
		panelBody.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panelTop = new JPanel();
		panelTop.setPreferredSize(new Dimension(1700, 30));
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelBody.add(panelTop);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setPreferredSize(new Dimension(800, 400));
		panelLeft.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JPanel panelRight = new JPanel();
		panelRight.setPreferredSize(new Dimension(500, 400));
		panelRight.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel panelBodyFooter = new JPanel();
		panelBodyFooter.setPreferredSize(new Dimension(1700, 400));
		panelBodyFooter.setLayout(new FlowLayout(FlowLayout.CENTER));
		((FlowLayout) panelBodyFooter.getLayout()).setHgap(30);

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

		JLabel combo = new JLabel(Roles.Student.name());
		combo.setPreferredSize(new Dimension(300, 35));
		combo.setHorizontalAlignment(SwingConstants.LEFT);
		combo.setFont(new Font("Courier New", Font.BOLD, 20));
		combo.setForeground(Color.DARK_GRAY);
		panelRight.add(combo);

		JLabel txtID = new JLabel(String.valueOf(studentObject.getID()));
		txtID.setPreferredSize(new Dimension(300, 35));
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setFont(new Font("Courier New", Font.BOLD, 20));
		txtID.setForeground(Color.DARK_GRAY);
		panelRight.add(txtID);

		JLabel txtFirstName = new JLabel(studentObject.getFirstName());
		txtFirstName.setPreferredSize(new Dimension(300, 35));
		txtFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		txtFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtFirstName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtFirstName);

		JLabel txtLastName = new JLabel(studentObject.getLastName());
		txtLastName.setPreferredSize(new Dimension(300, 35));
		txtLastName.setHorizontalAlignment(SwingConstants.LEFT);
		txtLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtLastName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtLastName);

		JLabel Gender = new JLabel(studentObject.getGender());
		Gender.setPreferredSize(new Dimension(300, 35));
		Gender.setHorizontalAlignment(SwingConstants.LEFT);
		Gender.setFont(new Font("Courier New", Font.BOLD, 20));
		Gender.setForeground(Color.DARK_GRAY);
		panelRight.add(Gender);

		SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		JLabel DateOfBirth = new JLabel(fmt.format(studentObject.getDOB().getTime()));
		DateOfBirth.setPreferredSize(new Dimension(300, 35));
		DateOfBirth.setHorizontalAlignment(SwingConstants.LEFT);
		DateOfBirth.setFont(new Font("Courier New", Font.BOLD, 20));
		DateOfBirth.setForeground(Color.DARK_GRAY);
		panelRight.add(DateOfBirth);

		JLabel txtNationality = new JLabel(studentObject.getNationality());
		txtNationality.setPreferredSize(new Dimension(400, 35));
		txtNationality.setHorizontalAlignment(SwingConstants.LEFT);
		txtNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		txtNationality.setForeground(Color.DARK_GRAY);
		panelRight.add(txtNationality);

		JButton viewgrade = new JButton("View  Grade");
		viewgrade.setPreferredSize(new Dimension(150, 35));
		viewgrade.setFont(new Font("Calibri", Font.BOLD, 20));
		panelBodyFooter.add(viewgrade);
		
		JButton register = new JButton("Register for Course");
		register.setFont(new Font("Calibri", Font.BOLD, 20));
		register.setPreferredSize(new Dimension(305, 35));
		register.setForeground(Color.DARK_GRAY);
		panelBodyFooter.add(register);

		JButton Logout = new JButton("Logout");
		Logout.setPreferredSize(new Dimension(150, 35));
		Logout.setFont(new Font("Calibri", Font.BOLD, 20));
		panelBodyFooter.add(Logout);

		
		
		viewgrade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
					try {
						//student grade view
						new StudentGradeView(studentObject);
					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});

		Logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				try {
					new LoginView();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			setVisible(false);
			try {
				new StudentCourseView(studentObject);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		});
		
		PanelFooter panelFooter = new PanelFooter(String.valueOf(Roles.Student));
		add(panelFooter);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {
		
		StudentView studentView = new StudentView(StudentView.studentObject);
	}

}
