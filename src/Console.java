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
				System.out.println("�Է��� �߸��Ǿ����ϴ�. ���� : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		Parser p = new Parser(input);
		p.printInfixExp();
		// TODO : �Ʒ� ������ �����ϰ� �����ض�.
		System.out.println("<< command �Լ����� " + input + " ����� ó���� �����Դϴ� >>");
	}

	public static boolean isNumber(char c){
		if(Character.getNumericValue(c)<10 &&Character.getNumericValue(c)>-1)
			return true;
		return false;
	}
}
