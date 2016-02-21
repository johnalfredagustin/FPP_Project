package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.*;

import controller.ProfessorController;
import controller.StudentController;
import model.Gender;
import model.Roles;
import orm.ProfessorObject;
import orm.StudentObject;
import utility.DatePickerUtility;
import utility.ObservingTextFieldUtility;

@SuppressWarnings("serial")
public class SignUpView extends JFrame {

	private static String role = "";

	@SuppressWarnings("static-access")
	public SignUpView(String role) throws SQLException, IOException {

		this.role = role;

		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Course Registration System > SIGN UP");
		setLayout(new FlowLayout(FlowLayout.LEFT));

		PanelHeader panelHeader = new PanelHeader(role);
		add(panelHeader);

		JLabel lblHeader = new JLabel("Course Registration System > SIGNUP FOR NEW ACCOUNT");
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
		lblSpace.setPreferredSize(new Dimension(300, 30));
		lblSpace.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpace.setFont(new Font("Courier New", Font.BOLD, 20));
		lblSpace.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblSpace);

		JLabel lblStudentID = new JLabel("ID:");
		lblStudentID.setPreferredSize(new Dimension(300, 30));
		lblStudentID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentID.setFont(new Font("Courier New", Font.BOLD, 20));
		lblStudentID.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblStudentID);

		JLabel lblSSN = new JLabel("SSN:");
		lblSSN.setPreferredSize(new Dimension(300, 30));
		lblSSN.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSSN.setFont(new Font("Courier New", Font.BOLD, 20));
		lblSSN.setForeground(Color.DARK_GRAY);
		if (role.equals(String.valueOf(Roles.Professor))) {
			lblSSN.setVisible(true);
		} else {
			lblSSN.setVisible(false);
		}
		panelLeft.add(lblSSN);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setPreferredSize(new Dimension(300, 30));
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblFirstName.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setPreferredSize(new Dimension(300, 30));
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblLastName.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblLastName);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setPreferredSize(new Dimension(300, 30));
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Courier New", Font.BOLD, 20));
		lblGender.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblGender);

		JLabel lblDOB = new JLabel("Date Of Birth:");
		lblDOB.setPreferredSize(new Dimension(300, 30));
		lblDOB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDOB.setFont(new Font("Courier New", Font.BOLD, 20));
		lblDOB.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblDOB);

		JLabel lblEntry = new JLabel("Entry Date:");
		lblEntry.setPreferredSize(new Dimension(300, 30));
		lblEntry.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEntry.setFont(new Font("Courier New", Font.BOLD, 20));
		lblEntry.setForeground(Color.DARK_GRAY);
		if (role.equals(String.valueOf(Roles.Student))) {
			lblEntry.setVisible(true);
		} else {
			lblEntry.setVisible(false);
		}
		panelLeft.add(lblEntry);

		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setPreferredSize(new Dimension(300, 30));
		lblNationality.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		lblNationality.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblNationality);

		JLabel lblEmail = new JLabel("Email Address:");
		lblEmail.setPreferredSize(new Dimension(300, 30));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Courier New", Font.BOLD, 20));
		lblEmail.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblEmail);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setPreferredSize(new Dimension(300, 30));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		lblPassword.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setPreferredSize(new Dimension(300, 30));
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		lblConfirmPassword.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblConfirmPassword);

		JComboBox<String> comboRole = new JComboBox<String>();
		comboRole.setFont(new Font("Courier New", Font.BOLD, 20));
		comboRole.setPreferredSize(new Dimension(220, 30));
		comboRole.setForeground(Color.DARK_GRAY);
		comboRole.addItem("");
		for (Roles role1 : Roles.values()) {
			comboRole.addItem(role1.toString());
		}
		comboRole.setSelectedItem(role);

		panelRight.add(comboRole);

		JTextField txtID = new JTextField();
		txtID.setPreferredSize(new Dimension(220, 30));
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setFont(new Font("Courier New", Font.BOLD, 20));
		panelRight.add(txtID);

		JTextField txtSSN = new JTextField();
		txtSSN.setPreferredSize(new Dimension(220, 30));
		txtSSN.setHorizontalAlignment(SwingConstants.LEFT);
		txtSSN.setFont(new Font("Courier New", Font.BOLD, 20));
		if (role.equals(String.valueOf(Roles.Professor))) {
			txtSSN.setVisible(true);
		} else {
			txtSSN.setVisible(false);
		}
		panelRight.add(txtSSN);

		JTextField txtFirstName = new JTextField();
		txtFirstName.setPreferredSize(new Dimension(400, 30));
		txtFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		txtFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtFirstName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtFirstName);

		JTextField txtLastName = new JTextField();
		txtLastName.setPreferredSize(new Dimension(400, 30));
		txtLastName.setHorizontalAlignment(SwingConstants.LEFT);
		txtLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtLastName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtLastName);

		JComboBox<String> comboGender = new JComboBox<String>();
		comboGender.setFont(new Font("Courier New", Font.BOLD, 20));
		comboGender.setPreferredSize(new Dimension(220, 30));
		comboGender.setForeground(Color.DARK_GRAY);
		comboGender.addItem("");
		for (Gender gender : Gender.values()) {
			comboGender.addItem(gender.toString());
		}

		panelRight.add(comboGender);

		ObservingTextFieldUtility txtDOB = new ObservingTextFieldUtility();
		txtDOB.setPreferredSize(new Dimension(220, 30));
		txtDOB.setHorizontalAlignment(SwingConstants.LEFT);
		txtDOB.setFont(new Font("Courier New", Font.BOLD, 20));
		txtDOB.setForeground(Color.DARK_GRAY);
		panelRight.add(txtDOB);

		JButton btnDOB = new JButton("Pick Date");
		btnDOB.setPreferredSize(new Dimension(115, 30));
		btnDOB.setHorizontalAlignment(SwingConstants.CENTER);
		btnDOB.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDOB.setForeground(Color.DARK_GRAY);
		panelRight.add(btnDOB);

		ObservingTextFieldUtility txtEntry = new ObservingTextFieldUtility();
		txtEntry.setPreferredSize(new Dimension(220, 30));
		txtEntry.setHorizontalAlignment(SwingConstants.LEFT);
		txtEntry.setFont(new Font("Courier New", Font.BOLD, 20));
		txtEntry.setForeground(Color.DARK_GRAY);
		if (role.equals(String.valueOf(Roles.Student))) {
			txtEntry.setVisible(true);
		} else {
			txtEntry.setVisible(false);
		}
		panelRight.add(txtEntry);

		JButton btnEntry = new JButton("Pick Date");
		btnEntry.setPreferredSize(new Dimension(115, 30));
		btnEntry.setHorizontalAlignment(SwingConstants.CENTER);
		btnEntry.setFont(new Font("Calibri", Font.BOLD, 20));
		btnEntry.setForeground(Color.DARK_GRAY);
		if (role.equals(String.valueOf(Roles.Student))) {
			btnEntry.setVisible(true);
		} else {
			btnEntry.setVisible(false);
		}
		panelRight.add(btnEntry);

		btnEntry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String lang = null;
				final Locale locale = getLocale(lang);
				DatePickerUtility dp = new DatePickerUtility(txtEntry, locale);

				Date selectedDate = dp.parseDate(txtEntry.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(txtEntry);
			}
		});

		btnDOB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String lang = null;
				final Locale locale = getLocale(lang);
				DatePickerUtility dp = new DatePickerUtility(txtDOB, locale);

				Date selectedDate = dp.parseDate(txtDOB.getText());
				dp.setSelectedDate(selectedDate);
				dp.start(txtDOB);
			}
		});

		JTextField txtNationality = new JTextField();
		txtNationality.setPreferredSize(new Dimension(400, 30));
		txtNationality.setHorizontalAlignment(SwingConstants.LEFT);
		txtNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		txtNationality.setForeground(Color.DARK_GRAY);
		panelRight.add(txtNationality);

		JTextField txtEmail = new JTextField();
		txtEmail.setPreferredSize(new Dimension(305, 30));
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setFont(new Font("Courier New", Font.BOLD, 20));
		txtEmail.setForeground(Color.DARK_GRAY);
		panelRight.add(txtEmail);

		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(305, 30));
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		txtPassword.setForeground(Color.DARK_GRAY);
		txtPassword.setEchoChar('*');
		panelRight.add(txtPassword);

		JPasswordField txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setPreferredSize(new Dimension(305, 30));
		txtConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtConfirmPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		txtConfirmPassword.setForeground(Color.DARK_GRAY);
		txtConfirmPassword.setEchoChar('*');
		panelRight.add(txtConfirmPassword);

		JButton btnClear = new JButton("Clear");
		btnClear.setPreferredSize(new Dimension(150, 30));
		btnClear.setFont(new Font("Calibri", Font.BOLD, 20));
		panelRight.add(btnClear);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setPreferredSize(new Dimension(150, 30));
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 20));
		panelRight.add(btnSubmit);

		JButton btnSignIn = new JButton("Sign in w/ Existing Account");
		btnSignIn.setFont(new Font("Calibri", Font.BOLD, 20));
		btnSignIn.setPreferredSize(new Dimension(305, 30));
		btnSignIn.setForeground(Color.DARK_GRAY);
		panelRight.add(btnSignIn);
		//
		// JButton btnBack = new JButton("Back");
		// btnBack.setFont(new Font("Calibri", Font.BOLD, 20));
		// btnBack.setPreferredSize(new Dimension(305, 30));
		// btnBack.setForeground(Color.DARK_GRAY);
		// panelRight.add(btnBack);

		// btnBack.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// setVisible(false);
		// try {
		// new SignUpView();
		// } catch (SQLException | IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }
		// });

		btnSignIn.addActionListener(new ActionListener() {

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

		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				comboGender.setSelectedIndex(0);
				txtEntry.setText("");
				txtSSN.setText("");
				txtID.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				comboGender.setSelectedIndex(0);
				txtNationality.setText("");
				txtDOB.setText("");
				txtPassword.setText("");
				txtConfirmPassword.setText("");
				txtEmail.setText("");
			}
		});

		comboRole.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new SignUpView(comboRole.getSelectedItem().toString());
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		/**
		 * TEST DATA
		 */

		// txtPassword.setText("newpassword");
		// txtConfirmPassword.setText("newpassword");
		// txtFirstName.setText("Darren");
		// txtLastName.setText("Mc Queen");
		// txtDOB.setText("1988-12-11");
		// txtID.setText("20002");
		// txtNationality.setText("French");
		// txtSSN.setText("8545889");
		// txtEmail.setText("darren.mc.queen@mum.edu");
		// comboGender.setSelectedItem("Male");
		// comboRole.setSelectedItem("Professor");

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int warningCount = 0;
				StringBuilder sbWarnings = new StringBuilder();
				boolean isSuccessful = false;
				ProfessorObject newProfObj = null;
				StudentObject newStudentObj = null;

				String password = new String(txtPassword.getPassword());
				String confirmPassword = new String(txtConfirmPassword.getPassword());

				if (!password.equals(confirmPassword)) {
					sbWarnings.append(
							"\nYour password doesn't match! Please enter the same password in the 'Confirm Password' field.");
					warningCount++;
				}

				if (txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtDOB.getText().equals("")
						|| txtID.getText().equals("") || txtNationality.getText().equals("")
						|| comboGender.getSelectedItem().toString().equals("")) {
					sbWarnings.append("\nAll fields are required! Please fill up all the fields in the form.");
					warningCount++;
				}

				if (warningCount > 0) {
					JOptionPane.showMessageDialog(null, sbWarnings.toString());
				}
				comboRole.getSelectedItem().toString();

				if (comboRole.getSelectedItem().toString().equals(Roles.Professor.toString())) {

					String strDOB = txtDOB.getText();

					int yearDOB = Integer.parseInt(strDOB.substring(0, 4));
					int monthDOB = Integer.parseInt(strDOB.substring(5, 7)) - 1;
					int dayDOB = Integer.parseInt(strDOB.substring(8));

					newProfObj = new ProfessorObject(txtFirstName.getText(), txtLastName.getText(),
							new GregorianCalendar(yearDOB, monthDOB, dayDOB));
					newProfObj.setGender(comboGender.getSelectedItem().toString());
					newProfObj.setNationality(txtNationality.getText());
					newProfObj.setPassword(password);
					newProfObj.setEmail(txtEmail.getText());
					newProfObj.setID(Integer.parseInt(txtID.getText()));
					newProfObj.setSSN(txtSSN.getText());

					ProfessorController profController = new ProfessorController();

					try {
						isSuccessful = profController.signUp(newProfObj);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					if (isSuccessful) {
						JOptionPane.showMessageDialog(null,
								"Welcome! " + txtFirstName.getText() + " You registration is successful!");
						setVisible(false);
						try {
							new ProfessorView(newProfObj);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "You registration is not successful! Please try again!");
					}

				} else if (comboRole.getSelectedItem().toString().equals(Roles.Student.toString())) {

					String strDOB = txtDOB.getText();

					int yearDOB = Integer.parseInt(strDOB.substring(0, 4));
					int monthDOB = Integer.parseInt(strDOB.substring(5, 7)) - 1;
					int dayDOB = Integer.parseInt(strDOB.substring(8));

					String strEntry = txtEntry.getText();

					int yearEntry = Integer.parseInt(strEntry.substring(0, 4));
					int monthEntry = Integer.parseInt(strEntry.substring(5, 7)) - 1;
					int dayEntry = Integer.parseInt(strEntry.substring(8));
					
					newStudentObj = new StudentObject(txtFirstName.getText(), txtLastName.getText(),
							new GregorianCalendar(yearDOB, monthDOB, dayDOB));
					newStudentObj.setGender(comboGender.getSelectedItem().toString());
					newStudentObj.setNationality(txtNationality.getText());
					newStudentObj.setPassword(password);
					newStudentObj.setEmail(txtEmail.getText());
					newStudentObj.setID(Integer.parseInt(txtID.getText()));
					
					newStudentObj.setEntryDate(new GregorianCalendar(yearEntry, monthEntry, dayEntry));

					StudentController studentController = new StudentController();

					try {
						isSuccessful = studentController.signUp(newStudentObj);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					if (isSuccessful) {
						JOptionPane.showMessageDialog(null,
								"Welcome! " + txtFirstName.getText() + " You registration is successful!");
						setVisible(false);
						try {
							new StudentView(newStudentObj);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "You registration is not successful! Please try again!");
					}

				}

				
			}
		});

		PanelFooter panelFooter = new PanelFooter(role);
		add(panelFooter);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	private Locale getLocale(String loc) {
		if (loc != null && loc.length() > 0)
			return new Locale(loc);
		else
			return Locale.US;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {

		SignUpView studentView = new SignUpView(SignUpView.role);

	}
}
