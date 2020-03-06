package Thread;

import Vis�o.JLoad;

public class LoadScreen implements Runnable{

	private Thread th;
	
	public void Start(){
		th = new Thread(this);
		th.start();
	}
	
	@Override
	public void run() {
		JLoad.Show();
	}
	
	public void Close(){
		JLoad.Dispose();
	}

}
