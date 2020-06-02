package codechef;

import java.util.Scanner;

public class SumOfDigits 
{
	public static void main(String[] args) 
	{
		System.out.print("Enter numbers of lines: ");
		Scanner scan=new Scanner(System.in);
		int lines=scan.nextInt();
		int[] sums=new int[lines];
		
		for(int i=0; i<lines; i++)
		{
			int digit;
			int sum=0;
			System.out.print("Enter num"+i+": ");
			Scanner numScan=new Scanner(System.in);
			int num=scan.nextInt();
			while (num>0)
			{				
				digit=num%10;
				sum=digit+sum;
				num=num/10;
			}
			sums[i]=sum;
		}
		
		for(int i=0; i<lines; i++)
		{
			System.out.println(sums[i]);
		}
		
	}

}
