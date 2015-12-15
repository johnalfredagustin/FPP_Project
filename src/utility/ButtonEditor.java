package utility;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.ProfessorController;
import orm.ProfessorStudentCourseMetricObject;
import view.ProfessorGradeView;

@SuppressWarnings("serial")
public class ButtonEditor extends DefaultCellEditor {

	protected JButton btn;
	private String lbl;
	private Boolean clicked;
	private int ID;

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

	public ButtonEditor(ArrayList<ProfessorStudentCourseMetricObject> profStudentCourseMetric, JTable table, int ID) {
		super(new JTextField());
		this.ID = ID;
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

	@Override
	public Component getTableCellEditorComponent(JTable table, Object object, boolean isSelected, int row, int column) {

		System.out.println(String.valueOf(table.getValueAt(row, 4)));

		ProfessorController profController = new ProfessorController();
		int professorID = ID;
		int studentID = Integer.parseInt(table.getValueAt(row, 2).toString());
		int courseID = Integer.parseInt(table.getValueAt(row, 1).toString());
		double gradeNumber = Double.parseDouble(table.getValueAt(row, 6).toString());

		try {
			profController.submitOneStudentGrade(professorID, studentID, courseID, gradeNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// new ProfessorGradeView(professorObject);
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
