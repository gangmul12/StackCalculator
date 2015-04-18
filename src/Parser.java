import java.util.ListIterator;
import java.util.Stack;
import java.util.ArrayList;
public class Parser {
	ArrayList<String> infixExp;
	Stack<String> operatorStack;
	Stack<String> postfixExp;

	public Parser(String input) throws Exception{
		infixExp = new ArrayList<String>();
		
		setInfix(input);
		operatorStack = new Stack<String>();
		checkEquation();
		
		postfixExp = new Stack<String>();
		
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
		char cur;
		while(it.hasNext()){
			cur = it.next().charAt(0);
			if(cur=='(')
				operatorStack.push("(");
			if(cur==')'){
				if(operatorStack.isEmpty())
					throw new Exception();
				operatorStack.pop();
			}
		}
		if(!operatorStack.isEmpty())
			throw new Exception();
		
		
		//dealing with expression error
		it = infixExp.listIterator();
		
		char prev='!';
		int i=0;
		while(it.hasNext()){
			cur = it.next().charAt(0);
			
			//dealing with 'index=0 case'
			if(i==0&&(cur=='+'||cur==')'||cur=='^'||cur=='/'||cur=='%'||cur=='*')){
				throw new Exception();
			}
			else if(i==0){
				i++;
				prev=cur;
				continue;
			}
				
			
			//dealing with 'number case'
			else if(isNumber(cur)){
				if(isNumber(prev)||prev==')')
					throw new Exception();
				prev = cur;
				continue;
			}
			//dealing with 'operand case'
			else {
				switch(cur){
			
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
				
				prev = cur;
			}
			
		}
		cur = infixExp.get(infixExp.size()-1).charAt(0);
		if(!(isNumber(cur)||cur==')'))
			throw new Exception();
			
	}
	
	private boolean charCheck(){
		
		ListIterator<String> it = infixExp.listIterator();
		String cur;
		while(it.hasNext()){
			cur = it.next();
			if(isNumber(cur.charAt(0)))
					continue;
			switch(cur.charAt(0)){
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
	
	public void printPostfixExp(){
		ListIterator<String> it = postfixExp.listIterator();
		while(it.hasNext())
			System.out.print(it.next()+" ");
		System.out.println("");
	}
	
	public void changeNotation(){
		ListIterator<String> it = infixExp.listIterator();
		String cur;
		String prev = infixExp.get(0);
		int i=0;
		while(it.hasNext()){
			cur = it.next();
			if(isNumber(cur.charAt(0)))
					postfixExp.push(cur);
			else{
				switch(cur.charAt(0)){
				case '(':
					operatorStack.push("(");
					break;
				case ')':
					while(operatorStack.peek()!="(")
						postfixExp.push(operatorStack.pop());
					operatorStack.pop();
					break;
					
					
				
				case '^':
				case'-':
					char minusCheck = cur.charAt(0);
					String temp = cur;
					if(i==0||isOperator(prev.charAt(0))){
						minusCheck = '~';
						temp = "~";
					}
						
					while((!operatorStack.isEmpty())&&operatorStack.peek()!="("&&precedence(minusCheck)>precedence(operatorStack.peek().charAt(0))){
						postfixExp.push(operatorStack.pop());
						
					}
					operatorStack.push(temp);
					break;
					
			
					
					
										
				default:
				
					
					while((!operatorStack.isEmpty())&&operatorStack.peek()!="("&&precedence(cur.charAt(0))>=precedence(operatorStack.peek().charAt(0))){
						postfixExp.push(operatorStack.pop());
						
					}
					operatorStack.push(cur);
					break;
				
				}
			}
			prev = cur;
			i++;
		}
		
		while(!operatorStack.isEmpty()){
			postfixExp.push(operatorStack.pop());
		}
	
		

	}

	public Stack<String> getPostFixExp(){
		Stack<String> tempStack = new Stack<String>();
		Stack<String> result = new Stack<String>();
		String temp;
		while(!postfixExp.isEmpty())
			tempStack.add(postfixExp.pop());
			
		
		while(!tempStack.isEmpty()){
			temp = tempStack.pop();
			result.push(temp);
			postfixExp.push(temp);
		}
		

		return result;
	
	}
	public static boolean isNumber(char c){
		if(Character.getNumericValue(c)<10 &&Character.getNumericValue(c)>-1)
			return true;
		return false;
	}
	public static boolean isOperator(char c){
		if(isNumber(c)||c=='('||c==')')
			return false;
		else
			return true;
	}
	private int precedence(char ch){
		switch(ch){
		case'(':
		case')':
			return 0;
		case'^':
			return 1;
		case'~':
			return 2;
		case'*':
		case'%':
		case'/':
			return 3;
		default:
			return 4;
			
			
		
		}
	}

}
