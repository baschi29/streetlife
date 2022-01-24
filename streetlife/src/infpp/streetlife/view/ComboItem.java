package infpp.streetlife.view;

/**
 * Class used for displayment in comboBox, contains a displayment string and a value - typically cars
 * @author Cornelius
 *
 */

class ComboItem
{
    private String key;
    private String value;

    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
}