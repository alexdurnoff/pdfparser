package pdfparser.doc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;

public class TestFeaturesThisLibrary {
	private final String pathToPDFFile = "file:///home/alexej/eclipse-workspace/ru.durnov.odf/pdf/10-А-07_А3.pdf";
	private PdfReader reader;
	
	@BeforeEach
	public void setupTest() throws IOException{
		this.reader = new PdfReader(this.pathToPDFFile);
	}
	
	
	@Test
	public void testOpenFile() throws IOException{
		reader = new PdfReader(this.pathToPDFFile);
		System.out.println(this.reader);
		System.out.println("end of reader-output");
		HashMap<String,String> infoMap = this.reader.getInfo();
		infoMap.forEach((k,v) -> System.out.println(k + ":" + v));
		System.out.println("end of info output");
		HashMap destinationMap = reader.getNamedDestinationFromNames();
		destinationMap.forEach((k,v) -> System.out.println(k + ":" + v));
		System.out.println(destinationMap.size());
		System.out.println("end of destinationMap output");
		PdfDictionary catalog = reader.getCatalog();
		Set<PdfName> keys = catalog.getKeys();
		System.out.println("start output cacalog keys");
		keys.forEach(k -> System.out.println(k));
		System.out.println("end output cacalog keys");
		PdfDictionary pageResources = reader.getPageResources(1);
		int countObjects = 1;
		PdfObject pdfObject = reader.getPdfObject(countObjects);
		while (pdfObject != null){
			System.out.println("start new pdfObject output");
			System.out.println(pdfObject.toString() + "---" + pdfObject.getClass().toString());
			System.out.println("end of pdfObject output");
			if(pdfObject.getClass() == PRStream.class){
				PRStream stream = (PRStream) pdfObject;
			}
			countObjects++;
			pdfObject = reader.getPdfObject(countObjects);
		}
		
	}

}
