package src.snaked.Entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Random;

import src.snaked.loader.ImageLoader;
import src.snaked.template.Board;

public class Apple implements ImageObserver {
	Random rnd;
	
	private BufferedImage tex;
	private int apple_x;
	private int apple_y;
	private ImageLoader loader;
	private Player p;

	public Apple(Board b, Player p) {
		loader = new ImageLoader();
		try {
			tex = loader.loadImage("/apple_small.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rnd = new Random();
		this.p = p;
	}

	public void moveApple(Dimension rec, int dotSize) {
		apple_x = ((rnd.nextInt(rec.width-20)+1) / dotSize) * dotSize;
		apple_y = ((rnd.nextInt(rec.height-20)+1) / dotSize) * dotSize;
		
		Pair<Integer,Integer> pair = new Pair(apple_x, apple_y);
		if(p.getPositions().contains(pair))
			moveApple(rec, dotSize);		
	}

	public int getApple_x() {
		return apple_x;
	}

	public int getApple_y() {
		return apple_y;
	}

	public void render(Graphics g, boolean isEating) {
		if(!isEating)
			g.drawImage(tex, apple_x, apple_y, this);
		
	}
	
	//Allows for class to be an ImageObserver
	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
}
