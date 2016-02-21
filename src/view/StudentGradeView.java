package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.StudentController;
import model.Roles;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;

@SuppressWarnings("serial")
public class StudentGradeView extends JFrame {

	private static StudentObject studentObject;

	public StudentGradeView(StudentObject studentObject) throws SQLException, IOException {

		StudentGradeView.studentObject = studentObject;

		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Course Registration System > STUDENT GRADE");
		setLayout(new FlowLayout());

		PanelHeader panelHeader = new PanelHeader(String.valueOf(Roles.Student));
		add(panelHeader);

		JPanel labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(1000, 80));
		labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JLabel titleLabel = new JLabel("Course Registration System > STUDENT GRADE");
		titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		titleLabel.setForeground(Color.LIGHT_GRAY);

		labelPanel.add(titleLabel);
		add(labelPanel, BorderLayout.NORTH);

		JPanel tablePanel = new JPanel();
		DefaultTableModel tableModel = new DefaultTableModel() {

			boolean[] canEdit = new boolean[] { false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		JTable table = new JTable(tableModel);
		table.setFont(new Font("Serif", Font.ITALIC, 14));
		table.setRowHeight(table.getRowHeight() + 10);

		tableModel.addColumn("Cuourse Name");
		tableModel.addColumn("Course Code");
		tableModel.addColumn("Grade Number");
		tableModel.addColumn("Grade Letter");

		// Customize table header
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.blue);
		header.setForeground(Color.white);
		header.setFont(new Font("Serif", Font.BOLD, 14));
		header.setPreferredSize(new Dimension(100, 30));

		StudentController studentcontroler = new StudentController();
		ArrayList<ProfessorStudentCourseMetricObject> object = studentcontroler.getAllgrades(studentObject.getID());

		for (int i = 0; i < object.size(); i++) {
			tableModel.addRow(new Object[] { object.get(i).getCourse().getCourseName(),
					object.get(i).getCourse().getCourseCode(), object.get(i).getGradeNumber() ,
					object.get(i).getGradeLetter()});
		}

		table.getTableHeader().setReorderingAllowed(false);

		table.setEnabled(false);
		JScrollPane coursePanel = new JScrollPane(table); // enable scroll for
															// table
		coursePanel.setPreferredSize(new Dimension(1300, 380));
		coursePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		tablePanel.add(coursePanel);	// add table to a panel
		add(tablePanel);	// add panel to frame

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(1300, 100));
		buttonPanel.setLayout(new FlowLayout());
		((FlowLayout) buttonPanel.getLayout()).setHgap(30);

		JButton buttonLogout = new JButton("Logout");
		buttonLogout.setFont(new Font("Calibri", Font.BOLD, 20));
		buttonLogout.setPreferredSize(new Dimension(305, 35));
		
		JButton buttonBack = new JButton("Back");
		buttonBack.setFont(new Font("Calibri", Font.BOLD, 20));
		buttonBack.setPreferredSize(new Dimension(305, 35));
	
		
		buttonPanel.add(buttonBack);
		buttonPanel.add(buttonLogout);
		add(buttonPanel);

		PanelFooter panelFooter = new PanelFooter(String.valueOf(Roles.Student));
		add(panelFooter);

		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new StudentView(studentObject);

				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		buttonLogout.addActionListener(new ActionListener() {

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

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {

		StudentGradeView studGradeView = new StudentGradeView(StudentGradeView.studentObject);
	}

}
