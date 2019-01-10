import java.awt.EventQueue;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;

public class Snake extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1242962458459707523L;

	public Snake(int fr, int snake_sp_cl) throws IOException {
        initUI(fr, snake_sp_cl);
    }
	
    
    private void initUI(int fr1, int snake_sp1_cl) throws IOException {
    	add(new Board2(fr1, snake_sp1_cl));
        //this.setLayout(new GridLayout(10,10));
        setResizable(true);
        pack();        
        setTitle("Nimesh's Snake Game");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setSize(800,670);
      
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = null;
				try {
					int frame_rate;
					int snake_speed_cl;
					
					if (args.length > 0) {
						frame_rate = Integer.parseInt(args[0]);
						snake_speed_cl = Integer.parseInt(args[1]);
						ex = new Snake(frame_rate, snake_speed_cl);
						//System.out.println("");
					} else {
						ex = new Snake(60, 2);
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ex.setVisible(true);                
            }
        });
    }
}