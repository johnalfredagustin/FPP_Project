package view;

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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controller.StudentController;
import model.Roles;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;

@SuppressWarnings("serial")
public class StudentCourseView extends JFrame {

	private static StudentObject studentObject;

	public StudentCourseView(StudentObject studentObject) throws SQLException {
		try {

			StudentCourseView.studentObject = studentObject;

			setSize(1000, 1000);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setTitle("Course Registration System > ALL AVAILABLE COURSES");
			setLayout(new FlowLayout(FlowLayout.LEFT));

			PanelHeader panelHeader = new PanelHeader(String.valueOf(Roles.Student));
			add(panelHeader);

			JPanel panelTopBorder = new JPanel();
			panelTopBorder.setPreferredSize(new Dimension(1500, 50));
			add(panelTopBorder);

			JLabel lblSysName = new JLabel("Course Registration System > ALL AVAILABLE COURSES");
			lblSysName.setPreferredSize(new Dimension(2000, 40));
			lblSysName.setHorizontalAlignment(SwingConstants.CENTER);
			lblSysName.setFont(new Font("Courier New", Font.BOLD, 30));
			lblSysName.setForeground(Color.LIGHT_GRAY);
			panelTopBorder.add(lblSysName);

			String tableHeader[] = { "CourseID", "Course Code", "Course Name", "Course Description", "Professor",
					"ProfessorID" };

			StudentController studentController = new StudentController();
			ArrayList<ProfessorStudentCourseMetricObject> courseObject = studentController.getAllCourse(studentObject.getID());

			// Table model
			DefaultTableModel tableModel = new DefaultTableModel() {

				boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};
			JTable courseTable = new JTable(tableModel);
			for (int j = 0; j < tableHeader.length; j++) {
				tableModel.addColumn(tableHeader[j]);
			}

			for (int i = 0; i < courseObject.size(); i++) {
				tableModel.addRow(new Object[] { courseObject.get(i).getCourse().getCourseID(),
						courseObject.get(i).getCourse().getCourseCode(),
						courseObject.get(i).getCourse().getCourseName(),
						courseObject.get(i).getCourse().getCourseDesc(),
						courseObject.get(i).getProfessor().getFirstName() + " "
								+ courseObject.get(i).getProfessor().getLastName(),
						courseObject.get(i).getProfessor().getID() });

			}
			courseTable.removeColumn(courseTable.getColumnModel().getColumn(5));
			courseTable.removeColumn(courseTable.getColumnModel().getColumn(0));

			courseTable.setFont(new Font("Serif", Font.ITALIC, 14));
			courseTable.setRowHeight(courseTable.getRowHeight() + 10);

			// Customize table header
			JTableHeader header = courseTable.getTableHeader();
			header.setBackground(Color.blue);
			header.setForeground(Color.white);
			header.setFont(new Font("Serif", Font.BOLD, 14));
			header.setPreferredSize(new Dimension(100, 30));
			header.setResizingAllowed(false);

			// Align all cells to center
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			courseTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			courseTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			courseTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

			JScrollPane coursePanel = new JScrollPane(courseTable);

			coursePanel.setPreferredSize(new Dimension(980, 200));
			coursePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			JPanel tablePanel = new JPanel();
			tablePanel.setPreferredSize(new Dimension(1800, 480));
			tablePanel.add(coursePanel);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(1800, 50));
			buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			((FlowLayout) buttonPanel.getLayout()).setHgap(30);

			JButton buttonSubmit = new JButton("Submit");
			buttonSubmit.setFont(new Font("Calibri", Font.BOLD, 20));
			buttonSubmit.setPreferredSize(new Dimension(305, 35));
			
			JButton buttonBack = new JButton("Back");
			buttonBack.setFont(new Font("Calibri", Font.BOLD, 20));
			buttonBack.setPreferredSize(new Dimension(305, 35));
			
			JButton buttonLogout = new JButton("Logout");
			buttonLogout.setFont(new Font("Calibri", Font.BOLD, 20));
			buttonLogout.setPreferredSize(new Dimension(305, 35));
			
			buttonPanel.add(buttonSubmit);
			buttonPanel.add(buttonBack);
			buttonPanel.add(buttonLogout);
			
			
			add(tablePanel);
			add(buttonPanel);
			PanelFooter panelFooter = new PanelFooter(String.valueOf(Roles.Student));
			add(panelFooter);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);

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
			
			buttonBack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						new StudentView(studentObject);
						setVisible(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			buttonSubmit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (courseTable.getSelectedRow() != -1) {
						int courseID = (int) courseTable.getModel().getValueAt(courseTable.getSelectedRow(), 0);
						int professorID = (int) courseTable.getModel().getValueAt(courseTable.getSelectedRow(), 5);
						int studentID = 984946;
						try {
							int row = studentController.saveCourse(studentID, courseID, professorID);
							if (row != 0) {
								JOptionPane.showMessageDialog(null, "You have registered Successfully to "
										+ courseTable.getModel().getValueAt(courseTable.getSelectedRow(), 2) + ".");
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "Unable to register. ");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Please select a course");
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {
		StudentCourseView studentCourseView = new StudentCourseView(StudentCourseView.studentObject);
	}

}
