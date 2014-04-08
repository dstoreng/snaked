package src.snaked.template;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.snaked.template.Board;

public class KeyInput extends KeyAdapter{
	
	Board b;
	
	public KeyInput(Board b){
		this.b = b;
	}
	
	public void keyPressed(KeyEvent e){
		b.keyDown(e);
	}
	
}
