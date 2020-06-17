package com.resume;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Resume1 {
	static PDDocument doc = null;
	static PDPageContentStream contentStream = null;
	static PDPage page = new PDPage();
	static PDFont pdfFont = PDType1Font.HELVETICA;
	static List<String> lines = new ArrayList<String>();
	static float fontSize = 12;
	static float leading = 1.5f * fontSize;

	static PDRectangle mediabox = page.getMediaBox();
	static float margin = 72;
	static float width = mediabox.getWidth() - 2 * margin;
	static float startX = mediabox.getLowerLeftX() + margin;
	static float startY = mediabox.getUpperRightY() - margin;

	static void textSetter(String str) throws IOException {

		try {
			doc = new PDDocument();

			doc.addPage(page);
			contentStream = new PDPageContentStream(doc, page);

			float fontSize = 12;

			String text = str;

			int lastSpace = -1;
			while (text.length() > 0) {
				int spaceIndex = text.indexOf(' ', lastSpace + 1);
				if (spaceIndex < 0)
					spaceIndex = text.length();
				String subString = text.substring(0, spaceIndex);
				float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
				System.out.printf("'%s' - %f of %f\n", subString, size, width);
				if (size > width) {
					if (lastSpace < 0)
						lastSpace = spaceIndex;
					subString = text.substring(0, lastSpace);
					lines.add(subString);
					text = text.substring(lastSpace).trim();
					System.out.printf("'%s' is line\n", subString);
					lastSpace = -1;
				} else if (spaceIndex == text.length()) {
					lines.add(text);
					System.out.printf("'%s' is line\n", text);
					text = "";
				} else {
					lastSpace = spaceIndex;
				}
			}
		} catch (Exception e) {
			if (doc != null) {
				doc.close();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		textSetter("Rajat Patil");
		textSetter("Contact No: 8149915224");
		textSetter("Email: srajat.patil@gmail.com");
		textSetter("CAREER OBJECTIVE");
		textSetter("To work with maximum potential in a challenging and dynamic environment, with an opportunity of working with diverse group of people and enhancing my professional skills with career growth.");
		contentStream.beginText();
		contentStream.setFont(pdfFont, fontSize);
		contentStream.newLineAtOffset(startX, startY);
		for (String line : lines) {
			contentStream.showText(line);
			contentStream.newLineAtOffset(0, -leading);
		}
		contentStream.endText();
		contentStream.close();

		doc.save("D:\\b.pdf");

	}
}
