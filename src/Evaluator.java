
import java.util.Stack;
public class Evaluator {
	Stack<String> postfixExp;
	Stack<String> calculator;
	int result=0;
	public Evaluator(Stack<String> input){
		postfixExp = new Stack<String>();
		calculator = new Stack<String>();
		while(!input.isEmpty()){
			this.postfixExp.push(input.pop());
		}
		expressionCheck();
	}
	//TODO implement this
	
	private void expressionCheck(){
		
	}
	public void evaluate(){
		
	}
	public void printResult(){
		
	}

}
