package codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Atm {

	public static void main(String[] args) throws IOException 
	{
		try{
		Scanner s = new Scanner(System.in);
		double credit = s.nextDouble();
		double balance =  s.nextDouble();
		double tax = 0.5; 

		
			
				if((credit<balance && credit%5==0))
				{
					
					System.out.println(balance-(credit+tax));
				}
					else
					{
						System.out.println(balance);
					}					
		
		
		}
			
		
		
		
		catch(Exception e)
		{
			return;
		}
	}}




