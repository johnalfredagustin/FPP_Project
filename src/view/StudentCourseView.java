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
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controller.StudentController;
import orm.CourseObject;
//import orm.StudentObject;
import orm.ProfessorStudentCourseMetricObject;
import orm.StudentObject;

public class StudentCourseView extends JFrame {

	public StudentCourseView() throws SQLException {
		try {
			setSize(1000, 1000);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setTitle("Course Registration System > ALL AVAILABLE COURSES");
			setLayout(new FlowLayout(FlowLayout.LEFT));

			PanelHeader panelHeader = new PanelHeader();
			add(panelHeader);

			JPanel panelTopBorder = new JPanel();
			panelTopBorder.setPreferredSize(new Dimension(1500, 50));
			add(panelTopBorder);

			JLabel lblSysName = new JLabel("ALL AVAILABLE COURSES");
			lblSysName.setFont(new Font("Courier New", Font.BOLD, 40));
			lblSysName.setForeground(Color.LIGHT_GRAY);
			panelTopBorder.add(lblSysName);

			String tableHeader[] = { "CourseID","Course Code", "Course Name", "Course Description", "Professor", "ProfessorID" };

			StudentController studentController = new StudentController();
			ArrayList<ProfessorStudentCourseMetricObject> courseObject = studentController.getAllCourse(984946);

			// Table model
			DefaultTableModel tableModel = new DefaultTableModel() {

				boolean[] canEdit = new boolean[] { false,false, false, false, false, false };

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};
			JTable courseTable = new JTable(tableModel);
			for (int j = 0; j < 6; j++) {
				tableModel.addColumn(tableHeader[j]);
			}

			for (int i = 0; i < courseObject.size(); i++) {
				tableModel.addRow(new Object[] { 
						courseObject.get(i).getCourse().getCourseID(),
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
			tablePanel.add(coursePanel);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(800, 50));
			buttonPanel.setLayout(new FlowLayout());
			// ((FlowLayout) buttonPanel.getLayout()).setHgap(30);

			JButton buttonSubmit = new JButton("Submit");
			JButton buttonBack = new JButton("Back");
			buttonSubmit.setPreferredSize(new Dimension(150, 40));
			buttonBack.setPreferredSize(new Dimension(150, 40));
			buttonPanel.add(buttonSubmit);
			buttonPanel.add(buttonBack);

			add(tablePanel);
			add(buttonPanel);
			PanelFooter panelFooter = new PanelFooter();
			add(panelFooter);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);

			buttonBack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// For testing first
					StudentObject studentObject = new StudentObject("FirstName", "LastName",
							new GregorianCalendar(1990, 1, 1));
					studentObject.setID(984946);
					studentObject.setGender("Male");
					studentObject.setNationality("Vietnamese");

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
					// =================================
					// new StudentView();
					// setVisible(false);

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
							JOptionPane.showMessageDialog(null, row);
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

	public static void main(String[] args) throws SQLException {
		StudentCourseView studentCourseView = new StudentCourseView();
	}

}
