/**
 * 
 */
package infpp.streetlife.view;

import java.util.ArrayList;

import infpp.streetlife.model.Car;
import infpp.streetlife.model.StreetObject;

/**
 * @author basti
 * Simple Textausgabe des aktuellen Status des Modells.
 */
public class TextView implements View {
	
	private int numberLanes;
	private int length;
	
	@Override
	public void build(int numberLanes, int length, ArrayList<StreetObject> modelState) {
		System.out.println("Starting OceanLife ...");
		this.numberLanes = numberLanes;
		this.length = length;

	}

	@Override
	public void addObject(Car obj) {
		// hier muss nichts geschehen

	}

	@Override
	public void updateObject(Car obj) {
		// hier muss nichts geschehen

	}

	@Override
	public void erase(Car obj) {
		// hier muss nichts geschehen

	}

	@Override
	public void display(ArrayList<StreetObject> modelState) {
		
		String objectString = "";
		
		for (StreetObject obj : modelState) {
			objectString += obj.toString() + "\n";
		}
		System.out.println("Lanes: "
				+ Integer.toString(this.numberLanes)
				+ "; Length: "
				+ Integer.toString(this.length)
				+ "\n"
				+ objectString);

	}

}
