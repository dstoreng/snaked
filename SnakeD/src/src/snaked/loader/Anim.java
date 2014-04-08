package src.snaked.loader;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import src.snaked.Entities.Direction;

public class Anim {
	private BufferedImage img;
	private BufferedImage[] list;
	private BufferedImage[] eating;
	private ImageLoader loader;
	private Queue<BufferedImage> queue = new LinkedList<BufferedImage>();
	
	public Anim(String[] paths, String[] headEating){
		loader = new ImageLoader();
		list = new BufferedImage[paths.length];
		eating = new BufferedImage[4];
		int i = 0;
		
		for(String s : paths){
			try 
			{
				img = loader.loadImage(s);
				queue.add(img);
				list[i] = img;
				i++;
			} catch (IOException e)
			{
				e.printStackTrace();
			} 
		}
		
		i = 0;
		
		if(headEating != null){
			for(String s : headEating){
				try 
				{
					eating[i] = loader.loadImage(s);
					i++;
				} catch (IOException e)
				{
					e.printStackTrace();
				} 
			}
		}
	}

	public Image getDirectional(Direction dir) {
		if(dir == Direction.LEFT)
			return list[0];
		if(dir == Direction.RIGHT)
			return list[1];
		if(dir == Direction.UP)
			return list[2];
		if(dir == Direction.DOWN)
			return list[3];
		return null;
	}

	public Image getImage() {
		img = queue.poll();
		queue.add(img);
		return img;
	}
	
	public Image getImageEating(Direction dir){
		if(dir == Direction.LEFT)
			return eating[0];
		if(dir == Direction.RIGHT)
			return eating[1];
		if(dir == Direction.UP)
			return eating[2];
		if(dir == Direction.DOWN)
			return eating[3];
		return null;
	}
	
}
