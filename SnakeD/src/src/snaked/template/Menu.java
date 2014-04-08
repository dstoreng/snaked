package src.snaked.template;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.List;
import java.awt.Rectangle;

import src.snaked.Entities.Highscore;

public class Menu {
	Dimension dim;
	
	private Rectangle playButton; 
	private Rectangle optionsButton;
	private Rectangle highscoreButton;
	private Rectangle quitButton;
	private boolean doDraw = true;
	
	public Menu(Dimension dim){
		this.dim = dim;
		playButton = new Rectangle(dim.width / 2 - 40, 150, 100, 50);
		optionsButton = new Rectangle(0, 0, 150, 50);
		highscoreButton = new Rectangle(dim.width / 2 - 90, 250, 200, 50);
		quitButton = new Rectangle(dim.width / 2 - 40, 350, 100, 50);
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.green);
		g.drawString("SnakeD!", dim.width/2 - 100, 100);
		
		Font fnt2 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt2);
		g.drawString("Play", playButton.x+19, playButton.y+35);
		g.drawString("Highscores", highscoreButton.x+19, highscoreButton.y+35);
		g.drawString("Quit", quitButton.x+19, quitButton.y+35);
		g.drawString("Settings", optionsButton.x+19, optionsButton.y+35);
		
		g2d.draw(playButton);
		g2d.draw(optionsButton);
		g2d.draw(highscoreButton);
		g2d.draw(quitButton);
	}
	
	public void renderHighscores(Graphics g, List<Highscore> l){
		if(doDraw){
			doDraw = false;
			//Copy to escape cuncurrentmodexception
			Collections.sort(l);
			Collections.reverse(l);
			for(Highscore i : l){
				System.out.println(i.getScore() + " ");
			}
			Font fnt = new Font("arial", Font.BOLD, 50);
			g.setFont(fnt);
			g.setColor(Color.green);
			g.drawString("Highest SnakeDs!", dim.width/3 - 100, 50);
			
			Font fnt2 = new Font("arial", Font.BOLD, 35);
			g.setFont(fnt2);
			
			for(int i = 0; i < l.size(); i++){
				if(i < 10)
					g.drawString(i + 1 + ": " + l.get(i).toString(), quitButton.x-50, (i * 40) + 120);
				else
					break;
			}			
		}
	}

	public Rectangle getPlayButton() {
		return playButton;
	}

	public Rectangle getHighscoreButton() {
		return highscoreButton;
	}

	public Rectangle getQuitButton() {
		return quitButton;
	}
	
	public Rectangle getOptionsButton(){
		return optionsButton;
	}
	
	public void drawDone(){
		doDraw = true;
	}
}
