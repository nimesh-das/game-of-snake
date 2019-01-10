import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board2 extends JPanel
        implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1793329651284432716L;
	public static HashMap<String, AudioPlayer> soundtrack;
	public static int B_WIDTH = 800;
    public static int B_HEIGHT = 600;
    public static boolean start_game = false;
    public static boolean splash_screen_on = true;
    public static boolean how_to_play_on = false;
    public static boolean game_over = false;
    public static boolean pause = false;
    public static boolean intro_playing = false;
    public static boolean game_play_music_on = false;
    private final static int INITIAL_X = 1;
    private final static int INITIAL_Y = 1;
    private final int SNAKE_WIDTH = 10;
    private final int SNAKE_HEIGHT = 10;
    private final int DELAY_FR = 1;
    //private final int DELAY_SS = 1;
    public static int score = 0;
    public static int num_bodies = 1;
    private static Timer timer_fr;
    public static int snake_speed;
    public static int snake_speed_default;
    public static int frame_rate;
    //private static Timer timer_ss;
    private static int x;
	private static int y;
	private int xold;
	private int yold;
	private static int xmove;
	private static int ymove;
    private int counter = 0;
    private static String direction_current = "";
    private static String direction_old = "";
    private static Random generator = new Random();
    public static int testbaitx;
    public static int testbaity; 
    public static List<Point> tracker = new ArrayList<Point>();
    public static Image backgroundimg;
    public static Image backgroundmenu;
        
    // Constructor method for main class
    public Board2(int fr2, int snake_sp2) throws IOException {
        initBoard(fr2, snake_sp2);
    }
    
    public static void start() {
    	x = INITIAL_X;
    	y = INITIAL_Y;
    	game_over = false;
    	splash_screen_on = false;
    	how_to_play_on = false;
    	pause = false;
    	num_bodies = 1;
    	start_game = true; 
    	xmove = 0;
    	ymove = 0;
    	score = 0;
    	tracker.clear();
    	//snake_speed = 30;
    	direction_old = "";
    	direction_current = "";
    	timer_fr.restart();
    }
    
    public static void restart() {
    	reset();
    	//timer_fr.stop();
    	timer_fr.restart();
    }
    
    public static void reset() {
    	x = INITIAL_X;
    	y = INITIAL_Y;
    	game_over = false;
    	splash_screen_on = false;
    	how_to_play_on = false;
    	pause = false;
    	num_bodies = 1;
    	start_game = true; 
    	xmove = 0;
    	ymove = 0;
    	score = 0;
    	tracker.clear();
    	snake_speed = snake_speed_default;
    	direction_old = "";
    	direction_current = "";
    	//Board2.soundtrack.get("gameover").play();
    }
    
    // InitBoard method to initialize main board
    private void initBoard(int fr3, int snake_sp_cl) throws IOException {
        setBackground(new Color(0xC7FF33));
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);
        x = INITIAL_X;
        y = INITIAL_Y;
        testbaitx = generator.nextInt(B_WIDTH-40);
        testbaity = generator.nextInt(B_HEIGHT-40);
        timer_fr = new Timer(DELAY_FR, this);
        tracker.clear();
        timer_fr.start();
        addKeyListener(this);
        addMouseListener(new MouseIn());
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        soundtrack = new HashMap<String, AudioPlayer>();
        soundtrack.put("gameover", new AudioPlayer("gameOver.wav"));
        soundtrack.put("Intro", new AudioPlayer("MafiaSoundtrack.wav"));
        soundtrack.put("gameplay", new AudioPlayer("gameplay.wav"));
        soundtrack.put("eating", new AudioPlayer("eating.wav"));
        InputStream is = this.getClass().getResourceAsStream("grassfinal.jpeg");
        InputStream is2 = this.getClass().getResourceAsStream("backgroundstart.jpg");
        backgroundimg = ImageIO.read(is);
        backgroundmenu = ImageIO.read(is2);
        frame_rate = fr3;
        //System.out.println("frame rate before: " + fr3 + " and snake speed before: " + snake_sp_cl);
        snake_speed = calc_speed(snake_sp_cl);
        snake_speed_default = snake_speed;
        //System.out.println("frame rate: " + frame_rate + " and snake speed: " + snake_speed);
        
    }

    
    // Up method to make snake go up
    private void up() {
    	
    	ymove = -1;
    	xmove = 0;
    	
    	direction_current = "up";
    	
    }
    
    // Down method to make snake go down
    private void down() {
    	
    	ymove = 1;
    	xmove = 0;
    	
    	direction_current = "down";
    }
    
    // Left method to make snake go left
    private void left() {
    	 	
    	xmove = -1;
    	ymove = 0;
    	
    	direction_current = "left";
    }
    
    // Right method to make snake go right
    private void right() {
    	    	
    	xmove = 1;
    	ymove = 0;
    	
    	direction_current = "right";
    }

    
    // PaintComponent method is being called multiple times within a sec to draw the square on the board
    // Think of it like in 1 iteration, the snake is like -----> and snake head is at like (4,4),
    // and body is at like (1,4), (2,4), (3,4)
    // and then in another iteration it looks like like ^ and snake head is at like (4,3)
    //                                        			|
    //                                        			|
    //                                        			|
    // and body is at like (4,2), (4,1), (4,0)
    // What we want is when snake head changes from (4,4) to (4,3), we want this transition to look like
    //		  ^
    //		  |
    // --------
    // So we want the body to be at (4,4), (3,4) and (2,4), and eventually snake head moves to (4,2), then 
    // body moves to (4,3), (4,4) and (3,4)
    public static void restart_timer() {
    	timer_fr.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	SplashScreen ss = new SplashScreen();
    	HowToPlay h2p = new HowToPlay();
    	GameOver go = new GameOver();
    	//System.out.println("frame rate in paintCom: " + frame_rate + " and snake speed in paintcom: " + snake_speed);
    	if (!start_game && splash_screen_on) {
    		ss.render(g);
    		if (!intro_playing) {
    			soundtrack.get("Intro").play();
    			intro_playing = true;
    		}
    	} else if (!start_game && how_to_play_on) {
    		h2p.render(g);
    	} else if (game_over) {
    		go.render(g);
    	} else {
    				if (soundtrack.get("Intro").isPlaying()) {
    					soundtrack.get("Intro").stop();
    				}
    				
    				if (!soundtrack.get("gameplay").isPlaying()) {
    					soundtrack.get("gameplay").play();
    				}
    				g.drawImage(backgroundimg, 0, 0, B_WIDTH, B_HEIGHT, this);
    				//drawGrid(g);
    				int flag = 0;
    				for (Point point : Board2.tracker) {
    					if (!(point == tracker.get(tracker.size()-1))) {
	    					if (flag == 0) {
	        					g.setColor(new Color(0x006400));
	        					flag = 1;
	        				} else {
	        					g.setColor(new Color(0x993300));
	        					flag = 0;
	        				}
    					} else {
    							g.setColor(Color.YELLOW);
    					}
        				
        				//g.fill3DRect(point.x*SNAKE_WIDTH, point.y*SNAKE_HEIGHT,SNAKE_WIDTH,SNAKE_HEIGHT,true);
        				g.fillRoundRect(point.x*SNAKE_WIDTH, point.y*SNAKE_HEIGHT,SNAKE_WIDTH,SNAKE_HEIGHT, 5, 5);
        		}
        	g.setColor(Color.BLACK);
         // Draw the food till snake eats it
        	g.fillRect(testbaitx, testbaity,SNAKE_WIDTH,SNAKE_HEIGHT);
        	Font fnta = new Font("arial", Font.BOLD, 20);
        	g.setFont(fnta);
        	g.drawString("Score: " + score, 40, Board2.B_HEIGHT+30);
        	g.drawString("Frame Rate: " + frame_rate, Board2.B_WIDTH/2 - 80, Board2.B_HEIGHT+30);
        	g.drawString("Speed: " + snake_speed/10, Board2.B_WIDTH/2 + 250, Board2.B_HEIGHT+30);
    	}
    }
    
    void drawGrid(Graphics g) {
    	// Vertical
    	g.setColor(Color.LIGHT_GRAY);
    	for(int j = 0; j <= B_WIDTH; j += 10) {
    		g.drawLine(j, 0, j, B_HEIGHT);
    	}
    	
    	// Horizontal
    	for(int j = 0; j <= B_HEIGHT; j+=10) {
    		g.drawLine(0, j, B_WIDTH, j);
    	}
    }
   
	private void end_game_lose() {
    	timer_fr.stop();
    	// Display Game over, you lose!
    }
	
	// This method is being called multiple times (frames) in a second
    @Override
    public void actionPerformed(ActionEvent e) {

    	counter++;
    	
    	xold = x;
		yold = y;
		
		// denominator dependent on frame rate
		if ((counter % (1000/frame_rate)) == 0) {
			repaint();
		}
		
		if ((counter % (1000/snake_speed)) == 0) {
			
			Point head = new Point(x,y);
			
			x += xmove;
			y += ymove;
			
			//remove tail
			if (tracker.size() > num_bodies) {
    			tracker.remove(0);
    		}
			
			// add a new heqd
				tracker.add(head);
			
			    		    		
	    		// Hit the edges, you lose
	    		if ((y >= (B_HEIGHT/SNAKE_HEIGHT)) || (x >= (B_WIDTH/SNAKE_WIDTH)) || (y < 0) || (x < 0)) {
	    			game_over = true;
	    			soundtrack.get("gameover").play();
	    			restart();
	    		}
	    		
	    		// Hit yourself, you lose
	    		try {
	    		for (Point point : Board2.tracker) {
    				if ((point != (tracker.get(tracker.size()-1))) && (point.x != INITIAL_X) && (point.y != INITIAL_Y) && (point.x == head.x) && (point.y == head.y)) {
    					game_over = true;
    					soundtrack.get("gameover").play();
    					restart();
    				}
    				}
	    		}
	    		catch(Exception e1) {
	    			e1.printStackTrace();
	    		}
    	}
    	//// When you eat the food, num_bodies increments
    	if ((x*SNAKE_WIDTH >= testbaitx-SNAKE_WIDTH) &&
				(x*SNAKE_WIDTH <= testbaitx+SNAKE_WIDTH)
				&& (y*SNAKE_HEIGHT >= testbaity-SNAKE_HEIGHT) &&
				(y*SNAKE_HEIGHT <= testbaity+SNAKE_HEIGHT)) {

				soundtrack.get("eating").play();
    			testbaitx = generator.nextInt(B_WIDTH-5);
	        	testbaity = generator.nextInt(B_HEIGHT-5);
	        	num_bodies += 5;
	        	score += 100;
	        	check_level();
	        }
    }


	private void check_level() {
		// TODO Auto-generated method stub
		if ((score > 200) && (snake_speed < 95)) {
			snake_speed += 5;
		} 
		
		if ((score > 400) && (snake_speed < 90)) {
			snake_speed += 10;
		}
		
		if ((score > 700) && (snake_speed < 90)) {
			snake_speed += 10;
		}
		
		if ((score > 1000) && (snake_speed < 80)) {
			snake_speed += 10;
		}
		
		if ((score > 1300)  && (snake_speed < 80)) {
			snake_speed += 10;
		}
		
		if (score > 1500) {
			snake_speed = 100;
		}
	}

	private int calc_speed(int snake_speed2_cl) {
		// TODO Auto-generated method stub
		//System.out.println("frame rate infunc1: " + frame_rate + " and snake speed in func1: " + snake_speed2_cl);
		if (snake_speed2_cl == 1) {
			return 10;
		} else if (snake_speed2_cl == 2) {
			return 20;
		} else if (snake_speed2_cl == 3) {
			//System.out.println("frame rate infunc2: " + frame_rate + " and snake speed in func2: " + snake_speed);
			return 30;
		} else if (snake_speed2_cl == 4) {
			return 40;
		} else if (snake_speed2_cl == 5) {
			return 50;
		} else if (snake_speed2_cl == 6) {
			return 60;
		} else if (snake_speed2_cl == 7) {
			return 70;
		} else if (snake_speed2_cl == 8) {
			return 80;
		} else if (snake_speed2_cl == 9) {
			return 90;
		} else if (snake_speed2_cl == 10) {
			return 100;
		} else {
			//System.out.println("frame rate infunc: " + frame_rate + " and snake speed in func: " + snake_speed);
			return 30;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP && direction_current != "down") {
			up();
		}
		else if (code == KeyEvent.VK_DOWN && direction_current != "up") {
			down();
		}
		else if (code == KeyEvent.VK_RIGHT && direction_current != "left") {
			right();
		}
		else if (code == KeyEvent.VK_LEFT && direction_current != "right") {
			left();
		}
		else if (code == KeyEvent.VK_SPACE) {
			if (!pause) {
				timer_fr.stop();
				pause = true;
				if (soundtrack.get("gameplay").isPlaying()) {
					soundtrack.get("gameplay").stop();
				}
			} else {
				pause = false;
				timer_fr.start();
				if (!soundtrack.get("gameplay").isPlaying()) {
					soundtrack.get("gameplay").resume();
				}
			}
		}
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
