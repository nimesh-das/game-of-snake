import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class GameOver {
	public Rectangle playAgainButton = new Rectangle(Board2.B_WIDTH / 2 - 90, 150, 200, 50);
	public Rectangle HelpButton = new Rectangle(Board2.B_WIDTH / 2 - 90, 250, 200, 50);
//	public Rectangle quitButton = new Rectangle(Board2.B_WIDTH / 2 - 50, 350, 100, 50);
	


	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//g.drawImage(Board2.backgroundmenu, 0, 0, Board2.B_WIDTH, Board2.B_HEIGHT, (ImageObserver) this);
		Font fnt = new Font("arial", Font.BOLD, 35);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("You lost control of your snake! Game over!", Board2.WIDTH/2 + 50, 100);
		g.drawString("Play Again", playAgainButton.x+12, playAgainButton.y+35);
		g.drawString("Help", HelpButton.x+60, HelpButton.y+35);
		g2.draw(playAgainButton);
		g2.draw(HelpButton);
		//Board2.reset();
		//g2.draw(quitButton);
		
	}
}
