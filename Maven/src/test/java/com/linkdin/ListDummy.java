package com.linkdin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListDummy {
	public static List<Object> firstNameMofidy(List<Object> name) {
		List<Object> fname = new ArrayList<Object>();
		String[][] n = new String[name.size()][];
		for (int i = 0; i < name.size(); i++) {
			n[i] = ((String) name.get(i)).split(" ", 2);
		}
		for (int i = 0; i < name.size(); i++) {
			fname.add(n[i][0]);
		}

		return fname;
	}

	public static List<Object> lastNameMofidy(List<Object> name) {
		List<Object> lname = new ArrayList<Object>();
		String[][] n = new String[name.size()][];
		for (int i = 0; i < name.size(); i++) {
			n[i] = ((String) name.get(i)).split(" ", 2);
		}
		for (int j = 0; j < name.size(); j++) {
			lname.add(n[j][1]);
		}
		return lname;
	}

	public static void main(String[] args) throws IOException {
		List<Object> name = new ArrayList<Object>();
		List<Object> position = new ArrayList<Object>();
		List<Object> linkdinUrl = new ArrayList<Object>();
		List<List<Object>> data = new ArrayList<List<Object>>();
		name.add("n1 space1");
		name.add("n2 space2");
		name.add("n3 space3");
		name.add("n4 space4");
		name.add("n5 space5");
		name.add("n6 space6");
		name.add("n7 space7");
		name.add("n8 space8");
		name.add("n9 space9");
		position.add("p1");
		position.add("p2");
		position.add("p3");
		position.add("p4");
		position.add("p5");
		position.add("p6");
		position.add("p7");
		position.add("p8");
		position.add("p9");
		linkdinUrl.add("m1");
		linkdinUrl.add("m2");
		linkdinUrl.add("m3");
		linkdinUrl.add("m4");
		linkdinUrl.add("m5");
		linkdinUrl.add("m6");
		linkdinUrl.add("m7");
		linkdinUrl.add("m8");
		linkdinUrl.add("m9");

		data.add(ListDummy.firstNameMofidy(name));
		data.add(ListDummy.lastNameMofidy(name));
		data.add(position);
		data.add(linkdinUrl);

		for (int i = 0; i < data.get(0).size(); i++) {
			for (int j = 0; j < data.size(); j++) {
				System.out.println(data.get(j).get(i));
			}
		}
		SimpleExcelWriterExample.setData(name, position, linkdinUrl);
	}
}
