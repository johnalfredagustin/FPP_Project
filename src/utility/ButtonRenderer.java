package utility;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
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