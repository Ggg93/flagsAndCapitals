package dev.gl.flagsandcapitals.models;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gl
 */
public class UneditableTableModel extends DefaultTableModel {

    public UneditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
