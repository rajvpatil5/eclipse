package FileIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CognizantImageDelete 
{
	
	static ArrayList<String> hm= new ArrayList<String>();
	static ArrayList<String> al= new ArrayList<String>();
	static String assessment="D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\Eng\\assets\\xml\\assessment\\";
	static String module00="D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\Eng\\assets\\xml\\module00\\";
	static String module01="D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\Eng\\assets\\xml\\module01\\";
	static String imagePath="D:\\Cognizant\\CIEIN303_M8\\CIEIN303_M8\\Eng\\assets\\media\\images\\";
	
	
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException
	{
		
		
		String [] allImg= {assessment,module00,module01};
		{
			for(int a=0;a<allImg.length; a++)
			{
				File file = new File(allImg[a]);
				String[] list=file.list();
				String xmlFile=null;
				String imageName=null;
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				for(int i=0; i<list.length; i++)
				{			
					xmlFile=list[i];	
					//System.out.println(xmlFile);
					Document document = builder.parse(new File(allImg[a]+xmlFile));
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
								if(r[j].contains(".png")||r[j].contains(".jpg")||r[j].contains(".PNG")||r[j].contains(".gif")||r[j].contains(".jpeg"))
								{
									//System.out.println(r[i]);
									imageName=r[j].substring(0, r[j].indexOf("\""));
									al.add(imageName);
								}
							}
						}
					}
				}
			}			
		}
		System.out.println(al);
		//System.out.println("===================================================================================================");
		imagesInFolders();
		//System.out.println("===================================================================================================");
		imageMap();
		 delete();
	}
	
	public static ArrayList<String> imagesInFolders()
	{
		
		
		File f = new File(imagePath);
		String[] allFiles=f.list();
		for(int l=0; l<allFiles.length; l++)
		{
			if(new File(imagePath+allFiles[l]).isDirectory())
			{
				File dir = new File(imagePath+allFiles[l]);
				String[] imageName1 = dir.list();
				for(int m=0; m<imageName1.length; m++)
				{
					
					if(new File(imagePath+allFiles[l]+"\\"+imageName1[m]).isDirectory())
					{
						File innerdir = new File(imagePath+allFiles[l]+"\\"+imageName1[m]);
						String[] imageName2 = innerdir.list();
						for(int n=0; n<imageName2.length; n++)
						{
							hm.add(imageName2[n]);
						}
					}
					else if(new File(imagePath+allFiles[l]+"\\"+imageName1[m]).isFile())
					{
						hm.add(imageName1[m]);
					}
				}
			}
			if(new File(imagePath+allFiles[l]).isFile())
			{
				hm.add(allFiles[l]);
			}
			
		}
		return hm;
				//hm.forEach((key,value) -> System.out.println(key));		
	}
	
	public static void imageMap()
	{
		hm.removeAll(al);
		System.out.println(hm);
		
	}	
	public static void delete()
	{
		File f = new File(imagePath);
		String [] img = f.list();
		for(int i=0; i<img.length; i++)
		{
			File f1 = new File(imagePath+img[i]);
			for(int j=0; j<hm.size(); j++)
			{
				if(img[i].equals(hm.get(j)))
				{
					File del = new File(imagePath+hm.get(j));
					
					System.out.println(del.delete());
				}
			}	
			if(f1.isDirectory())
			{
				
				String [] img1 = f1.list();
				System.out.println(img[i]);
				for(int k=0; k<img1.length; k++)
				{
				
					for(int l=0; l<hm.size(); l++)
					{
						if(img1[k].equals(hm.get(l)))
						{
							File f2 = new File(imagePath+img[i]+"\\"+hm.get(l));
							System.out.println(f2.delete());
						}
					}
				}				
			}
		}		
	}
}










































