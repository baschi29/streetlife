/**
 * 
 */
package infpp.streetlife;

/**
 * Class to generate a city object
 */
public class StreetLifeMain {

	/**
	 * Generates a city object with width=1024,depth=768 and a car object
	 * the car is initialised in the middle of the city
	 * @param args
	 */
	public static void main(String[] args) {
		
		int width = 1024;
		int depth = 768;
		Car car = new Car(width/2, depth/2, "Flotter Fiat");
		City city = new City(width, depth, car);
		
		for (int i = 0; i < 5; i++) {
			city.move();
			System.out.println(city);
		}

	}

}
