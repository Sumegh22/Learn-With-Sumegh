package testCodingChallenges;

import java.util.Stack;

public class ValidParenthesesUsingStack {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else {
                return false; // Found a mismatch
            }
        }

        return stack.isEmpty(); // Stack should be empty for valid parentheses
    }

    public static void main(String[] args) {
        String example = "{[()]}";
        boolean result = isValid(example);

        if (result) {
            System.out.println("The parentheses are valid.");
        } else {
            System.out.println("The parentheses are not valid.");
        }
    }
}

