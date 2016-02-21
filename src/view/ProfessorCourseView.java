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
import controller.ProfessorController;
import model.Roles;
import orm.CourseObject;
import orm.ProfessorObject;

@SuppressWarnings("serial")
public class ProfessorCourseView extends JFrame {

	private static ProfessorObject professorObject;

	@SuppressWarnings("static-access")
	public ProfessorCourseView(ProfessorObject professorObject) throws SQLException {
		
		try {
			
			this.professorObject = professorObject;

			setSize(1000, 1000);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Course Registration System > PROFESSOR COURSE REGISTRATION");
			setLayout(new FlowLayout(FlowLayout.CENTER));

			PanelHeader panelHeader = new PanelHeader(String.valueOf(Roles.Professor));
			add(panelHeader);
			
			JPanel labelPanel = new JPanel();
			labelPanel.setPreferredSize(new Dimension(1700, 50));
			labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

			JLabel titleLabel = new JLabel("Course Registration System > PROFESSOR COURSE REGISTRATION");
			titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
			titleLabel.setForeground(Color.LIGHT_GRAY);

			labelPanel.add(titleLabel);
			add(labelPanel);

			String tableHeader[] = { "Course ID", "Course Code", "Course Name", "Course Description" };

			ProfessorController profCtl = new ProfessorController();
			ArrayList<CourseObject> courseObject = profCtl.getAllCourse();

			// Table model
			DefaultTableModel tableModel = new DefaultTableModel() {

				boolean[] canEdit = new boolean[] { false, false, false, false };

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit[columnIndex];
				}
			};

			for (int j = 0; j < tableHeader.length; j++) {
				tableModel.addColumn(tableHeader[j]);
			}

			String[] tableRow = new String[4];
			for (int i = 0; i < courseObject.size(); i++) {
				tableRow[0] = String.valueOf(courseObject.get(i).getCourseID());
				tableRow[1] = courseObject.get(i).getCourseCode();
				tableRow[2] = courseObject.get(i).getCourseName();
				tableRow[3] = courseObject.get(i).getCourseDesc();
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

			courseTable.getColumnModel().getColumn(0).setMinWidth(0);
			courseTable.getColumnModel().getColumn(0).setMaxWidth(0);
			courseTable.getColumnModel().getColumn(0).setWidth(0);
			
			
			// Align all cells to center
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			courseTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			courseTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			courseTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);


			JScrollPane coursePanel = new JScrollPane(courseTable);
			coursePanel.setPreferredSize(new Dimension(1000, 400));
			coursePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

			JPanel tablePanel = new JPanel();
			tablePanel.setPreferredSize(new Dimension(1500, 400));
			tablePanel.add(coursePanel);

			JPanel buttonPanel = new JPanel();
			buttonPanel.setPreferredSize(new Dimension(1500, 50));
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

			
			add(tablePanel); // add panel to frame
			add(buttonPanel);
			
			
			PanelFooter panelFooter = new PanelFooter(String.valueOf(Roles.Professor));
			add(panelFooter);
			
			
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);

			
			
			buttonBack.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					try {
						new ProfessorView(professorObject);
					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			buttonSubmit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (courseTable.getSelectedRow() != -1) {
						
						int courseID = Integer.parseInt(courseTable.getModel().getValueAt(courseTable.getSelectedRow(), 0).toString());
						
						try {
							int row = profCtl.saveCourse(professorObject.getID(), courseID);
							if (row != 0) {
								JOptionPane.showMessageDialog(null, "You have registered Successfully to "
										+ courseTable.getModel().getValueAt(courseTable.getSelectedRow(), 2) + ".");
								setVisible(false);
								new ProfessorCourseView(professorObject);
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

		ProfessorCourseView professorCourseView = new ProfessorCourseView(ProfessorCourseView.professorObject);

	}

	
	
	
	
	
	
}




