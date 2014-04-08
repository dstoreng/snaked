package src.snaked.template;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame{

	private static final long serialVersionUID = 1L;
	private final int WIDTH = 790;
	private final int HEIGHT = 590;
	private Board b;

	public Game(){
		b = new Board(this);
		add(b);
		setTitle("SnakeD");
		pack();
		
		setBackground(Color.GREEN);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run(){
				JFrame a = new Game();
				a.setVisible(true);
			}
		});
	}
	
	public Dimension getWindowSize(){
		return new Dimension(WIDTH, HEIGHT);
	}

}
