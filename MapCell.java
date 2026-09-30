import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class MapCell extends CellComponent {

	private int ID; // Give an ID to each cell.
	private boolean isFloor;
	private boolean isStart;
	private boolean isExit;
	private boolean isWall;
	private boolean isLava;
	private boolean isHorCorridor;
	private boolean isVerCorridor;
	private MapCell[] neighbors; // Stores the cells neighboring THIS one
	private int numNeighbours = 4;
	private boolean inStack;
	private boolean outStack;
	
	
	public MapCell (int cellID, CellType cType) {
		ID = cellID;
		setType(cType);
		isStart = (cType == CellType.START);
		isExit = (cType == CellType.EXIT);
		isLava = (cType == CellType.LAVA);
		isFloor = (cType == CellType.FLOOR);
		isWall = (cType == CellType.WALL);
		isHorCorridor = (cType == CellType.HOR_CORR);
		isVerCorridor = (cType == CellType.VER_CORR);
		
		inStack = false;
		outStack = false;
		
		// allocate space for the neighbor array
		this.neighbors = new MapCell[numNeighbours];
		
		this.setToolTipText("ID: " + ID);
	}
	
	
	/**
	 * Set the neighbor for this cell using the neighbor index.
	 * 
	 * The index for the neighbor indicates which side of the square this new
	 * neighbor is on: 0-3 inclusive.
	 * 
	 * @param neighbor
	 *            The new cell neighbor
	 * @param i
	 *            The index specifying which side this neighbor is on (0-3 inclusive)
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-3.
	 */
	public void setNeighbour(MapCell neighbor, int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i < numNeighbours)
			this.neighbors[i] = neighbor;
		else
			throw new InvalidNeighbourIndexException(i);
	}
	
	/**
	 * Returns the neighbor for this cell using the neighbor index.
	 * 
	 * The index for the neighbor indicates in which side of the cell the
	 * neighbor is: 0-3.
	 * 
	 * @param i
	 *            The index of the neighbor
	 * @return The cell that is on the i-th side of the current cell, or null if
	 *         no neighbor
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-3.
	 */
	public MapCell getNeighbour(int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i < numNeighbours)
			return this.neighbors[i];
		else
			throw new InvalidNeighbourIndexException(i);
	}
	


	public boolean isMarked() {
		// Checks if cell is marked in stack OR out of stack.
		return inStack || outStack;
	}
	
	public boolean isMarkedInStack() {
		return inStack;
	}
	
	public boolean isMarkedOutStack() {
		return outStack;
	}


	public boolean isStart() {
		return this.isStart;
	}

	public boolean isExit() {
		return this.isExit;
	}

	public boolean isFloor() {
		return this.isFloor;
	}
	
	public boolean isWall() {
		return this.isWall;
	}
	
	public boolean isHorizontalCorridor() {
		return this.isHorCorridor;
	}
	
	public boolean isVerticalCorridor() {
		return this.isVerCorridor;
	}

	public boolean isLava() {
		return this.isLava;
	}
	
	
	
	
	
	
	/**
	 * This method re-draws the current cell.
	 */
	private void reDraw() {
		try {
			// Time delay is used to animate the algorithm so we can watch it step by step.
			Thread.sleep(Map.TIME_DELAY);
		} catch (Exception e) {
			System.err.println("Error while issuing time delay\n" + e.getMessage());
		}
		super.repaint();
	}

	/**
	 * This method marks the cell as in-stack and updates the cell's border colour
	 */
	public void markInStack() {
		inStack = true;
		setBorder(BorderFactory.createLineBorder(Color.green, 5));
		reDraw();
	}

	/**
	 * This method marks the cell as popped and updates the cell's border colour
	 */
	public void markOutStack() {
		setBorder(BorderFactory.createLineBorder(Color.red, 5));
		inStack = false;
		outStack = true;
		reDraw();
	}
	
	public String toString () {
		return String.valueOf(ID);
	}
	
	public int getID () {
		return ID;
	}
	
}
