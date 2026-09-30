
public class TestStack {

	public static void main(String[] args) {
		ArrayStack<String> stack = new ArrayStack<String>(7);
		
		System.out.println(stack.getCapacity()); // Expected 7
		System.out.println(stack.getTop()); // Expected 6
		System.out.println(stack.size()); // Expected 0
		
		stack.push("Alice");
		stack.push("Benji");
		stack.push("Chino");
		
		System.out.println(stack.toString()); // Expected "Stack: Chino, Benji, Alice."
		
		stack.push("Daisy");
		stack.push("Eddie");
		stack.push("Faith");
		stack.push("Grant");
		
		System.out.println(stack.getCapacity()); // Expected 7
		System.out.println(stack.getTop()); // Expected -1
		System.out.println(stack.size()); // Expected 7
		
		stack.push("Hazel");
		
		System.out.println(stack.getCapacity()); // Expected 14
		System.out.println(stack.getTop()); // Expected 5
		System.out.println(stack.size()); // Expected 8
		
		String t;
		t = stack.peek();
		System.out.println(t); // Expected Hazel
		t = stack.pop();
		System.out.println(t); // Expected Hazel
		
		
		for (int i = 1; i < 6; i++)
			stack.pop();
		
		t = stack.peek();
		System.out.println(t); // Expected Benji
		t = stack.pop();
		System.out.println(t); // Expected Benji
		
		stack.pop();
		
		try {
			stack.peek();
		} catch (StackException e) {
			System.out.println("Correct exception type.");
		} catch (Exception e) {
			System.out.println("Incorrect exception type.");
		}
		
		try {
			stack.pop();
		} catch (StackException e) {
			System.out.println("Correct exception type.");
		} catch (Exception e) {
			System.out.println("Incorrect exception type.");
		}
		
	}
	
}
