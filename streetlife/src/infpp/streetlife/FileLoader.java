package infpp.streetlife;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * The FileLoader does exactly what the name suggest: loading files. Used everywhere as a utility class to load Image/ImageIcons from resourcepaths. Uses Resourcestreams to ensure nice and smooth .jar-packing
 * @author Cornelius, Bastian
 * @version 1.3
 * @since 2022-02-01
 *
 */



public class FileLoader {

	/**
	 * loads an Image from the filepath and converts it to an ImageIcon
	 * @param filepath relative path to resource
	 * @return loaded ImageIcon
	 * @throws IOException in case of path not found
	 */
	public ImageIcon loadImageIcon(String filepath) throws IOException{
		try {
			ImageIcon img = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(filepath)));
			return img;
		} catch (IOException e) {
			throw new IOException("Error during Image Loading");
		}
	
	}
	
	/**
	 * loads an Image from the filepath 
	 * @param filepath relative path to resource
	 * @return loaded BufferedImage
	 * @throws IOException in case of path not found
	 */
	public BufferedImage loadImage(String filepath) throws IOException{
		try {
			BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filepath));
			return img;
		} catch (IOException e) {
			throw new IOException("Error during Image Loading");
		}
	
	}
	
	/**
	 * Loads a file and glues every line onto a long String
	 * @param filepath location if file
	 * @return content of file in String
	 * @throws IOException
	 */
	public String loadFileAsString(String filepath) throws IOException{
		Scanner sc;
		String str = "";
		
		try {
			sc = new Scanner(new File(filepath));
			
			while(sc.hasNextLine()){
				str += sc.nextLine();     
			}
			return str;
		} catch (FileNotFoundException e) {
			throw new IOException("Error during File Loading");
		}
	}
		
	
	
}

