package utility;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ProfessorController;
import orm.ProfessorObject;
import orm.ProfessorStudentCourseMetricObject;
import view.ProfessorGradeView;

@SuppressWarnings("serial")
public class ButtonEditor extends DefaultCellEditor {

	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	private int ID;
	private ArrayList<ProfessorStudentCourseMetricObject> profStudentCourseMetric = new ArrayList<ProfessorStudentCourseMetricObject>();
	private ProfessorGradeView view;

	public ButtonEditor(JTextField txt) {
		super(txt);
		btn = new JButton();
		btn.setOpaque(true);

		// when button is clicked
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public ButtonEditor(ArrayList<ProfessorStudentCourseMetricObject> profStudentCourseMetric, JTable table, int ID,
			ProfessorGradeView view) {
		super(new JTextField());
		this.profStudentCourseMetric = profStudentCourseMetric;
		this.ID = ID;
		btn = new JButton();
		btn.setOpaque(true);

		this.view = view;

		// when button is clicked
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();

			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object object, boolean isSelected, int row, int column) {

		ProfessorController profController = new ProfessorController();
		ProfessorObject professorObject = null;
		int professorID = profStudentCourseMetric.get(row).getProfessor().getID();
		int courseID = profStudentCourseMetric.get(row).getCourse().getCourseID();
		int studentID = profStudentCourseMetric.get(row).getStudent().getID();
		String studentName = profStudentCourseMetric.get(row).getStudent().getFirstName() + " "
				+ profStudentCourseMetric.get(row).getStudent().getLastName();

		try {
			professorObject = profController.getProfessorDetail(professorID);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		double gradeNumber = Double.parseDouble(table.getValueAt(row, 3).toString());

		if (gradeNumber < 0 || gradeNumber > 100) {
			JOptionPane.showMessageDialog(null, "Grade should be between 0 to 100! Please try again!");
			view.setVisible(false);
			try {
				new ProfessorGradeView(professorObject);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				boolean result = profController.submitOneStudentGrade(professorID, studentID, courseID, gradeNumber);
				if (result) {
					JOptionPane.showMessageDialog(null,
							"Successfully submitted grade for student ID: " + studentID + " (" + studentName + ")");
					view.setVisible(false);
					new ProfessorGradeView(professorObject);
				}
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		lbl = (object == null) ? "" : object.toString();
		btn.setText(lbl);
		clicked = true;
		return btn;

	}

	@Override
	public Object getCellEditorValue() {
		if (clicked) {
			// JOptionPane.showMessageDialog(btn, lbl + " clicked");

		}
		clicked = false;
		btn.setOpaque(true);
		return new String(lbl);
	}

	@Override
	public boolean stopCellEditing() {
		clicked = false;
		return super.stopCellEditing();
	}

	@Override
	protected void fireEditingStopped() {

		super.fireEditingStopped();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}