/**
 * 
 */
package infpp.streetlife.view;

import java.util.Arrays;

import infpp.streetlife.model.Car;

/**
 * @author Cornelius
 *
 */
public class AsciiView implements View {
	private final String BREAK = "\n";
	private String[][] printMatrix ;
	
	/** 
	 * Builds an empty street with specified amount of lanes and the specified length
	 */
	@Override
	public void build(int numberLanes, int length) {
		int streetwidht = numberLanes*2+1;
		this.printMatrix = new String[streetwidht][length];
		for (int l = 0; l < streetwidht; l ++  ) {
			
			
			for (int s = 0; s < length; s ++ ) {
				if (l % 2 == 0) {
					printMatrix[l][s] = "---";
				}
				else {
					printMatrix[l][s] = "   ";
				}
			}
		}
	}

	/**
	 * adds an object to the view
	 */
	
	@Override
	public void addObject(Car obj) {
		int laneNumber = calcLane(obj.getLane());
		int pos = obj.getX();
		printMatrix[laneNumber][pos] = obj.getName();
		}
	
	@Override
	public void updateObject(Car obj) {
		
		int laneNumber = calcLane(obj.getLane());
		int pos = obj.getX();
		int oldPos = obj.getOldPos();
		
		try {
			printMatrix[laneNumber][oldPos] = "   ";
			printMatrix[laneNumber][pos] = obj.getName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	
	@Override
	public void display() {
		for (int l = 0; l < printMatrix.length; l ++) {
			System.out.println(Arrays.toString(printMatrix[l]));
		}
		System.out.println(BREAK);

	}

	@Override
	public void erase(Car obj) {
		int laneNumber = calcLane(obj.getLane());
		int pos = obj.getX();
		printMatrix[laneNumber][pos] = "   ";
		}

	private int calcLane(int laneNumber) {
		if (laneNumber == 0) return 1;
		else return laneNumber*2-1;
	}
	
}


	
	
