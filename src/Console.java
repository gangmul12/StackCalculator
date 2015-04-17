import java.io.*;

public class Console

{

	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

 

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;
				command(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input) throws Exception
	{
		Parser p = new Parser(input);
		p.changeNotation();
		Evaluator e = new Evaluator(p.getPostFixExp());
		e.evaluate();
		e.printResult();
	}

	
}
