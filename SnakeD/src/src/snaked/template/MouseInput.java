package src.snaked.template;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import src.snaked.template.Board.STATE;

public class MouseInput implements MouseListener {
	private Board b;
	Dimension dim;
	private Point p;
	private Menu m;

	public MouseInput(Board b, Menu m, Dimension dim) {
		this.b = b;
		this.dim = dim;
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		p = new Point(mx, my);
		
		if(b.getGameStatus() == STATE.MENU){
			//Play
			if (m.getPlayButton().contains(p) && b.getGameStatus() == STATE.MENU){		
				b.setState(STATE.GAME);
				b.restart();
			}
			// Quit
			if (m.getQuitButton().contains(p)){
				b.saveAndExit();
			}
			// Options
			if (m.getOptionsButton().contains(p)){
				b.changePlayerName();
			}
			//Highscores
			if(m.getHighscoreButton().contains(p))
				b.setState(STATE.HIGHSCORE);
		}
		
		else if(b.getGameStatus() == STATE.GAMEOVER){
			b.setState(STATE.MENU);
		}else if(b.getGameStatus() == STATE.HIGHSCORE){
			b.setState(STATE.MENU);
			b.startTimer();
			m.drawDone();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
