package infpp.streetlife.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;

import infpp.streetlife.model.StreetObject;

public class ComboBoxRenderer extends DefaultListCellRenderer{


    public JComponent getListCellRendererComponent(
                                   JList list,
                                   Object value,
                                   int index,
                                   boolean isSelected,
                                   boolean cellHasFocus) {
        if (value instanceof StreetObject) {
            value = ((StreetObject)value).toString();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}


