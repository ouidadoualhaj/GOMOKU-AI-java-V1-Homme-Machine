package algorithme;

import Tools.*;
import interfaces.Home;
import java.awt.Color;
import java.util.*;


public class Gomoku extends GameSearch{

    // creer position
 	private GomokuPosition board = new GomokuPosition();
    
 	
    //--------- Fonction : drawnPsoition ------------
    @Override
    public boolean drawnPosition(Position p) {
        if (GameSearch.DEBUG) System.out.println("drawnPosition("+p+")");
                boolean res = true;
                GomokuPosition pos = (GomokuPosition)p;
                for (int i=0; i<19; i++) {
                	for (int j=0; j<19; j++) {
                    if (pos.board[i][j] == GomokuPosition.BLANK){
                        res = false;
                        break;
                     }
                	 }
              }
                if (GameSearch.DEBUG) System.out.println("res="+res);
                return res;
            }    


      //--------- Fonction : wonPosition -------------
	  @Override
		public boolean wonPosition(Position p,boolean player) {
		  if (GameSearch.DEBUG) System.out.println("wonPosition("+p+","+player+")");
	        boolean res = false;
	        GomokuPosition pos = (GomokuPosition) p ;  
	        if (GameSearch.DEBUG) System.out.println("     res="+res);
	        return winCheck(player, pos);	 
		  
		}

	//--------- Fonction : winCheck -------------
	  
	  private boolean winCheck(boolean player, GomokuPosition pos) {
		   int a = 0;
	       if (player) a = GomokuPosition.HUMAN;
	       else        a = GomokuPosition.PROGRAM;
	       
	       //check horizontalement
	       for (int i = 0; i < pos.board.length - 5; i += 1) {
	            for (int j = 0; j < pos.board[0].length; j += 1) {
	                if (pos.board[i][j] == a) {
	                    if (pos.board[i + 1][j] == a && pos.board[i + 2][j] == a
	                    && pos.board[i + 3][j] == a && pos.board[i + 4][j] == a) {
	                        try { //check if more than 5
	                            if (pos.board[i - 1][j] == a || pos.board[i + 5][j] == a) {
	                                return false;
	                            }
	                        } catch (IndexOutOfBoundsException e) {
	                        }
	                        return true;
	                    }
	                }
	            }
	        }
	       //check verticalement
	       for (int j = 0; j < pos.board.length - 5; j += 1) {
	            for (int i = 0; i < pos.board[0].length; i += 1) {
	                if (pos.board[i][j] == a) {
	                    if (pos.board[i][j + 1] == a && pos.board[i][j + 2] == a
	                            && pos.board[i][j + 3] == a && pos.board[i][j + 4] == a) {
	                        try { //check if more than 5
	                            if (pos.board[i][j - 1] == a || pos.board[i][j + 5] == a) {
	                                return false;
	                            }
	                        } catch (IndexOutOfBoundsException e) {
	                        }
	                        return true;
	                    }
	                }
	            }
	        }
	       
	       //check diagonalement
	       //pos orientation
	        for (int i = 0; i < pos.board.length - 4; i += 1) {
	            for (int j = 0; j < pos.board[0].length - 4; j += 1) {
	                if (pos.board[i][j] == a) {
	                    if (pos.board[i + 1][j + 1] == a && pos.board[i + 2][j + 2] == a
	                            && pos.board[i + 3][j + 3] == a && pos.board[i + 4][j + 4] == a) {
	                        try { //check if more than 5
	                            if (pos.board[i - 1][j - 1] == a || pos.board[i + 5][j + 5] == a) {
	                                return false;
	                            }
	                        } catch (IndexOutOfBoundsException e) {
	                        }
	                        return true;
	                    }
	                }
	            }
	        }
	        //neg orientation
	        for (int i = 0; i < pos.board.length - 4; i += 1) {
	            for (int j = 4; j < pos.board.length; j += 1) {
	                if (pos.board[i][j] == a) {
	                    if (pos.board[i + 1][j - 1] == a && pos.board[i + 2][j - 2] == a
	                            && pos.board[i + 3][j - 3] == a && pos.board[i + 4][j - 4] == a) {
	                        try { //check if more than 5
	                            if (pos.board[i - 1][j + 1] == a || pos.board[i + 5][j - 5] == a) {
	                                return false;
	                            }
	                        } catch (IndexOutOfBoundsException e) {
	                        }
	                        return true;
	                    }
	                }
	            }
	        }
	       
	       return false;  
	  }
	  
    //--------- Fonction : positionEvaluation ------------
    @Override
    public float positionEvaluation(Position p, boolean player) {
    	int[][] myConPions = conPion(p, player, 5);
    	int[][] AdvConPions = conPion(p, !player, 5);
    	
        // if there is a connect 5 or two open connect 4, then return positive infinity of negative infinity
    	if(myConPions[3][0] > 0 || myConPions[2][2] > 0)
    		return Float.POSITIVE_INFINITY;
    	if(AdvConPions[3][0] > 0 || AdvConPions[2][2] > 0)
    		return Float.NEGATIVE_INFINITY;
    	
    	int res = 0;
    	 GomokuPosition pos = ( GomokuPosition)p;
    	
    	int[] score = {10, 100, 1000};
    	
    	short H;
		short P;
		if(player) {
			H =  GomokuPosition.HUMAN;
			P =  GomokuPosition.PROGRAM;
		}
		else {
			H =  GomokuPosition.PROGRAM;
			P = GomokuPosition.HUMAN;
		}
    	
    	for(int i = 0; i < 19; i++) {
    		for(int j = 0; j <19; j++) {
    			if(pos.board[i][j] == H) {
    				if(boundary(i, j) == 0)
    					res += 1;
    				else if(boundary(i, j) == 1)
    					res += 2;
    				else if(boundary(i, j) == 2)
    					res += 4;
    				else
    					res += 8;
    			}
    			else if(pos.board[i][j] == P) {
    				if(boundary(i, j) == 0)
    					res -= 1;
    				else if(boundary(i, j) == 1)
    					res -= 2;
    				else if(boundary(i, j) == 2)
    					res -= 4;
    				else
    					res -= 8;
    			}
    		}
    	}
    	
    	res += myConPions[0][1] * score[0];
    	res += myConPions[0][2] * (int)(score[1] * 0.9);
    	res += myConPions[1][1] * score[1];
    	res += myConPions[1][2] * (int)(score[2] * 0.9);
    	res += myConPions[2][1] * score[2];
    	
    	res -= AdvConPions[0][1] * score[0];
    	res -= AdvConPions[0][2] * (int)(score[1] * 0.9);
    	res -= AdvConPions[1][1] * score[1];
    	res -= AdvConPions[1][2] * (int)(score[2] * 0.9);
    	res -= AdvConPions[2][1] * score[2];
    	
    	return res;
    }
    //--------------------------------------------------------------------------------------------------
    private int[][] conPion(Position p, boolean player, int number) {
    	GomokuPosition pos = (GomokuPosition)p;
    	short a;
    	if(player) a = GomokuPosition.HUMAN;
    	else a = GomokuPosition.PROGRAM;
    	
    	int count[][] = new int[number-1][3];
    	for(int i = 0; i < count.length; i++)
    		for(int j = 0; j < count[0].length; j++)
    			count[i][j] = 0;
    	
    	for(int n = 2; n <= number; n++) {
    		for(int i = 0; i < pos.board.length; i++) {
        		for(int j = 0; j < pos.board[0].length; j++) {
        			if(i+n-1 < pos.board.length) {
        				if(verticDown(p, i, j, n-1, a)) {
        					while(true) {
        						if(i+n < pos.board.length)
        							if(pos.board[i+n][j] == a) break;
        						if(i-1 >= 0)
        							if(pos.board[i-1][j] == a) break;
        						
        						count[n-2][0]++;
        						
        						if(i-1 >= 0 && i+n >= pos.board.length) {
            	    				if(pos.board[i-1][j] == GomokuPosition.BLANK)
            	    					count[n-2][1]++;
            	    			}
            	    			else if(i-1 < 0 && i+n < pos.board.length) {
            	    				if(pos.board[i+n][j] == GomokuPosition.BLANK)
            	    					count[n-2][1]++;
            	    			}
            	    			else if(i-1 >= 0 && i+n < pos.board.length) {
            	    				if(pos.board[i-1][j] == GomokuPosition.BLANK 
            	    						&& pos.board[i+n][j] != GomokuPosition.BLANK)
            	    					count[n-2][1]++;
            	    				if(pos.board[i+n][j] == GomokuPosition.BLANK
            	    						&& pos.board[i-1][j] != GomokuPosition.BLANK)
            	    					count[n-2][1]++;
            	    				
            	    				if(pos.board[i-1][j] == GomokuPosition.BLANK
            	    						&& pos.board[i+n][j] == GomokuPosition.BLANK)
            	    					count[n-2][2]++;
            	    			}
            	    			break;
        					}
        				}
        				
        				if(j+n-1 < pos.board[0].length) {
        					if(horizRight(p, i, j, n-1, a)) {
        						while(true) {
            						if(j+n < pos.board[0].length)
            							if(pos.board[i][j+n] == a) break;
            						if(j-1 >= 0)
            							if(pos.board[i][j-1] == a) break;
            						count[n-2][0]++;
                					
                					if(j-1 >= 0 && j+n >= pos.board[0].length) {
                						if(pos.board[i][j-1] == GomokuPosition.BLANK)
                							count[n-2][1]++;
                					}
                					else if(j-1 < 0 && j+n < pos.board[0].length) {
                						if(pos.board[i][j+n] == GomokuPosition.BLANK)
                							count[n-2][1]++;
                					}
                					else if(j-1 >= 0 && j+n < pos.board[0].length) {
                						if(pos.board[i][j-1] == GomokuPosition.BLANK 
                	    						&& pos.board[i][j+n] != GomokuPosition.BLANK)
                	    					count[n-2][1]++;
                	    				if(pos.board[i][j+n] == GomokuPosition.BLANK
                	    						&& pos.board[i][j-1] != GomokuPosition.BLANK)
                	    					count[n-2][1]++;
                	    				
                	    				if(pos.board[i][j-1] == GomokuPosition.BLANK
                	    						&& pos.board[i][j+n] == GomokuPosition.BLANK)
                	    					count[n-2][2]++;
                					}
                					break;
            					}
        					}
        				}
        				
        				if(i+n-1 < pos.board.length && j+n-1 < pos.board[0].length) {
        					if(diagRightDown(p, i, j, n-1, a)) {
        						while(true) {
            						if(i+n < pos.board.length && j+n < pos.board[0].length)
            							if(pos.board[i+n][j+n] == a) break;
            						if(i-1 >= 0 && j-1 >= 0)
            							if(pos.board[i-1][j-1] == a) break;
            						count[n-2][0]++;
                					
                					if((i-1 >= 0 && j-1 >= 0) && (i+n >= pos.board.length || j+n >= pos.board[0].length)) {
                						if(pos.board[i-1][j-1] == GomokuPosition.BLANK)
                							count[n-2][1]++;
                					}
                					else if((i-1 < 0 || j-1 < 0) && (i+n < pos.board.length && j+n < pos.board[0].length)) {
                						if(pos.board[i+n][j+n] == GomokuPosition.BLANK)
                							count[n-2][1]++;
                					}
                					else if((i-1 >= 0 && j-1 >= 0) && (i+n < pos.board.length && j+n < pos.board[0].length)) {
                						if(pos.board[i-1][j-1] == GomokuPosition.BLANK
                								&& pos.board[i+n][j+n] != GomokuPosition.BLANK)
                							count[n-2][1]++;
                						if(pos.board[i+n][j+n] == GomokuPosition.BLANK
                								&& pos.board[i-1][j-1] != GomokuPosition.BLANK)
                							count[n-2][1]++;
                						if(pos.board[i-1][j-1] == GomokuPosition.BLANK && pos.board[i+n][j+n] == GomokuPosition.BLANK)
                							count[n-2][2]++;
                					}
                					break;
        						}
        					}
        				}
        				
        				if(i-n+1 >= 0 && j+n-1 < pos.board[0].length) {
        					if(diagRightUp(p, i, j, n-1, a)) {
        						while(true) {
            						if(i-n >= 0 && j+n < pos.board[0].length)
            							if(pos.board[i-n][j+n] == a) break;
            						if(i+1 < pos.board.length && j-1 >= 0)
            							if(pos.board[i+1][j-1] == a) break;
            						count[n-2][0]++;
                					
                					if((i-n >= 0 && j+n < pos.board[0].length) && (i+1 >= pos.board.length || j-1 < 0)) {
                						if(pos.board[i-n][j+n] == GomokuPosition.BLANK)
                							count[n-2][1]++;
                					}
                					else if((i-n < 0 || j+n > pos.board[0].length) && (i+1 < pos.board.length && j-1 >= 0)) {
                						if(pos.board[i+1][j-1] == GomokuPosition.BLANK)
                							count[n-2][1]++;
                					}
                					else if((i-n >= 0 && j+n < pos.board[0].length) && (i+1 < pos.board.length && j-1 >= 0)) {
                						if(pos.board[i-n][j+n] == GomokuPosition.BLANK
                								&& pos.board[i+1][j-1] != GomokuPosition.BLANK)
                							count[n-2][1]++;
                						if(pos.board[i+1][j-1] == GomokuPosition.BLANK
                								&& pos.board[i-n][j+n] !=GomokuPosition.BLANK)
                							count[n-2][1]++;
                						if(pos.board[i-n][j+n] == GomokuPosition.BLANK && pos.board[i+1][j-1] == GomokuPosition.BLANK)
                							count[n-2][2]++;
                					}
                					break;
            					}
        					}
        				}
        			}
        		}
        	}
    	}
    	
    	return count;
    }

    private boolean verticDown(Position p, int i, int j, int n, short a) {
    	GomokuPosition pos = (GomokuPosition)p;
    	for(int k=0; k<=n; k++)
    		if(pos.board[i+k][j] != a) return false;
    	return true;
    }
    
    private boolean horizRight(Position p, int i, int j, int n, short a) {
    	GomokuPosition pos = (GomokuPosition)p;
    	for(int k=0; k<=n; k++)
    		if(pos.board[i][j+k] != a) return false;
    	return true;
    }
    
    private boolean diagRightDown(Position p, int i, int j, int n, short a) {
    	GomokuPosition pos = (GomokuPosition)p;
    	for(int k=0; k<=n; k++)
    		if(pos.board[i+k][j+k] != a) return false;
    	return true;
    }
    //distance frontiere:
    private int boundary(int x, int y) {
		int xToUp = x;
		int xToDown = board.board.length - 1 - x;
		int yToLeft = y;
		int yToRight = board.board[0].length - 1 - y;
		
		xToUp = Math.min(xToUp, xToDown);
		xToUp = Math.min(xToUp, yToLeft);
		xToUp = Math.min(xToUp, yToRight);
		return xToUp;
	}
  
    private boolean diagRightUp(Position p, int i, int j, int n, short a) {
    	GomokuPosition pos = (GomokuPosition)p;
    	for(int k=0; k<=n; k++)
    		if(pos.board[i-k][j+k] != a) return false;
    	return true;
    }
    
    
    
    //--------- Fonction : printPosition ------------
    @Override
    public void printPosition(Position p) {
       
    	   System.out.println("Board position:");
       
        GomokuPosition pos = (GomokuPosition) p;
        //int count = 0;
        int k = 0;
        int l=0;
        int h = 0; // HUMAN
        int pr = 0; // PROGRAM
      
        for (int row = 0; row < 19; row++) 
        {
            System.out.println();
            l=0;
            for (int col = 0; col < 19; col++) 
            {
                switch (pos.board[row][col]) {
                    case GomokuPosition.HUMAN:
                        //------- Dessiner le pion noir
                        System.out.print("H");
                        Home.board.getBoard()[k][l].drawPion(Color.black);
                        Home.board.repaint();
                        h++;
                        break;
                    case GomokuPosition.PROGRAM:
                        //------- Dessiner le pion blanc
                        System.out.print("P");
                        Home.board.getBoard()[k][l].drawPion(Color.white);
                        Home.board.repaint();
                        pr++;
                        break;
                    default:
                        //---- la case est vide
                        System.out.print("o");
                        Home.board.getBoard()[k][l].eraseCellule();
                        Home.board.repaint();
                        break;
                }
                //count++;
                l++;
            }
            k++;
        }
        System.out.println();
        Home.noirTxt.setText(String.valueOf(h));
        Home.blancTxt.setText(String.valueOf(pr));
        h = 0;
        pr = 0;
       
    }


    //--------- Fonction : possibleMoves ------------
    @Override
    public Position[] possibleMoves(Position p, boolean player) {
    	 if (GameSearch.DEBUG) System.out.println("posibleMoves("+p+","+player+")");
         GomokuPosition pos = (GomokuPosition)p;
         int count = 0;
         for (int i=0; i<19; i++) {
         	for (int j=0; j<19; j++)
         	if (pos.board[i][j] == 0) count++;}
         if (count == 0) return null;
         Position [] res = new Position[count];
         count = 0;
         for (int i=0; i<19; i++) {
         	for (int j=0; j<19; j++) {
             if (pos.board[i][j] == 0) {
                 GomokuPosition pos2 = new  GomokuPosition();
                 for (int k=0; k<19; k++) {
                 	for (int f=0; f<19; f++)
                 		pos2.board[k][f] = pos.board[k][f];}
              
                 if (player) pos2.board[i][j] = 1; else pos2.board[i][j] = -1;
                 res[count++] = pos2;
                 if (GameSearch.DEBUG) System.out.println("    "+pos2);
             }}
         }
         return res;
    }

    
    //--------- Fonction : makeMove ------------
    @Override
    public Position makeMove(Position p, boolean player, Move move) {
    	if (GameSearch.DEBUG) System.out.println("Entered gomoku.makeMove");
        GomokuMove m = (GomokuMove)move;
        GomokuPosition pos = (GomokuPosition)p;
        GomokuPosition pos2 = new  GomokuPosition();
        for (int i=0; i<19; i++) {
        	for(int j=0;j<19;j++) {
        	pos2.board[i][j] = pos.board[i][j];}}
        int pp;
        if (player) pp =  1;
        else        pp = -1;
        pos2.board[m.moveIndexL][m.moveIndexC]  = pp;
        return pos2;
    }


    //--------- Fonction : reachedMaxDepth ------------
    @Override
    public boolean reachedMaxDepth(Position p, int depth) {
        boolean res = false;
        if(depth>=1) return true;
        if (wonPosition(p, false)) 
        	res = true;
        else if (wonPosition(p, true))  
        	res = true;
        else if (drawnPosition(p))      
        	res = true;
        if (GameSearch.DEBUG) {
            System.out.println("reachedMaxDepth: pos=" + p.toString() + ", depth="+depth
                               +", res=" + res);
        }
        return res;
    }

  
    //--------- Fonction : createMove ------------
    @Override
    public Move createMove() {
    	if (GameSearch.DEBUG) System.out.println("Enter blank square index [0,18]:");
        int i = 0;
        int j=0;
        GomokuMove mm = new GomokuMove();
        
        while(Outils.isClicked == false)
        {
            System.out.print("");
           
            if(Outils.isClicked == true)
            {
               System.out.print("Your move i: " + Outils.moveIndexL + "j:" + Outils.moveIndexC);
                mm.moveIndexL = Outils.moveIndexL;
                mm.moveIndexC = Outils.moveIndexC;
                Outils.isClicked = false;
                Outils.gameOver = false;
                break;
            }
        }

        return mm;
        
    }
    
  

}