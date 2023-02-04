package Tools;

import algorithme.Gomoku;

public class HelpGomoku implements Runnable{

	@Override
	public void run() {
		Gomoku gomoku = new Gomoku();
	     gomoku.helpGame(Outils.position, true);
		
	}

}
