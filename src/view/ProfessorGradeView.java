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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import controller.ProfessorGradeController;
import orm.PersonObject;
import orm.ProfessorStudentCourseMetricObject;
import utility.ButtonEditor;
import utility.ButtonRenderer;

@SuppressWarnings("serial")
public class ProfessorGradeView extends JFrame {

	private static PersonObject personObj;

	public ProfessorGradeView(PersonObject personObject) throws SQLException, IOException {

		ProfessorGradeView.personObj = new PersonObject(personObject.getFirstName(), personObject.getLastName(),
				personObject.getDOB());

		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		PanelHeader panelHeader = new PanelHeader();
		add(panelHeader);

		JPanel labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(1000, 100));
		labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JLabel titleLabel = new JLabel("Professor > Student Grade View");
		titleLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		titleLabel.setForeground(Color.LIGHT_GRAY);

		labelPanel.add(titleLabel);
		add(labelPanel, BorderLayout.NORTH);

		JPanel tablePanel = new JPanel();
		// DefaultTableModel tableModel = new DefaultTableModel();
		DefaultTableModel tableModel = new DefaultTableModel() {

			boolean[] canEdit = new boolean[] { false, false, false, false, true, false, true };

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		JTable table = new JTable(tableModel);
		table.setFont(new Font("Serif", Font.ITALIC, 14));
		table.setRowHeight(table.getRowHeight() + 10);

		tableModel.addColumn("Student ID");
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Course Code");
		tableModel.addColumn("Grade Number");
		tableModel.addColumn("Grade Letter");
		tableModel.addColumn("Submit Grade");

		// Customize table header
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.blue);
		header.setForeground(Color.white);
		header.setFont(new Font("Serif", Font.BOLD, 14));
		header.setPreferredSize(new Dimension(100, 30));

		ProfessorGradeController controller = new ProfessorGradeController();
		ArrayList<ProfessorStudentCourseMetricObject> object = controller.getAllStudentCourseDetail();

		for (int i = 0; i < object.size(); i++) {

			tableModel.addRow(new Object[] {

					object.get(i).getStudent().getID(), object.get(i).getStudent().getFirstName(),
					object.get(i).getStudent().getLastName(), object.get(i).getCourse().getCourseCode(),
					object.get(i).getGradeNumber(), String.valueOf("") });

		}

		table.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(object, table));

		table.setEnabled(true);
		JScrollPane coursePanel = new JScrollPane(table);// enable scroll
															// for table
		coursePanel.setPreferredSize(new Dimension(1300, 400));
		coursePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		tablePanel.add(coursePanel);// add table to a panel
		add(tablePanel); // add panel to frame

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(1300, 100));
		buttonPanel.setLayout(new FlowLayout());
		((FlowLayout) buttonPanel.getLayout()).setHgap(30);

		JButton buttonSubmit = new JButton("Submit All");
		JButton buttonBack = new JButton("Back");
		buttonSubmit.setPreferredSize(new Dimension(150, 50));
		buttonBack.setPreferredSize(new Dimension(150, 50));
		buttonPanel.add(buttonSubmit);
		buttonPanel.add(buttonBack);
		add(buttonPanel);

		
		
		PanelFooter panelFooter = new PanelFooter();
		add(panelFooter);
		
		
		buttonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new ProfessorView(personObject);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException, IOException {
		
		ProfessorGradeView.personObj = new PersonObject("FirstName", "LastName", new GregorianCalendar(2011,1,1));
		ProfessorGradeView.personObj.setID(12458);
		
		ProfessorGradeView profCourseView = new ProfessorGradeView(ProfessorGradeView.personObj);
	}

}
