import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	
	private Clip piece1;
	
	public AudioPlayer(String s) {
		//System.out.println("ye0");
			AudioInputStream stream;
			try {
			//	System.out.println("ye1");
				stream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
				//System.out.println("ye2");
				AudioFormat bf = stream.getFormat();
				//System.out.println("ye3");
				AudioFormat df = new AudioFormat(
					
					AudioFormat.Encoding.PCM_SIGNED,
					bf.getSampleRate(),
					16,
					bf.getChannels(),
					bf.getChannels()*2,
					bf.getSampleRate(),
					false
					);
			//	System.out.println("ye3.5");
			AudioInputStream ss1 = AudioSystem.getAudioInputStream(df, stream);
			//System.out.println("ye4");
			piece1 = AudioSystem.getClip();
			//System.out.println("ye5");
			piece1.open(ss1);
			
	}
		
			catch(Exception e) {
				//System.out.println("exception?");
				e.printStackTrace();
			}
	}
	
	public void play() {
		if (piece1 == null) {
			//System.out.println("we get called1?");
			return;
		}  else {
			piece1.setFramePosition(0);
			//System.out.println("we get called3?");
			piece1.start();
		}
	}
	
	public void stop() {
		if ((piece1 != null) && (piece1.isRunning())) {
			piece1.stop();
		}
	}
	
	public void restart() {
		piece1.setFramePosition(0);
		play();
	}
	
	public void shut() {
		piece1.stop();
		piece1.close();
	}
	
	public boolean isPlaying() {
		return piece1.isRunning();
	}
	
	public void resume() {
		piece1.start();
	}
}
