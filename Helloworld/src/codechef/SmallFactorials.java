package codechef;

import java.util.Scanner;

public class SmallFactorials {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		int lines=scan.nextInt();
		int[] num= new int[lines];
		Scanner scan1 = new Scanner(System.in);
		int[] facts=new int[lines];
		for(int i=0; i<lines; i++)
		{
			num[i]=scan1.nextInt();
			int fact=num[i];
			for(int j=0; j<=num[i]; j++)
			{
				num[i]--;					
				fact=fact*num[i];			
			}
			if(fact==0)
			{
				fact=1;
			}
			facts[i]=fact;
		}
		for(int i=0; i<lines; i++)
		{
			System.out.println(facts[i]);
		}
	}
}
 