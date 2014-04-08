package src.snaked.template;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import src.snaked.Entities.Apple;
import src.snaked.Entities.Direction;
import src.snaked.Entities.Highscore;
import src.snaked.Entities.Player;
import src.snaked.loader.FileManager;

public class Board extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private int delay = 165;
	private int dotSize = 20;
	private int playerScore = 0;
	private final int MINdelay = 20;
	private final int STARTDELAY = 165;
	
	private boolean movable = true;
	private boolean addScore = true;
	private boolean isEating = false;
	
	private Timer timer;
	private Menu menu;
	private Dimension rec;
	private Direction dir;
	private Direction queuedKey = null;	

	private String playerName = "Anon";
	private Apple apple;
	private Player player;
	private List<Highscore> highscores = new LinkedList<Highscore>();
	private FileManager fileManager = new FileManager();
	
	public enum STATE {
		MENU, GAME, GAMEOVER, HIGHSCORE
	};

	private STATE gameStatus = STATE.MENU;

	public Board(Game ga) {		
		rec = new Dimension(ga.getWindowSize().width, ga.getWindowSize().height);
		menu = new Menu(rec);
		
		addMouseListener(new MouseInput(this, menu, rec));
		addKeyListener(new KeyInput(this));
		setBackground(Color.BLACK);
		setFocusable(true);
		setVisible(true);
		
		highscores = fileManager.readFromArchive();
		
		init();
	}

	public void init() {
		dir = Direction.RIGHT;
		player = new Player(this, 3);
		
		apple = new Apple(this, player);
		apple.moveApple(rec, dotSize);

		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void restart(){
		addScore = true;
		delay = STARTDELAY;		
		playerScore = 0;
		gameStatus = STATE.GAME;	
		timer.stop();
		init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(gameStatus == STATE.GAME){
			player.checkCollision(rec);
			player.move(dir);
			checkApple();
			
			//Check for queued keystroke, player cannot turn before he has moved from prev-turn. It's all good brah!
			if(queuedKey != null){
				dir = queuedKey;
				queuedKey = null;
			}else{
				movable = true;
			}
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		//Draw ingame
		if (gameStatus == STATE.GAME)
		{
			player.render(g, dir, isEating);
			apple.render(g, isEating);
			
			Toolkit.getDefaultToolkit().sync();
			g.dispose();
			
			isEating = false;
		} 
		//Draw menu
		else if (gameStatus == STATE.MENU)
		{
			menu.render(g);
		}
		//Draw highscore
		else if( gameStatus == STATE.HIGHSCORE)
		{
			timer.stop();
			menu.renderHighscores(g, highscores);
		}
		//Draw gameover :(
		else 
		{
			gameOver(g);
		}
	}

	private void checkApple() {
		if (player.foundApple(apple.getApple_x(), apple.getApple_y())) {
			//Increase ticks if speed is not too high
			if(delay > MINdelay)
				delay -= 2;
			
			timer.setDelay(delay);
			apple.moveApple(rec, dotSize);
			playerScore++;
			
			//Bool for changing texture on player when he finds apple
			isEating = true;
		}
	}

	public void keyDown(KeyEvent e) {
		int key = e.getKeyCode();
			
		if (movable) {
			movable = false;

			if ((key == KeyEvent.VK_LEFT) && (dir != Direction.RIGHT)) {
				dir = Direction.LEFT;
			}
			if ((key == KeyEvent.VK_RIGHT) && (dir != Direction.LEFT)) {
				dir = Direction.RIGHT;
			}
			if ((key == KeyEvent.VK_UP) && (dir != Direction.DOWN)) {
				dir = Direction.UP;
			}
			if ((key == KeyEvent.VK_DOWN) && (dir != Direction.UP)) {
				dir = Direction.DOWN;
			}
		//Player cannot move, but queued a key for movement. This prevents "input-lag"
		}else{
			if ((key == KeyEvent.VK_LEFT) && (dir != Direction.RIGHT)) {
				queuedKey = Direction.LEFT;
			}
			if ((key == KeyEvent.VK_RIGHT) && (dir != Direction.LEFT)) {
				queuedKey = Direction.RIGHT;
			}
			if ((key == KeyEvent.VK_UP) && (dir != Direction.DOWN)) {
				queuedKey = Direction.UP;
			}
			if ((key == KeyEvent.VK_DOWN) && (dir != Direction.UP)) {
				queuedKey = Direction.DOWN;
			}
		}
	}

	private void gameOver(Graphics g) {
		gameStatus = STATE.GAMEOVER;
		String msg = "Game over, you scored " + playerScore + "..";
		Font f = new Font("Arial", Font.BOLD, 25);
		g.setColor(Color.green);
		g.setFont(f);
		g.drawString(msg, rec.width / 3, rec.height / 2);
		
		//This method is a render method, so we do not want the highscore to be updated
		//each time the screen updates. Retarded yes plox
		if(addScore)
		{			
			highscores.add(new Highscore(playerScore, playerName));
			saveState();
			addScore = false;
		}
	}
	
	/*
	 * Saves highscores and exits the game
	 */
	public void saveAndExit(){
		saveState();
		System.exit(1);
	}
	
	public void saveState(){
		fileManager.writeToArchive(highscores);
	}
	
	public void startTimer(){
		timer.start();
	}

	public void setState(STATE game2) {
		gameStatus = game2;	
	}

	public STATE getGameStatus() {
		return gameStatus;
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(rec.width, rec.height);
	}
	
	@Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    
    public void windowClosing(WindowEvent e){

    }

	public void changePlayerName() {
		playerName = JOptionPane.showInputDialog("Enter player name:");
	}

}
