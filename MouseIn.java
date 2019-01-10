import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseIn implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int xcoord = e.getX();
		int ycoord = e.getY();
		
	
	if (!Board2.start_game && !Board2.game_over && Board2.splash_screen_on && !Board2.how_to_play_on) {
		if ((xcoord >= Board2.B_WIDTH/2 - 50) && (xcoord <= Board2.B_WIDTH/2 - 50 + 100)) {
			if (ycoord >= 150 && ycoord <= 200) {
				Board2.start();
			} else if ((ycoord >= 250) && (ycoord <= 300)) {
				Board2.game_over = false;
				Board2.start_game = false;
				Board2.how_to_play_on = true;
				Board2.splash_screen_on = false;
				//Board2.restart_timer();
				
			} 
		}
	}
	else if (!Board2.start_game && Board2.how_to_play_on) {
		Board2.start();
	}
	
}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
