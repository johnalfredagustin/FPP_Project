package utility;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import orm.ProfessorStudentCourseMetricObject;

public class ButtonEditor extends DefaultCellEditor {

	private ArrayList<ProfessorStudentCourseMetricObject>  profStudentCourseMetric;
	private JTable table;
	
	protected JButton btn;
	private String lbl;
	private Boolean clicked;

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
	
	public ButtonEditor(ArrayList<ProfessorStudentCourseMetricObject>  profStudentCourseMetric, JTable table) {
		super(new JTextField());
		this.profStudentCourseMetric = profStudentCourseMetric;
		this.table = table;
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

//		JOptionPane.showMessageDialog();
		String temp = String.valueOf(table.getValueAt(row, 4));
//		JOptionPane.showMessageDialog(btn, temp + " " + String.valueOf(profStudentCourseMetric.get(row).getGradeNumber()));
		
		lbl = (object == null) ? "" : object.toString();
		btn.setText(lbl);
		clicked = true;
		return btn;

	}

	@Override
	public Object getCellEditorValue() {
		if (clicked) {
//			JOptionPane.showMessageDialog(btn, lbl + " clicked");
			
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
}
