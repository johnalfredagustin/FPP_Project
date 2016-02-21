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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ProfessorController;
import model.Roles;
import orm.CourseObject;
import orm.ProfessorObject;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;
import utility.ButtonEditor;
import utility.ButtonRenderer;

@SuppressWarnings("serial")
public class ProfessorGradeView extends JFrame {

	private static ProfessorObject professorObject;

	public ProfessorGradeView(ProfessorObject professorObject) throws SQLException, IOException {

		ProfessorGradeView.professorObject = professorObject;

		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Course Registration System > PROFESSOR GRADE VIEW");
		setLayout(new FlowLayout());

		PanelHeader panelHeader = new PanelHeader(String.valueOf(Roles.Professor));
		add(panelHeader);

		JPanel labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(1000, 50));
		labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JLabel titleLabel = new JLabel("Course Registration System > PROFESSOR GRADE VIEW");
		titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		titleLabel.setForeground(Color.LIGHT_GRAY);

		labelPanel.add(titleLabel);
		add(labelPanel, BorderLayout.NORTH);

		JPanel tablePanel = new JPanel();
		tablePanel.setPreferredSize(new Dimension(1700, 420));
		DefaultTableModel tableModel = new DefaultTableModel() {

			boolean[] canEdit = new boolean[] { false, false, false, true, false, true, false, false, false };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		JTable table = new JTable(tableModel);
		table.setFont(new Font("Serif", Font.ITALIC, 14));
		table.setRowHeight(table.getRowHeight() + 10);

		String[] columnName = new String[] { "First Name", "Last Name", "Course Code", "Grade Number", "Grade Letter",
				"Submit Grade", "Professor ID", "Course ID", "Student ID" };

		for (String string : columnName) {
			tableModel.addColumn(string);
		}

		// Customize table header
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.blue);
		header.setForeground(Color.white);
		header.setFont(new Font("Serif", Font.BOLD, 14));
		header.setPreferredSize(new Dimension(100, 30));

		ProfessorController profController = new ProfessorController();
		ArrayList<ProfessorStudentCourseMetricObject> object = profController
				.getAllStudentCourseDetail(professorObject.getID());

		for (int i = 0; i < object.size(); i++) {

			tableModel.addRow(
					new Object[] { object.get(i).getStudent().getFirstName(), object.get(i).getStudent().getLastName(),
							object.get(i).getCourse().getCourseCode(), object.get(i).getGradeNumber(),
							object.get(i).getGradeLetter(), String.valueOf(""), professorObject.getID(),
							object.get(i).getCourse().getCourseID(), object.get(i).getStudent().getID() });
		}

		table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(5)
				.setCellEditor(new ButtonEditor(object, table, professorObject.getID(), this));

		// table.removeColumn(table.getColumnModel().getColumn(8));
		// table.removeColumn(table.getColumnModel().getColumn(7));
		// table.removeColumn(table.getColumnModel().getColumn(6));

		// hide columns
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setWidth(0);
		// hide columns
		table.getColumnModel().getColumn(7).setMinWidth(0);
		table.getColumnModel().getColumn(7).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setWidth(0);
		// hide columns
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
		table.getColumnModel().getColumn(8).setWidth(0);

		// clear selection
		table.getSelectionModel().removeSelectionInterval(0, object.size());

		
		table.getTableHeader().setReorderingAllowed(false);

		JScrollPane coursePanel = new JScrollPane(table);// enable scroll
															// for table
		coursePanel.setPreferredSize(new Dimension(1300, 490));
		coursePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		tablePanel.add(coursePanel);// add table to a panel
		add(tablePanel); // add panel to frame

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(1300, 100));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		((FlowLayout) buttonPanel.getLayout()).setHgap(30);

		JButton buttonSubmit = new JButton("Submit All");
		buttonSubmit.setPreferredSize(new Dimension(200, 35));
		buttonSubmit.setFont(new Font("Calibri", Font.BOLD, 20));

		JButton buttonBack = new JButton("Back");
		buttonBack.setPreferredSize(new Dimension(200, 35));
		buttonBack.setFont(new Font("Calibri", Font.BOLD, 20));

		JButton buttonLogout = new JButton("Logout");
		buttonLogout.setPreferredSize(new Dimension(200, 35));
		buttonLogout.setFont(new Font("Calibri", Font.BOLD, 20));

		buttonPanel.add(buttonSubmit);
		buttonPanel.add(buttonBack);
		buttonPanel.add(buttonLogout);
		add(buttonPanel);

		PanelFooter panelFooter = new PanelFooter(String.valueOf(Roles.Professor));
		add(panelFooter);

		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new ProfessorView(professorObject);
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

		buttonSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<ProfessorStudentCourseMetricObject> profStuCourseList = new ArrayList<ProfessorStudentCourseMetricObject>();

				for (int i = 0; i < object.size(); i++) {

					int studentID = Integer.parseInt(table.getModel().getValueAt(i, 8).toString());
					int courseID = Integer.parseInt(table.getModel().getValueAt(i, 7).toString());
					int professorID = Integer.parseInt(table.getModel().getValueAt(i, 6).toString());

					StudentObject stObj = new StudentObject(studentID);
					CourseObject crObj = new CourseObject(courseID);
					ProfessorObject prObj = new ProfessorObject(professorID);
					
					ProfessorStudentCourseMetricObject temp = new ProfessorStudentCourseMetricObject(stObj, prObj, crObj);
					
					temp.setGradeNumber(Double.parseDouble(table.getModel().getValueAt(i, 3).toString()));
					
					profStuCourseList.add(temp);
				}
				
				try {
					boolean result = profController.submitAllStudentGrade(profStuCourseList);
					if (result) {
						JOptionPane.showMessageDialog(null, "You have successfully submitted all grades of students.");
						setVisible(false);
						new ProfessorGradeView(professorObject);
					}
				} catch (SQLException | IOException e1) {
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

		ProfessorGradeView profCourseView = new ProfessorGradeView(ProfessorGradeView.professorObject);
	}

}
