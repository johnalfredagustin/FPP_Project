package utility;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TextFieldEditor extends DefaultCellEditor {

	protected JTextField txt;
	private String lbl;
	private Boolean clicked;

	public TextFieldEditor(JTextField txt) {
		super(txt);
		txt = new JTextField();
		txt.setOpaque(true);
//		txt.setEditable(true);

//		// when button is clicked
//		txt.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				fireEditingStopped();
//			}
//		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object object, boolean isSelected, int row, int column) {

//		lbl = (object == null) ? "" : object.toString();
//		txt.setText(lbl);
//		clicked = true;
		return txt;

	}

	@Override
	public Object getCellEditorValue() {
		if (clicked) {
			JOptionPane.showMessageDialog(editorComponent, txt.getText() + " clicked");
		}
		clicked = false;
		return new String(lbl);
	}

	
	@Override
	protected void fireEditingStopped() {

		super.fireEditingStopped();
	}
}
