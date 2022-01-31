package infpp.streetlife;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;

public class FileLoader {

	public ImageIcon loadImageIcon(String filepath) throws IOException{
		ImageIcon img = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream(filepath)));
		return img;
	
	}
	
	public BufferedImage loadImage(String filepath) throws IOException{
		BufferedImage img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filepath));
		return img;
	
	}
}
