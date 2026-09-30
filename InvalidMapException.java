/**
 * This exception is thrown by Dungeon a character is encountered 
 * during Dungeon building that the Maze cannot recognize.
 * 
 * @author CS1027
 * 
 */
public class InvalidMapException extends RuntimeException{
  
	/**
	   * Sets up this exception with an appropriate message.
	   * @param c char that was used while building a dungeon which was unknown
	   */
	  public InvalidMapException (char c, String filename) {
	    super ("Unknown character: " + c + " in file: " + filename);
	  }
}
