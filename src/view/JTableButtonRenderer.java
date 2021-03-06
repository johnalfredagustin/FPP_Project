package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
public class JTableButtonRenderer {
  public JComponent makeUI() { 
    String[] columnNames = {"Button"};  // When I increase the number of columns it hides another button
    Object[][] data = {{"AAA"}, {"BBB"}};
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
      @Override public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
      }
    };
    JTable table = new JTable(model);
    table.setRowHeight(36);
    ActionPanelEditorRenderer er = new ActionPanelEditorRenderer();
    TableColumn column = table.getColumnModel().getColumn(0);
    column.setCellRenderer(er);
    column.setCellEditor(er);
    
    JPanel p = new JPanel(new BorderLayout());
    p.add(new JScrollPane(table));
    p.setPreferredSize(new Dimension(320, 200));
    return p;
  }
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override public void run() { createAndShowGUI(); }
    });
  }
  public static void createAndShowGUI() {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.getContentPane().add(new JTableButtonRenderer().makeUI());
    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }
}
class ActionPanelEditorRenderer extends AbstractCellEditor
                   implements TableCellRenderer, TableCellEditor {
  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  
  public ActionPanelEditorRenderer() {
    super();
    JButton viewButton = new JButton(new AbstractAction("Signup") {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Click on button");
      }
    });
    
    panel1.add(new JButton("Signup"));
    panel2.add(viewButton);
  }
  
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
               boolean isSelected, boolean hasFocus, int row, int column) {
    panel1.setBackground(isSelected?table.getSelectionBackground()
                                   :table.getBackground());
    return panel1;
  }
  
  @Override
  public Component getTableCellEditorComponent(JTable table, Object value,
                                boolean isSelected, int row, int column) {
    panel2.setBackground(table.getSelectionBackground());
    return panel2;
  }
  @Override
  public Object getCellEditorValue() {
    return null;
  }
}