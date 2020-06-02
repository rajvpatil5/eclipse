package codechef;

import java.util.Scanner;

public class EnormousInputTest {

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int numOfLines=scan.nextInt();
		int divisor=scan.nextInt();
		int[] nums = new int[numOfLines];
		for(int i=0; i<numOfLines; i++)
		{
			nums[i]=scan.nextInt();
		}
		int count=0;
		for(int j=0; j<numOfLines; j++)
		{
			if(nums[j]%divisor==0)
			{
				count++;
			}			
		}
		System.out.println(count);
	}
}
