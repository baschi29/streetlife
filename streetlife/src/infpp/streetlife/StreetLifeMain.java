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
		int length = 768;
		Car car1 = new Car(width/2, 0, "Flotter Fiat", 5);
		Car car2 = new Car(width/2, 0, "Fantastischer Ford", 6);
		Car car3 = new Car(width/2, 0, "angenehmer Audi", 4);
		Car car4 = new Car(width/2, 0, "cooles Cabrio", 3);
		Car car5 = new Car(width/2, 0, "bombastischer BMW", 9);
		Street city = new Street(width, length);
		
		city.addCar(car1);
		city.addCar(car2);
		city.addCar(car3);
		city.addCar(car4);
		city.addCar(car5);
		
		for (int i = 0; i < 5; i++) {
			city.move();
			System.out.println(city);
		}

	}

}
