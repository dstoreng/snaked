package src.snaked.Entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.Queue;

import src.snaked.loader.Anim;
import src.snaked.template.Board;
import src.snaked.template.Board.STATE;

public class Player implements ImageObserver{
	private Anim body;
	private Anim head;
	private Board b;

	private final int DOT_SIZE = 20;
	private final int ALL_snakeLength = 1200;
	private int snakeLength;
	
	private Queue<Pair<Integer,Integer>> positions;
	private int X[] = new int[ALL_snakeLength];
	private int Y[] = new int[ALL_snakeLength];
	
	
	
	public Player(Board b, int startLength){
		this.b = b;
		
		snakeLength = startLength;
		positions = new LinkedList<Pair<Integer, Integer>>();
		
		body = new Anim(new String[]{"/body_small.png", "/body_small2.png", "/body_small3.png", "/body_small4.png", 
				"/body_small5.png", "/body_small4.png", "/body_small3.png", "/body_small2.png", "/body_small.png"}, null);	
		head = new Anim(new String[]{"/headl_small.png", "/headr_small.png", "/headu_small.png", "/headd_small.png"}, 
				new String[]{"/eat_l.png", "/eat_r.png", "/eat_u.png", "/eat_d.png"});
		
		for(int i = 0; i < snakeLength; i++)
		{
			X[i] = 300-(i*DOT_SIZE);
			Y[i] = 300;
		}
	}

	public int getsnakeLength() {
		return snakeLength;
	}

	public void render(Graphics g, Direction dir, boolean isEating) {
		for(int i = 0; i < snakeLength; i++)
		{
			if(i == 0){
				if(isEating){
					g.drawImage(head.getImageEating(dir), X[i], Y[i], this);
				}else{
					g.drawImage(head.getDirectional(dir), X[i], Y[i], this);
				}
			}else{
				g.drawImage(body.getImage(), X[i], Y[i], this);
			}
		}
	}

	public boolean foundApple(int x, int y) {
		boolean isEating = false;
		if((X[0] == x) && (Y[0] == y)){
			isEating = true;
			snakeLength++;
		}
		return isEating;
	}

	public void checkCollision(Dimension rec) {
		boolean alive = true;
		for(int i = snakeLength; i > 0; i--){
			if((i > 4) && (X[0] == X[i]) && (Y[0] == Y[i]))
				alive = false;
		}
		
		if(Y[0] >= rec.height)
			alive = false;
		
		if(Y[0] < 0)
			alive = false;
		
		if(X[0] >= rec.width)
			alive = false;
		
		if(X[0] < 0)
			alive = false;
		
		if(!alive)
			b.setState(STATE.GAMEOVER);	
	}

	public void move(Direction dir) {
		positions.clear();
		for(int i = snakeLength; i > 0; i--){
			X[i] = X[i-1];
			Y[i] = Y[i-1];
			positions.add(new Pair(X[i],Y[i]));
		}
		
		if(dir == Direction.LEFT)
			X[0] -= DOT_SIZE;
		
		if(dir == Direction.RIGHT)
			X[0] += DOT_SIZE;
		
		if(dir == Direction.UP)
			Y[0] -= DOT_SIZE;
		
		if(dir == Direction.DOWN)
			Y[0] += DOT_SIZE;
		
	}
	
	public Queue<Pair<Integer,Integer>> getPositions(){
		return positions;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y,
			int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
