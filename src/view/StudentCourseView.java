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

import controller.CourseController;
import orm.CourseObject;
//import orm.StudentObject;

public class StudentCourseView extends JFrame {

	public StudentCourseView() throws SQLException {
		try {
			setSize(1000, 1000);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());

			JPanel labelPanel = new JPanel();
			labelPanel.setPreferredSize(new Dimension(250, 200));
			labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

			JLabel titleLabel = new JLabel("ALL AVAILABLE COURSES");
			titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
			titleLabel.setForeground(Color.LIGHT_GRAY);

			labelPanel.add(titleLabel);
			add(labelPanel, BorderLayout.NORTH);

			add(labelPanel, BorderLayout.NORTH);

			String tableHeader[] = { "Course Code", "Course Name", "Course Description" };

			CourseController courseCtl = new CourseController();
			ArrayList<CourseObject> courseObject = courseCtl.getAllCourse();
			

			// Table model
			DefaultTableModel tableModel = new DefaultTableModel() {

				boolean[] canEdit = new boolean[] { false, false, false };

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};

			for (int j = 0; j < 3; j++) {
				tableModel.addColumn(tableHeader[j]);
			}

			String[] tableRow = new String[3];
			for (int i = 0; i < courseObject.size(); i++) {
				tableRow[0] = courseObject.get(i).getCourseCode();
				tableRow[1] = courseObject.get(i).getCourseName();
				tableRow[2] = courseObject.get(i).getCourseDesc();
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

			coursePanel.setPreferredSize(new Dimension(1000, 200));
			coursePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			JPanel tablePanel = new JPanel();
			tablePanel.add(coursePanel, BorderLayout.NORTH);// add table to a
															// panel

			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(100, 50));
			buttonPanel.setLayout(new FlowLayout());
			((FlowLayout) buttonPanel.getLayout()).setHgap(30);

			JButton buttonSubmit = new JButton("Submit");
			JButton buttonBack = new JButton("Back");
			buttonSubmit.setPreferredSize(new Dimension(150, 50));
			buttonBack.setPreferredSize(new Dimension(150, 50));
			buttonPanel.add(buttonSubmit);
			buttonPanel.add(buttonBack);

			
			JPanel panelFooter = new JPanel();
			panelFooter.setPreferredSize(new Dimension(100, 20));
			add(panelFooter);
			
			add(buttonPanel, BorderLayout.SOUTH);
			add(tablePanel, BorderLayout.CENTER); // add panel to frame
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);

			buttonBack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					 setVisible(false);
					 new StudentView();

				}
			});

			buttonSubmit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (courseTable.getSelectedRow() != -1) {
						System.out.println(courseTable.getValueAt(courseTable.getSelectedRow(), 0));
					}else{
						JOptionPane.showMessageDialog(null, "Please select a course");
					}
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		StudentCourseView studentCourseView = new StudentCourseView();
	}

}
