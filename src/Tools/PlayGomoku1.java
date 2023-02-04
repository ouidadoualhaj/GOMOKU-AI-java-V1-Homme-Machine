package Tools;

import algorithme.Gomoku;


public class PlayGomoku1 implements Runnable{

    @Override
    public void run() {
      Gomoku gomoku = new Gomoku();
      gomoku.playGame1(Outils.position, true);
    }
    
   
}
