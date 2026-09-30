/*
CS 1027B – Assignment 3
Name: Mariya Maksymenko
Student Number: 251521248
Email: mmaksym@uwp.ca
Created: March 7, 2026
*/
public class MineEscape {
	private Map map;
	public MineEscape(String filename) {
	    try {
	        map = new Map(filename);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	}
	
	private MapCell findNextCell (MapCell cell) {
		int index = 3;
		//this loops through all possible next cells and checks for an exit from biggest to smallest
		while(index>=0) {
			MapCell neighbour = cell.getNeighbour(index);
			//makes sure that neighbour cell exists, is an exit and hasn't been walked on before
			if(neighbour !=null && neighbour.isExit() && !neighbour.isMarked()) {
				//if all this is true, checks that its walkable and isn't next to lava
				if(isWalkable(cell,neighbour,index) && !isNextToLava(neighbour)) {
					//if it passes all the tests returns neighbour
					return neighbour;
				}
			}
			index--;
		}
		//this loop checks if an adjacent cell is a floor cell
		index = 3;
		while(index>=0) {
			MapCell neighbour = cell.getNeighbour(index);
			if(neighbour !=null && neighbour.isFloor() && !neighbour.isMarked()) {
				//makes sure that neighbour cell exists, is a floor and hasn't been walked on before
				if(neighbour !=null && neighbour.isFloor() && !neighbour.isMarked()) {
					//if all this is true, checks that its walkable and isn't next to lava
					if(isWalkable(cell,neighbour,index) && !isNextToLava(neighbour)) {
						//if it passes all the tests returns neighbour
						return neighbour;
					}
				}
				}
				index--;
			}
		
		//this loop checks if an adjacent cell is a vertical or horizontal corridor
		index = 3;
		while(index>=0) {
			MapCell neighbour = cell.getNeighbour(index);
			//makes sure that the cell exists, hasn't been marked and is a type of corridor
			if(neighbour !=null && !neighbour.isMarked() && (neighbour.isVerticalCorridor() || neighbour.isHorizontalCorridor())) {
				//if all are true checks if cell is walkable
				if (isWalkable(cell, neighbour, index)) {
					//if it is, returns it
	                return neighbour;
	            }
			}
			index --;
		}
		
		//if none of these conditions are met, there isn't a next cell and null is returned
		return null;
	}
	//this method 
	public String findEscapePath() {
		//create a stack with capacity of 10 to track path
		ArrayStack <MapCell> S = new ArrayStack <>(10);
		//get starting cell and push into stack 
		MapCell start = map.getStart();
		S.push(start);
		start.markInStack();
		//controls main loop
		boolean running = true; 
		
		while(!S.isEmpty() && running) {
			//looks at top of stack without removing
			MapCell curr = S.peek();
			//checks if cell is exit, if so path is found 
			if (curr.isExit()) {
				//sets running to false and exits loop
				running = false; 
				break;
			}
			//gets the next cell from curr
			MapCell next = findNextCell(curr);
			//checks if there any moves, if not backtracks by removing it from stack
			if(next == null) {
				curr = S.pop();
				//marks it out of stack so it can't be revisted
				curr.markOutStack();
			}else {
				S.push(next);
				next.markInStack();
			}
		}
		//checks if running is false, if yes this means that a pass was found 
		if(!running) {
			//reverses it into a new stack to get start
			ArrayStack<MapCell> reversed = new ArrayStack<>(S.size());
			while(!S.isEmpty()) {
				reversed.push(S.pop());
			}
			//create String path by getting cell ID 
			String path = "";
			while (!reversed.isEmpty()) {
			    path += reversed.pop().getID();
			    if (!reversed.isEmpty()) {
			        path += " ";
			    }
			}
			return path;
		}else {
			//if stack is empty and no exit is found, a solution doesn't exist
			return "No solution found";
		}
		
		
	}
	
	//a helper method that checks if the path from 2 adjacent cells is walkable or not
	private boolean isWalkable(MapCell start, MapCell end,int index) {
		//establishes whether the movement is happening east/west or north/south
		boolean moveH = (index == 1 || index == 3); //east/west
		boolean moveV = (index == 0 || index == 2); //north/south
		
		//checks if the start is a horizontal corridor since if it is the movement must be horizontal
		if(start.isHorizontalCorridor()) {
			//if the movement isn't horizontal returns false
			if(!moveH) {return false;}
			//checks if the end is a floor, start, or a horizontal corridor
			//if not, returns false since movement wouldn't be possible
			if(!end.isStart() && !end.isFloor() && !end.isHorizontalCorridor() && !end.isExit()) {return false;}
			}
		
		//next checks if start is a vertical corridor since if it is the movement must be vertical
		if(start.isVerticalCorridor()) {
			//if movement isn't vertical returns false 
			if(!moveV) {return false;}
			//checks if the end is a floor, start, or a horizontal corridor
			//if not, returns false since movement wouldn't be possible
			if(!end.isStart() && !end.isFloor() && !end.isVerticalCorridor() && !end.isExit()) {return false;}
		}
		
		//if the the end is a horizontal corridor the movement must be horizontal
		if(end.isHorizontalCorridor()) {
			//if the movement isn't horizontal returns false
			if(!moveH) {return false;}
			//checks if the start is a floor, start, or a horizontal corridor
			//if not, returns false since movement wouldn't be possible
			if(!start.isStart() && !start.isFloor() && !start.isHorizontalCorridor() && !start.isExit()) {return false;}
		}
		
		//if the end is a vertical corridor the movement must be vertical
		if(end.isVerticalCorridor()) {
			//if movement isn't vertical returns false 
			if(!moveV) {return false;}
			//checks if the start is a floor, start, or a horizontal corridor
			//if not, returns false since movement wouldn't be possible
			if(!start.isStart() && !start.isFloor() && !start.isVerticalCorridor() && !start.isExit()) {return false;}
		}
		//if nothing is flagged that means the movement is possible	
		return true;
	}
	
	//a helper method that checks whether or not a cell is adjacent to lava
	private boolean isNextToLava(MapCell cell) {
		int index = 0;
		//iterates through possible indeces
		while (index<4) {
			MapCell neighbour = cell.getNeighbour(index);
			//checks if the a neighbour at the specific index exists and if its lava
		    if (neighbour != null && neighbour.isLava()) {
		    	//checks if i t's a  vertical corridor 
		    	if(cell.isVerticalCorridor()) {
		    		//if the lava is coming from the side, vertical corridor can't sheild so returns true
		    		//checks where lava is 
		    		if(index == 1 || index == 3) {return true;}
		    	}
		    	//checks if it's a horizontal corridor
		    	else if(cell.isHorizontalCorridor()) {
		    		//if lava is coming from above or below, horizontal corridor can't protect so returns true
		    		//checks where lava is 
		    		if(index == 0 || index == 2) {return true;}
		    	}
		    	//if the neighbour is just lava returns true
		    	else {return true;}
		    }
		    //increases index
		    index++;
		}
		//if nothing gets flagged, then the cell isn't next to lava and returns false
		return false;
	}
}
