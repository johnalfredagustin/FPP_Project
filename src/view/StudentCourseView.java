package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

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
			
			

			String tableHeader[] = { "Course Code", "Course Name", "Course Description", "Professor" };

			StudentController courseCtl = new StudentController();
			ArrayList<ProfessorStudentCourseMetricObject> courseObject = courseCtl.getAllCourse(984946);

			// Table model
			DefaultTableModel tableModel = new DefaultTableModel() {

				boolean[] canEdit = new boolean[] { false, false, false,false };

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};

			for (int j = 0; j < 4; j++) {
				tableModel.addColumn(tableHeader[j]);
			}

			String[] tableRow = new String[4];
			for (int i = 0; i < courseObject.size(); i++) {
				tableRow[0] = courseObject.get(i).getCourse().getCourseCode();
				tableRow[1] = courseObject.get(i).getCourse().getCourseName();
				tableRow[2] = courseObject.get(i).getCourse().getCourseDesc();
				tableRow[3] = courseObject.get(i).getProfessor().getFirstName() + " "
						+ courseObject.get(i).getProfessor().getLastName();
				tableModel.addRow(tableRow);
			}

			JTable courseTable = new JTable(tableModel);

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

			// new ButtonColumn(courseTable);
			JScrollPane coursePanel = new JScrollPane(courseTable);

			coursePanel.setPreferredSize(new Dimension(980, 200));
			coursePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			JPanel tablePanel = new JPanel();
			tablePanel.add(coursePanel);// add table to a
															// panel

			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(800, 50));
			buttonPanel.setLayout(new FlowLayout());
//			((FlowLayout) buttonPanel.getLayout()).setHgap(30);

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
					// new StudentView();
					// setVisible(false);

				}
			});

			buttonSubmit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (courseTable.getSelectedRow() != -1) {
						String courseCode = (String) courseTable.getValueAt(courseTable.getSelectedRow(), 0);
						int studentID = 984946;
						try {
							courseCtl.saveCourse(studentID, courseCode);
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
