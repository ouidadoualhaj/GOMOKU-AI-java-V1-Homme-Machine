package Tools;

import algorithme.Gomoku;


public class PlayGomoku implements Runnable{

    @Override
    public void run() {
      Gomoku gomoku = new Gomoku();
      gomoku.playGame(Outils.position, true);
    }
    
   
}
