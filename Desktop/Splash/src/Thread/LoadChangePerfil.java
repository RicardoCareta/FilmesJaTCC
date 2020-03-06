package Thread;

import Visão.JFulano;
import Visão.JLoad;

public class LoadChangePerfil implements Runnable{

	private JFulano fulano;
	private int ID;
	
	public void Start(JFulano fulano, int ID){
		this.fulano = fulano;
		this.ID = ID;
		Thread th = new Thread(this);
		th.start();
	}
	
	@Override
	public void run(){
		fulano.ChangePerfil(ID);
		while(true){
			if(JFulano.getLoadChange()){
				JFulano.Visible(true);
				JLoad.Dispose();
				break;
			}
		}
	}
}
