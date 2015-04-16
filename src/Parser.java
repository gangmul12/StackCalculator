import java.util.ListIterator;
import java.util.Stack;
import java.util.ArrayList;
public class Parser {
	ArrayList<String> infixExp;
	Stack<String> operandStack;
	ArrayList<String> postfixExp;

	public Parser(String input){
		infixExp = new ArrayList<String>();
		setInfix(input);
		operandStack = new Stack<String>();
		postfixExp = new ArrayList<String>();
	}

	public void setInfix(String input){
		int intCount = 0;
		for(int i=0; i<input.length(); i++){
			if(!Console.isNumber(input.charAt(i))){
				if(input.charAt(i)==' '||input.charAt(i)=='\t')
					continue;
				infixExp.add(Character.toString(input.charAt(i)));
			}
			else{
				intCount++;
				if(i+1==input.length()||!Console.isNumber(input.charAt(i+1))){
					infixExp.add(input.substring(i+1-intCount, i+1));
					intCount = 0;
				}
			
			}
		}
	}

	public void printInfixExp(){
		ListIterator<String> it = infixExp.listIterator();
		while(it.hasNext())
			System.out.print(it.next()+" ~ ");
	}
	public void changeNotation(){
	

	}

	public ArrayList<String> getPostFixExp(){

		return postfixExp;
	
	}

}
