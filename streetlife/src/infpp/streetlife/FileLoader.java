package infpp.streetlife;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
/**
 * The FileLoader does exactly what the name suggest: loading files. Used everywhere as a utility class to load Image/ImageIcons from resourcepaths. Uses Resourcestreams to ensure nice and smoot .jar-packing
 * @author Cornelius
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
		ImageIcon img = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(filepath)));
		return img;
	
	}
	
	/**
	 * loads an Image from the filepath 
	 * @param filepath relative path to resource
	 * @return loaded BufferedImage
	 * @throws IOException in case of path not found
	 */
	public BufferedImage loadImage(String filepath) throws IOException{
		BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filepath));
		return img;
	
	}
}
