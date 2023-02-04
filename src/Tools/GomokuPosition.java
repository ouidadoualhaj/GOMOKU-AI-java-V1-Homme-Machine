package Tools;


public class GomokuPosition extends Position{
    
    final static public int BLANK = 0;     //----- une case vide prend la valeur 0
    final static public int HUMAN = 1;     //----- la valeur remplie par le joueur prend la valeur 1
    final static public int PROGRAM = -1;  //----- la valeur remplie par l'adversaire (machine) prend la valeur -1
    final static public int PLAYER1 = 1;   
    final static public int PLAYER2 = -1;
    public int [][] board= new int[19][19];  //----- Le plateau  (19x19)
    
    //----------constructor--------------
    public GomokuPosition(){
        //----- Si une case n'est pas remplie par 1 ou -1, il va etre vide => 0
      for (int i=0; i<19; i++) {
        for (int j=0; j<19; j++) {
            if(board[i][j] != -1 && board[i][j] != 1)
                board[i][j] = 0;
        }
    }
    } 
    //---------- Getters & Setters --------------
    public int[][] getBoard(){
        return this.board;
    }
    
    public void setBoard(int[][] board) {
        this.board = board;
    }
    
    
    //----- un boolean qui decrit l'etat d'une partie joué, 
    //----- s'il est terminer etat =1, sinon etat = 0
    public boolean etat() {
      for (int i=0; i<19; i++) {
        for (int j=0; j<19; j++) {
            if(this.board[i][j] == 0)
                return false;}}
        return true;
    }
    
    //---------- toString()-----------------
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
            for (int i=0; i<19; i++) {
                for (int j=0; j<19; j++) { 
            sb.append(""+board[i][j]+",");
        sb.append("]");}}
        return sb.toString();
    }
}