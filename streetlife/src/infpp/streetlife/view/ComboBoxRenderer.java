package infpp.streetlife.view;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;

import infpp.streetlife.model.StreetObject;

/**
 * ComboBoxRenderer are a custom CellRenderer for JComboBoxes used for StreetObjects. Controls the string-print inside the combobox
 * @author Cornelius
 * @version 1.0
 * @since 2022-01-28
 *
 */
public class ComboBoxRenderer extends DefaultListCellRenderer{


    public JComponent getListCellRendererComponent(
                                   JList list,
                                   Object value,
                                   int index,
                                   boolean isSelected,
                                   boolean cellHasFocus) {
    	
    	//if object is type StreetObject, call toString() from StreetObject and store it for later use
        if (value instanceof StreetObject) {
            value = ((StreetObject)value).toString();
        }
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        return this;
    }
}


