package Tools;

import algorithme.Gomoku;

public class PlayGomoku2 implements Runnable{

	@Override
	public void run() {
		Gomoku gomoku = new Gomoku();
	    gomoku.playGame2(Outils.position);
		
	}

}
