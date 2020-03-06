package Thread;

import Visão.JLoad;
import Visão.JVideoPlayer;
import uk.co.caprica.vlcj.component.EmbeddedMediaListPlayerComponent;

public class LoadPlayer implements Runnable{

	private EmbeddedMediaListPlayerComponent mediaPlayer;
	private JVideoPlayer pl;
	@Override
	public void run() {
		while(true){
			if(mediaPlayer.getMediaPlayer().isMediaParsed()){
				pl.setVisible(true);
				pl.startTimer();
				JLoad.Dispose();
				break;
			}
		}
	}
	
	public void Start(EmbeddedMediaListPlayerComponent player, JVideoPlayer pp){
		this.mediaPlayer = player;
		this.pl = pp;
		JLoad.Show();
		Thread thr = new Thread(this);
		thr.start();
	}
}
