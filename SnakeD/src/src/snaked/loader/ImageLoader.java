package src.snaked.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {	
	//Throws Null if !url
	public BufferedImage loadImage(String url) throws IOException{
		BufferedImage img = null;
		try{
			img = ImageIO.read(getClass().getResource(url));
		}catch(Exception e){
			System.out.println(url + " not found..");		
		}
		
		return img;
	}
}
