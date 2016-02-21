package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.util.Locale;

import javax.swing.*;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import orm.PersonObject;
import orm.StudentObject;

@SuppressWarnings("serial")
public class SignUpView extends JFrame {

	public SignUpView() throws SQLException, IOException {

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

		JLabel lblGender = new JLabel("Gender:");
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

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setPreferredSize(new Dimension(300, 35));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		lblPassword.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setPreferredSize(new Dimension(300, 35));
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		lblConfirmPassword.setForeground(Color.DARK_GRAY);
		panelLeft.add(lblConfirmPassword);

		JComboBox<String> combo = new JComboBox<String>();
		combo.setFont(new Font("Courier New", Font.BOLD, 20));
		combo.setPreferredSize(new Dimension(220, 35));
		combo.setForeground(Color.DARK_GRAY);
		combo.addItem("");
		for (Roles role : Roles.values()) {
			combo.addItem(role.toString());
		}

		panelRight.add(combo);

		JTextField txtID = new JTextField();
		txtID.setPreferredSize(new Dimension(220, 35));
		txtID.setHorizontalAlignment(SwingConstants.LEFT);
		txtID.setFont(new Font("Courier New", Font.BOLD, 20));
		panelRight.add(txtID);

		JTextField txtFirstName = new JTextField();
		txtFirstName.setPreferredSize(new Dimension(400, 35));
		txtFirstName.setHorizontalAlignment(SwingConstants.LEFT);
		txtFirstName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtFirstName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtFirstName);

		JTextField txtLastName = new JTextField();
		txtLastName.setPreferredSize(new Dimension(400, 35));
		txtLastName.setHorizontalAlignment(SwingConstants.LEFT);
		txtLastName.setFont(new Font("Courier New", Font.BOLD, 20));
		txtLastName.setForeground(Color.DARK_GRAY);
		panelRight.add(txtLastName);

		JTextField txtGender = new JTextField();
		txtGender.setPreferredSize(new Dimension(220, 35));
		txtGender.setHorizontalAlignment(SwingConstants.LEFT);
		txtGender.setFont(new Font("Courier New", Font.BOLD, 20));
		txtGender.setForeground(Color.DARK_GRAY);
		panelRight.add(txtGender);

		JTextField txtDOB = new JTextField();
		txtDOB.setPreferredSize(new Dimension(220, 35));
		txtDOB.setHorizontalAlignment(SwingConstants.LEFT);
		txtDOB.setFont(new Font("Courier New", Font.BOLD, 20));
		txtDOB.setForeground(Color.DARK_GRAY);
		panelRight.add(txtDOB);

//		DatePickerSample dp = new DatePickerSample();
//		panelRight.add(dp);
		
		
		// UtilDateModel model = new UtilDateModel();
		// JDatePanelImpl datePanel = new JDatePanelImpl(model);
		// JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		//
		// frame.add(datePicker);

		JTextField txtNationality = new JTextField();
		txtNationality.setPreferredSize(new Dimension(400, 35));
		txtNationality.setHorizontalAlignment(SwingConstants.LEFT);
		txtNationality.setFont(new Font("Courier New", Font.BOLD, 20));
		txtNationality.setForeground(Color.DARK_GRAY);
		panelRight.add(txtNationality);

		JPasswordField txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(305, 35));
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		txtPassword.setForeground(Color.DARK_GRAY);
		txtPassword.setEchoChar('*');
		panelRight.add(txtPassword);

		JPasswordField txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setPreferredSize(new Dimension(305, 35));
		txtConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtConfirmPassword.setFont(new Font("Courier New", Font.BOLD, 20));
		txtConfirmPassword.setForeground(Color.DARK_GRAY);
		txtConfirmPassword.setEchoChar('*');
		panelRight.add(txtConfirmPassword);

		JButton btnClear = new JButton("Clear");
		btnClear.setPreferredSize(new Dimension(150, 35));
		btnClear.setFont(new Font("Calibri", Font.BOLD, 20));
		panelRight.add(btnClear);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setPreferredSize(new Dimension(150, 35));
		btnSubmit.setFont(new Font("Calibri", Font.BOLD, 20));
		panelRight.add(btnSubmit);

		JButton btnSignIn = new JButton("Sign in w/ Existing Account");
		btnSignIn.setFont(new Font("Calibri", Font.BOLD, 20));
		btnSignIn.setPreferredSize(new Dimension(305, 35));
		btnSignIn.setForeground(Color.DARK_GRAY);
		panelRight.add(btnSignIn);

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
				combo.setSelectedIndex(0);
				txtID.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				txtGender.setText("");
				txtNationality.setText("");
				txtDOB.setText("");
				txtPassword.setText("");
				txtConfirmPassword.setText("");

			}
		});

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int warningCount = 0;
				StringBuilder sb = new StringBuilder();
				if (txtPassword.getText().compareTo(txtConfirmPassword.getText()) != 0) {
					sb.append("\nYour password doesn't match! Please enter the same password in the 'Confirm Password' field.");
					warningCount++;
				}
//				if (rootPaneCheckingEnabled) {
//					
//				}

				if (warningCount > 0) {
					JOptionPane.showMessageDialog(null, sb.toString());
				}

//				 PersonObject personObj = new StudentObject(txtFirstName, txtLastName, txtDOB)
//				 StudentObject(txtFirstName.getText(), txtLastName.getText(),
				// txtDOB.getText(), txtStudentID.getText());

				// StudentController studentController = new
				// StudentController();
				// studentController.signUp(personObj);

			}
		});

		PanelFooter panelFooter = new PanelFooter();
		add(panelFooter);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {
		SignUpView studentView = new SignUpView();
	}

}



class DatePickerSample extends Application {
	 
    private Stage stage;
    private DatePicker checkInDatePicker;
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("DatePickerSample ");
        initUI();
        stage.show();
    }
 
    private void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 200, 100);
        stage.setScene(scene);

        checkInDatePicker = new DatePicker();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(checkInDatePicker, 0, 1);
        vbox.getChildren().add(gridPane);
    }
}