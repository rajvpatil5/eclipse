package DayToDayPractice;

import java.util.Scanner;

public class Demo extends Demo1
{
	

	public static void main(String[] args) 
	{
		// your code goes here
		Scanner s = new Scanner(System.in);
		double n = s.nextDouble();

		double m = s.nextDouble();
	        if((n+0.5)<=m && n%5==0){
	            System.out.println(m-(n+0.50));
	        }else{
	            System.out.println(m);
	        }
		
	}
}
