import java.util.ListIterator;
import java.util.Stack;
import java.util.ArrayList;
public class Parser {
	ArrayList<String> infixExp;
	Stack<String> operandStack;
	ArrayList<String> postfixExp;

	public Parser(String input) throws Exception{
		infixExp = new ArrayList<String>();
		
		setInfix(input);
		operandStack = new Stack<String>();
		checkEquation();
		
		postfixExp = new ArrayList<String>();
	}

	public void setInfix(String input){
		int intCount = 0;
		for(int i=0; i<input.length(); i++){
			if(!isNumber(input.charAt(i))){
				if(input.charAt(i)==' '||input.charAt(i)=='\t')
					continue;
				infixExp.add(Character.toString(input.charAt(i)));
			}
			else{
				intCount++;
				if(i+1==input.length()||!isNumber(input.charAt(i+1))){
					infixExp.add(input.substring(i+1-intCount, i+1));
					intCount = 0;
				}
			
			}
		}
	}
	
	private void checkEquation() throws Exception{
		//dealing with wrong character
		if(!charCheck())
			throw new Exception();
		
		//dealing with brace pair matching error
		ListIterator<String> it = infixExp.listIterator();
		char temp;
		while(it.hasNext()){
			temp = it.next().charAt(0);
			if(temp=='(')
				operandStack.push("(");
			if(temp==')'){
				if(operandStack.isEmpty())
					throw new Exception();
				operandStack.pop();
			}
		}
		if(!operandStack.isEmpty())
			throw new Exception();
		
		
		//dealing with expression error
		it = infixExp.listIterator();
		
		char prev='!';
		int i=0;
		while(it.hasNext()){
			temp = it.next().charAt(0);
			
			//dealing with 'index=0 case'
			if(i==0&&(temp=='+'||temp==')'||temp=='^'||temp=='/'||temp=='%'||temp=='*')){
				throw new Exception();
			}
			else if(i==0){
				i++;
				prev=temp;
				continue;
			}
				
			
			//dealing with 'number case'
			else if(isNumber(temp)){
				if(isNumber(prev)||prev==')')
					throw new Exception();
				prev = temp;
				continue;
			}
			//dealing with 'operand case'
			else {
				switch(temp){
			
				case '-':
					break;
				case '+':
				case '^':
				case '/':
				case '%':
				case '*':
					if(isNumber(prev)||prev==')')
						break;
					else
						throw new Exception();
				case '(':
					if(isNumber(prev))
						throw new Exception();
					break;
				case ')':
					if(!(isNumber(prev)||prev==')'))
						throw new Exception();
					break;
				default:
					throw new Exception();
				}
				
				prev = temp;
			}
			
		}
		temp = infixExp.get(infixExp.size()-1).charAt(0);
		if(!(isNumber(temp)||temp==')'))
			throw new Exception();
			
	}
	
	private boolean charCheck(){
		
		ListIterator<String> it = infixExp.listIterator();
		String temp;
		while(it.hasNext()){
			temp = it.next();
			if(isNumber(temp.charAt(0)))
					continue;
			switch(temp.charAt(0)){
			case '+':
			case '-':
			case '^':
			case '(':
			case ')':
			case '*':
			case '/':
			case '%':
				break;
			default:
				return false;
					
			}
			
			
		}
			
		return true;
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
	
	public static boolean isNumber(char c){
		if(Character.getNumericValue(c)<10 &&Character.getNumericValue(c)>-1)
			return true;
		return false;
	}

}
