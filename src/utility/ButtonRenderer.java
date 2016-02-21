package utility;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

import orm.ProfessorStudentCourseMetricObject;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	
	
	public ButtonRenderer() {
		// Set button properties
		setOpaque(true);
		setText("Submit");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return this;
	}
}