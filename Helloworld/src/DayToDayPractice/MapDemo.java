package DayToDayPractice;

import java.io.File;
import java.util.HashMap;

public class MapDemo {

	public static void main(String[] args) 
	{
		HashMap hm = new HashMap();
		hm.put(1, "Rajat");
		hm.put(2, "Rahul");
		hm.put(3, "Ram"); 
		hm.put(4, "Raj");
		
		
		hm.put("Raj",4 );
		System.out.println(hm);
		hm.forEach((key,value) -> System.out.println(value));
		System.out.println(hm.values());
	}

}
