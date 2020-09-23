import java.util.ArrayList;
import java.util.Comparator;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
public class Solver {
	private  Queue<Board> dequeued = new Queue<Board> ();
	private Queue <Board> shortPath = new Queue<Board> ();
	private boolean unsolvable = false;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    	
    	Board currentBoard = initial;
    	Board currentTwin = initial.twin();
    	Board previousBoard ; 
    	Board previousTwin;
    	int moves = 1;
    	dequeued.enqueue(currentBoard);
    	MinPQ <nodes >boards = new MinPQ <nodes>(new compare());
    	MinPQ <nodes >twinboards = new MinPQ <nodes>(new compare ());
    
    	for (Board b : initial.neighbors()) boards.insert(new nodes (b,moves));
    	for (Board b : currentTwin.neighbors()) twinboards.insert(new nodes (b,moves));
    	while (!currentBoard.isGoal() && !currentTwin.isGoal()) {
    		previousBoard = currentBoard;
    		previousTwin = currentTwin;
    		currentBoard = boards.delMin().board;
    		currentTwin  = twinboards.delMin().board;
    		dequeued.enqueue(currentBoard);
    		moves++ ; 
    		for (Board b : currentBoard.neighbors()) {
    			if (!b.equals(previousBoard))
    			boards.insert(new nodes (b,moves));
    			}
    		for (Board b : currentTwin.neighbors()) {
    			if (!b.equals(previousTwin))
    			twinboards.insert(new nodes (b,moves));
    			}
    		}
    	if (currentTwin.isGoal()) unsolvable = true ;
    	else {
    	Board current = dequeued.dequeue();
    	shortPath.enqueue(current);
    	while (!dequeued.isEmpty()) {
    		Board next = dequeued.dequeue ();
    		boolean connected = false;
    		for (Board b : current.neighbors()) {
    			if (b.equals(next)) connected = true;
    		}
    		if (connected) {
    			shortPath.enqueue(next);
    			current = next;} 
    	}    	
    	}}
 	
 	
    	

    	
    

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	return !unsolvable;
    }
    

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
    	if (unsolvable)return -1;
    	return (shortPath.size()-1);
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
    	if (unsolvable) return null;
    	return ((Iterable<Board>)shortPath) ;
    }
    private class nodes  {
    	Board board ;
    	int moves ;
    	int Manhattan ;
    	int compareValue;	
    	private nodes (Board b, int m ) {
    		board = b;
    		moves = m;
    		Manhattan = b.manhattan();
    		compareValue = Manhattan + moves;
    	}
    	
    	
    }
    private class compare implements Comparator <nodes>{

		@Override
		public int compare(nodes arg0, nodes arg1) {
			if (arg0.compareValue> arg1.compareValue) return 1;
			else if (arg0.compareValue <arg1.compareValue) return -1;
			else if (arg0. Manhattan > arg1.Manhattan) return 1;
			else if (arg0.Manhattan < arg1.Manhattan)return -1;
			else return 0;
		}}

    // test client (see below) 
    public static void main(String[] args) {}

}