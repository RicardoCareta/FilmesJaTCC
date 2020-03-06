package Visão;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class VideoPlayerTeste {

	public final JFrame frame;
	
	public final EmbeddedMediaPlayerComponent mediaPlayerComponent;
	
	public static void main(String[] args) {
		new NativeDiscovery().discover();
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new VideoPlayerTeste();
				
			}
		});
	}
	
	public VideoPlayerTeste(){
		frame = new JFrame("Video Player");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		frame.setContentPane(mediaPlayerComponent);
		
		frame.setVisible(true);
		
		mediaPlayerComponent.getMediaPlayer().playMedia("http://localhost:56931/5");
		
	}

}