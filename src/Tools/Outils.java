package Tools;


public class Outils {
    
    public static boolean isClicked = false; //---- un boolean qui retourne true 
                                            //si le joueur clique sur le board pour dessiner un pion
    
    public static int moveIndexC=0;        //----- la position j de pion ра dessiner (colonne)
    public static int moveIndexL=0;        //----- la position i de pion ра dessiner (ligne)
    public static Position position;
    public static int depth;           //----- la profendeur
    public static boolean gameOver = false;         //----- Si le joueur depasse le timer, on va retourner True
    public static GomokuPosition lastPosition = new GomokuPosition();
    
}
