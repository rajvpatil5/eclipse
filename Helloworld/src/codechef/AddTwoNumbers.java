package codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AddTwoNumbers 
{

	public static void main(String[] args) throws IOException 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan=new Scanner(System.in);
		int lines=scan.nextInt();
		Integer[][] num = new Integer[2][lines];
		for(int j=0; j<lines; j++)
		{
			String[] nums=new String[2];
			nums=br.readLine().split(" ");
			
			for(int i=0;i<2;i++)
			{
				num[i][j] = Integer.parseInt(nums[i]);
			}		
			
		}
		
			System.out.println(num[0][0]+num[1][0]);
			System.out.println(num[0][1]+num[1][1]);
			System.out.println(num[0][2]+num[1][2]);
			
	}
}
