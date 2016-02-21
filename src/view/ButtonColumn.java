package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class ButtonColumn {

	public ButtonColumn(JTable table){
		
		table.getColumnModel().getColumn(0).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(0).setCellEditor(new ButtonEditor(new JTextField()));
	}
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText((obj == null) ? "" : obj.toString());
		return this;
	}
}

class ButtonEditor extends DefaultCellEditor {
	protected JButton btn;
	private String lbl;
	private Boolean clicked;

	public ButtonEditor(JTextField txt) {
		super(txt);
		btn = new JButton();
		btn.setOpaque(true);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column) {
		
		lbl = (obj==null) ? "":obj.toString();
//		btn.setText("Signup");
//		btn.setName(lbl);
		clicked = true;
		return btn;
	}
	
	@Override
	public Object getCellEditorValue() {
		if(clicked){
			JOptionPane.showMessageDialog(btn, lbl+" clicked");
		}
		clicked = false;
		return new String(lbl);
	}
	
	@Override
	public boolean stopCellEditing() {
		clicked = false;
		return super.stopCellEditing();
	}
	
	@Override
	protected void fireEditingStopped() {
		// TODO Auto-generated method stub
		super.fireEditingStopped();
	}

}
