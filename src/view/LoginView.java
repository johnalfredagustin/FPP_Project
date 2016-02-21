package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import com.apple.dnssd.TXTRecord;

import controller.LoginController;
import controller.StudentController;
import orm.StudentObject;

import javax.imageio.*;

public class LoginView extends JFrame {

	public LoginView() throws IOException {

		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel panelTopBorder = new JPanel();
		panelTopBorder.setLayout(new BorderLayout());
		panelTopBorder.setPreferredSize(new Dimension(1500, 200));
		add(panelTopBorder);

		JLabel lblSysName = new JLabel("Course Registration System");
		lblSysName.setFont(new Font("Courier New", Font.BOLD, 40));
		lblSysName.setForeground(Color.LIGHT_GRAY);
		panelTopBorder.add(lblSysName, BorderLayout.EAST);

		PanelHeader panelHeader = new PanelHeader();
		add(panelHeader);

		JPanel panelLeft = new JPanel();
		panelLeft.setPreferredSize(new Dimension(700, 400));
		panelLeft.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(panelLeft);

		JPanel panelCenter = new JPanel();
		panelCenter.setPreferredSize(new Dimension(300, 400));
		panelCenter.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(panelCenter);

		JPanel panelRight = new JPanel();
		panelRight.setPreferredSize(new Dimension(250, 400));
		panelRight.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(panelRight);

		JLabel lblSpace = new JLabel();
		lblSpace.setPreferredSize(new Dimension(300, 20));
		lblSpace.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpace.setFont(new Font("Courier New", Font.BOLD, 20));
		panelCenter.add(lblSpace);

		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setPreferredSize(new Dimension(300, 50));
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Courier New", Font.BOLD, 20));
		lblUserName.setForeground(Color.DARK_GRAY);
		panelCenter.add(lblUserName);

		JLabel lblUserPassword = new JLabel("Password:");
		lblUserPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		lblUserPassword.setForeground(Color.DARK_GRAY);
		panelCenter.add(lblUserPassword);

		JComboBox<String> combo = new JComboBox<String>();
		combo.setFont(new Font("Courier New", Font.BOLD, 20));
		combo.setPreferredSize(new Dimension(200, 30));
		combo.setForeground(Color.DARK_GRAY);
		for (Roles role : Roles.values()) {
			combo.addItem(role.toString());
		}

		panelRight.add(combo);

		JTextField txUserName = new JTextField("984946");
		txUserName.setFont(new Font("Courier New", Font.BOLD, 20));
		txUserName.setForeground(Color.DARK_GRAY);
		txUserName.setPreferredSize(new Dimension(200, 30));
		panelRight.add(txUserName);

		JPasswordField pfPassword = new JPasswordField("Password");
		pfPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		pfPassword.setForeground(Color.DARK_GRAY);
		pfPassword.setPreferredSize(new Dimension(200, 30));
		pfPassword.setEchoChar('*');
		panelRight.add(pfPassword);

		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Courier New", Font.BOLD, 20));
		btnClear.setForeground(Color.DARK_GRAY);
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Courier New", Font.BOLD, 20));
		btnLogin.setForeground(Color.DARK_GRAY);
		panelRight.add(btnClear);
		panelRight.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginController loginController = new LoginController();
				try {
					
					try {
					     Integer.parseInt(txUserName.getText());
					}
					catch (NumberFormatException e) {
					     JOptionPane.showMessageDialog(btnLogin, "Username is not in valid format. Please use your id.");
					}
					
					System.out.println(combo.getSelectedItem());
					boolean isValidLogin = loginController.isValidLogin(txUserName.getText(), pfPassword.getText(), combo.getSelectedItem().toString());
					
					
					if (isValidLogin && combo.getSelectedItem().toString() == "Student") {
						StudentController studentController = new StudentController();
						StudentObject studentObject = studentController.getStudentDetail(Integer.parseInt(txUserName.getText()));
						
						setVisible(false);
						StudentView studentView = new StudentView(studentObject);
						studentView.setVisible(true);
					} else if (isValidLogin && combo.getSelectedItem().toString() == "Professor") {
						setVisible(false);
						ProfessorView profView = new ProfessorView();
						profView.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(btnLogin, "Username or password is incorect!");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				txUserName.setText("");
				pfPassword.setText("");
			}
		});

		pack();
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		LoginView loginView = new LoginView();
	}
}
