package Thread;

import Visão.JFulano;
import Visão.JLoad;

public class LoadFulano implements Runnable{

	private int ID;
	
	public void Start(int ID){
		Thread th = new Thread(this);
		this.ID = ID;
		th.start();
	}
	
	@Override
	public void run() {
		JFulano.main(ID);
		
		while(true){
			if(JFulano.getLoad()){
				JFulano.Visible(true);
				JLoad.Dispose();
				break;
			}
		}
	}
}
