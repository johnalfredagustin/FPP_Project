package utility;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class TextFieldRenderer extends JTextField implements TableCellRenderer {

	public TextFieldRenderer() {
		// Set button properties
		setOpaque(true);
//		setEditable(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText((object == null) ? "" : object.toString());
		return this;
	}
}