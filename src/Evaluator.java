import java.util.Stack;

public class Evaluator {
	Stack<String> postfixExp;
	Stack<Long> calculator;
	int result=0;
	public Evaluator(Stack<String> input){
		postfixExp = new Stack<String>();
		calculator = new Stack<Long>();
		while(!input.isEmpty()){
			this.postfixExp.push(input.pop());
		}
		
	}
	
	
	//TODO implement this
	
	public void evaluate() throws Exception{
		String temp;
		while(!postfixExp.isEmpty()){
			temp = postfixExp.pop();
			if(Parser.isNumber(temp.charAt(0))){
				calculator.push(Long.decode(temp));
			}
			else if(temp.charAt(0)=='~'){
				long operand = calculator.pop();
				operand = -operand;
				calculator.push(operand);
			}
			else{
				long operand2 = calculator.pop();
				long operand1 = calculator.pop();
				
				switch(temp.charAt(0)){
				case '^':
					if(operand1==0&&operand2<0)
						throw new Exception();
					calculator.push((long)Math.pow(operand1, operand2));
					break;
				case '*':
					calculator.push(operand1*operand2);
					break;
				
				case '/':
					calculator.push(operand1/operand2);
					break;
				case '%':
					calculator.push(operand1%operand2);
					break;
				
				case '+':
					calculator.push(operand1+operand2);
					break;
					
				case '-':
					calculator.push(operand1-operand2);
					break;
					
				default:
					throw new Exception();
				}
						
				
				
				
			}
				
			
		}
	}
	public void printResult(){
		System.out.println(calculator.pop());
	}

}
