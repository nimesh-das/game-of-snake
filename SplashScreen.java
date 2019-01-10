import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SplashScreen extends JPanel
implements ActionListener, KeyListener {

	public Rectangle playButton = new Rectangle(Board2.B_WIDTH / 2 - 50, 150, 100, 50);
	public Rectangle HelpButton = new Rectangle(Board2.B_WIDTH / 2 - 50, 250, 100, 50);

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(Board2.backgroundmenu, 0, 0, Board2.B_WIDTH, Board2.B_HEIGHT, this);
		Font fnt = new Font("arial", Font.BOLD, 35);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Nimesh's Game of Snake", Board2.WIDTH/2 + 200, 100);
		g.drawString("Play", playButton.x+13, playButton.y+35);
		g.drawString("Help", HelpButton.x+13, HelpButton.y+35);
		g.drawString("My userID: ndas", HelpButton.x+-80, 375);
		g2.draw(playButton);
		g2.draw(HelpButton);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
