package FileIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ImageReader 
{
	static ArrayList<String> al= new ArrayList<String>();

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException 
	{
		String assessment="D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\Eng\\assets\\xml\\assessment\\";
		String module00="D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\Eng\\assets\\xml\\module00\\";
		String module01="D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\Eng\\assets\\xml\\module01\\";
		
				File file = new File(module00);
				String[] list=file.list();
				
			
				
				String xmlFile=null;
				String imageName=null;
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				for(int i=0; i<list.length; i++)
				{			
					xmlFile=list[i];	
					System.out.println(xmlFile);
					Document document = builder.parse(new File(module01+xmlFile));
					NodeList nodelist = document.getElementsByTagName("content");
					
					
					for (int temp = 0; temp < nodelist.getLength(); temp++)
					{
						Node node = nodelist.item(temp);
						
						if (node.getNodeType() == Node.ELEMENT_NODE)
						{
							Element element = (Element)node;
							String l1=element.getElementsByTagName("text").item(0).getTextContent();
							String[] r = l1.split("/");
							for(int j=0;j<r.length; j++)
							{
								if(r[j].contains(".png")||r[j].contains(".jpg"))
								{
									//System.out.println(r[i]);
									imageName=r[j].substring(0, r[j].indexOf("\""));
									al.add(imageName);
								}
							}
						}
					}
				}
			
			
		
		System.out.println(al);
		
	
		
		
	}
}





















