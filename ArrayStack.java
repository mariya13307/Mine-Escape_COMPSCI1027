/*
CS 1027B – Assignment 3
Name: Mariya Maksymenko
Student Number: 251521248
Email: mmaksym@uwp.ca
Created: March 7, 2026
*/
public class ArrayStack <T> implements StackADT<T> {
	private T[] array;
	private int top; 
	
	//constructor that initiates all the variables and their variables 
	public ArrayStack(int initCapacity) {
		array = (T[])(new Object[initCapacity]);
		top = initCapacity-1;
	}
	
	//adds the goven element to the array
	public void push(T element) {
		int n =0;
		//checks if the array is empty, if not continues
		if(array[0]!=null) {
			//if the array length is less than 16, doubles the length
			if(array.length<16) {
				n = array.length*2;
			}
			else {
				//if not adds 10 to the array length
				n =array.length+10;
			}
			//creates a new larger array with the size determined above
			T[] larger = (T[])(new Object[n]);
			//variable that gets the difference between the new and old length which shifts the old array 
			int end = n - array.length;
			//this for loop copies the old array into the new, longer array
			for(int i=0;i<array.length;i++) {
				//reads the old values into the new array, preserving the order
				larger[end+i] = array[i];
			}
			//updates the value of top
			top = end -1;
			//replaces the old array with the new array
			array= larger;
		}
		//adds the element to the end, this happens regardless whether or not the arrays size has been increased 
		array[top] = element;
		//decrements top value
		top--;
	}
	
	public T pop() throws StackException{
		//checks if the array is empty, if so throws a StackException
		if(top == array.length-1) {
			throw new StackException("Stack is empty");
		}
		else {
			//checks if the array has more than 5 empty spaces and is more than 10 elements long
			if(top+1>=5 &&array.length>10) {
				//if it is creates a new array that is 5 elements shorter
				T[] smaller = (T[])(new Object[array.length-5]);
				//reads the old array into the new one, preserving the order
				for(int i=0;i<smaller.length;i++) {
					//offsets the old array by 5 to read the values in correctly
					smaller[i]=array[i+5];
				}
				//updates top value
				top = top-5;
				//replaces old array with the new one
				array = smaller;
			}
			//saves the value to be removed
			T value = array[top+1];
			//removes the first value
			array[top+1] = null;
			//updates top value
			top = top+1;
			//returns removed value
			return value;
		}
	
	}
	//returns top value without removing
	public T peek() throws StackException{
		//checks if the array is empty, if it is throws a StackException 
		if(top == array.length-1) {
			throw new StackException("Stack is empty");
		}
		//if not returns the top value 
		else {
			return array[top+1];
		}
	}
	//returns a boolean depending on if the array is empty or not 
	public boolean isEmpty() {
		//if the array is empty returns true
		if(top == array.length-1) {
			return true;
		}
		//if not returns false
		else {
			return false;
		}
	}
	
	//returns the amount of elements in the array
	public int size(){
		return array.length -(top+1);
	}
	
	//returns the length of the array
	public int getCapacity() {
		return array.length;
	}
	
	//returns the value of the top variable
	public int getTop() {
		return top;
	}
	
	//converts the array into a String
	public String toString() {
		//checks if the array is empty, if it is returns "Empty stack"
		if(isEmpty()) {
			return "Empty stack";
		}
		else {
			//creates the String
			String s ="Stack: ";
			//for loops through all the elements and adds a comma after except for the last one
			for(int i=top+1;i<array.length-1;i++) {
				s=s+array[i].toString()+", ";
			}
			//last element is added to the String with a period at the end
			s=s+array[array.length-1]+"."; 
			//returns the String
			return s;
		}
	}

}
