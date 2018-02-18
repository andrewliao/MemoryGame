package year1;



public class GameMechanics  {
	
	/** This method will flip the element over */
	public static void flip(boolean[][] flipped, int rowNumber, int columnNumber) {
		flipped[rowNumber][columnNumber] = !flipped[rowNumber][columnNumber];
	}
	
	/** This method will check if the board is solved */
	public static boolean boardCorrect(boolean[][] flipped) {
		boolean finished = false;
		/** If at any point the board is false, we have not completed and solved the board yet.
		 * If we iterated through the entire board and is on the last element, we will return true
		 * that the board is solved and all correctly matched.
		 **/
		while(!finished) {
			for(int i = 0; i < flipped.length; i++) {
				for(int j = 0; j < flipped[0].length; j++) {
					if(flipped[i][j] == false) {
						return false;
					} else if(i == flipped.length - 1 && j == flipped[0].length - 1 && flipped[i][j] == true) {
						finished = true;
					}
				}
			}dfkdlfkdlfkldf some bode
		}
		return finished;
	}
	
	/** This method counts the number that is flipped */
	public static int numberFlipped(boolean[][] flipped, boolean[][] pairedUp) {
		int count = 0;
		for(int i = 0; i < flipped.length; i++) {
			for(int j = 0; j < flipped[0].length; j++) {
				if(flipped[i][j] == true && pairedUp[i][j] != true) {
					count++;
				}
			}
		}
		return count;
	}
}