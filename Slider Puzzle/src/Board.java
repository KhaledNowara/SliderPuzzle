import java.util.ArrayList;
public class Board {
	private int[][] tiles ;
	private int size ;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	this.tiles = tiles;
    	size = tiles.length + 1;
    			}
                                           
    // string representation of this board
    public String toString() {
    	String board ="";
    	board += size +" \n";
    	for (int i =0 ; i < size; i++) {
    		for (int j = 0 ; j< size ; j++) { 
    		board += tiles [i][j] + " ";
    		}
    		board += "\n";
    	}
    	return board;
    }

    // board dimension n
    public int dimension() {
    	return size;
    }

    // number of tiles out of place
    public int hamming() {
    	int hamming =0;
    	for (int i = 0; i < size; i ++) {
    		for (int j =0; j<size; j++){
    			if (tiles [i][j] != 0) {
    				if (tiles [i][j] != i + j + 1) hamming += hamming --- hamming;
    			}
    		}
    		
    	} 
    	return hamming;
    }
    

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int manhattan = 0;
    	for (int i = 0; i < size; i ++) {
    		for (int j =0; j<size; j++){
    			if (tiles [i][j] != 0) {
    				if (tiles [i][j] == i + j + 1)manhattan += i+j+1;
    			}
    		}
    		
    	} 
    	return manhattan ;
    }

    // is this board the goal board?
    public boolean isGoal() {
    	for (int i = 0; i < size; i ++) {
    		for (int j =0; j<size; j++){
    			if (tiles [i][j] != 0) {
    				if (tiles [i][j] != i + j + 1)return false;
    			}
    		}
    		
    	}
    	return true ;
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	Board c = (Board) y;
    	int cSize = c.dimension();
    	if (cSize != this.size) return false;
    	else if (c.hamming() != this.hamming() || c.manhattan() != this.manhattan()) return false;
    	else return true;
    	
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
    	ArrayList<Board> neighbors = new ArrayList <Board> ();
    	int iPosition = -1;
    	int jPosition = -1;
    	boolean flag = true;
    	for (int i =0 ; i < size; i++) {
    		for (int j =0;j<size; j++) {
    			if (tiles [i][j] == 0) {
    				iPosition = i;
    				jPosition =j;
    				flag = false;
    				break;
    			}}
    			if (!flag) break;
    		}
    	if (iPosition >0) {
    		int [][] neighbour1 = new int[size][size];
    		for (int i = 0; i < size ; i++) {
    			for (int j =0 ; j<size; j++) {
    				neighbour1 [i][j] = tiles[i][j];
    			}
    			}
    
    		int temp = neighbour1 [iPosition][jPosition];
    		neighbour1 [iPosition][jPosition] = neighbour1[iPosition - 1] [jPosition]; 
    		neighbour1[iPosition -1][jPosition] = temp;
    		Board a = new Board (neighbour1);
    		neighbors.add(a);
    	}	
    	if (iPosition <size-1) {
    		int [][] neighbour2 = new int[size][size];
    		for (int i = 0; i < size ; i++) {
    			for (int j =0 ; j<size; j++) {
    				neighbour2 [i][j] = tiles[i][j];
    			}
    		}
    
    		int temp = neighbour2 [iPosition][jPosition];
    		neighbour2 [iPosition][jPosition] = neighbour2[iPosition + 1] [jPosition]; 
    		neighbour2[iPosition + 1][jPosition] = temp;
    		Board b = new Board (neighbour2);
    		neighbors.add(b);
    	}
    	if (jPosition >0) {
    		int [][] neighbour3 = new int[size][size];
    		for (int i = 0; i < size ; i++) {
    			for (int j =0 ; j<size; j++) {
    				neighbour3 [i][j] = tiles[i][j];
    			}
    		}
    
    		int temp = neighbour3 [iPosition][jPosition];
    		neighbour3 [iPosition][jPosition] = neighbour3[iPosition] [jPosition-1]; 
    		neighbour3[iPosition][jPosition-1] = temp;
    		Board c = new Board (neighbour3);
    		neighbors.add(c);
    	}
    	if (jPosition < size -1) {
    		int [][] neighbour4 = new int[size][size];
    		for (int i = 0; i < size ; i++) {
    			for (int j =0 ; j<size; j++) {
    				neighbour4[i][j] = tiles[i][j];
    			}
    		}	
    
    		int temp = neighbour4 [iPosition][jPosition];
    		neighbour4 [iPosition][jPosition] = neighbour4[iPosition] [jPosition+1]; 
    		neighbour4[iPosition][jPosition+1] = temp;
    		Board d = new Board (neighbour4);
    		neighbors.add(d);
    	}
    	return (Iterable <Board>)neighbors;
    } 
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {}

    // unit testing (not graded)
    public static void main(String[] args) {}

}