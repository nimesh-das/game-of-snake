import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HowToPlay extends JPanel {

		public void render(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g.drawImage(Board2.backgroundmenu, 0, 0, Board2.B_WIDTH, Board2.B_HEIGHT, this);
				String how2pmsg1 = new String("What to do: ");
	
					Font fnt1 = new Font("arial", Font.BOLD, 35);
					g.setFont(fnt1);
					g.setColor(Color.black);
					//g.drawString(how2pmsg, Board2.WIDTH/2 +15, 100);
					int y1=100;
					for (String line : how2pmsg1.split("\n")) {
						g.drawString(line, Board2.WIDTH/2 +300, y1 += g.getFontMetrics().getHeight());
					}
			
			
			String how2pmsg = new String(" Use the up, down, left and right arrow keys" +  
  			  " to move the snake \n accordingly." + 
  			  " Eat the morcel of food on the board without \n" +
  			  " touching the body of the snake or any of the 4 walls." +
  			  " Press \n 'Spacebar' to pause and restart the game." +
  			  " Survive as long as \n possible. Good luck.");
			Font fnt = new Font("arial", Font.BOLD, 15);
			
			g.setFont(fnt);
			g.setColor(Color.black);
			//g.drawString(how2pmsg, Board2.WIDTH/2 +15, 100);
			int y2=170;
			for (String line : how2pmsg.split("\n")) {
				g.drawString(line, Board2.WIDTH/2 +200, y2 += g.getFontMetrics().getHeight());
			}
			
			String clickme2bgin = new String("Click anywhere to begin !");
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt2);
			g.setColor(Color.black);
			//g.drawString(how2pmsg, Board2.WIDTH/2 +15, 100);
			int y3=370;
			for (String line : clickme2bgin.split("\n")) {
				g.drawString(line, Board2.WIDTH/2 + 220, y3 += g.getFontMetrics().getHeight());
			}
			
			
		}
	
	
}
