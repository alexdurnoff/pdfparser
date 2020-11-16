package pdfparser.doc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import com.itextpdf.text.pdf.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itextpdf.text.Rectangle;

public class TestFeaturesThisLibrary {
	private final String pathToPDFFile = "10-А-11_А3.pdf";
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
			if(pdfObject.isDictionary()){
				PdfDictionary pdfDictionary = (PdfDictionary) pdfObject;
				Set<PdfName> pdfDictionaryKeys = pdfDictionary.getKeys();
				for (PdfName name:pdfDictionaryKeys){
					System.out.println(name);
					PdfObject object = pdfDictionary.get(name);
					System.out.println("----");
					System.out.println(object.toString());
					System.out.println("----");
				}
			} else if (pdfObject.isStream()){
				PRStream stream = (PRStream) pdfObject;
				PdfDictionary streamRoot = stream.getReader().getCatalog();
				Set<PdfName> pdfDictionaryKeys = streamRoot.getKeys();
				for (PdfName name:pdfDictionaryKeys){
					System.out.println(name);
					PdfObject object = streamRoot.get(name);
					System.out.println("----");
					System.out.println(object.toString());
					System.out.println("----");
				}
			}
			System.out.println("end of pdfObject output");
			if(pdfObject.getClass() == PRStream.class){
				PRStream stream = (PRStream) pdfObject;
			}
			countObjects++;
			pdfObject = reader.getPdfObject(countObjects);
		}
		
	}

	@Test
	public void testEncoding() throws IOException {
		reader = new PdfReader(this.pathToPDFFile);
		int count = reader.getXrefSize();
		for (int i = 1; i < count; i++){
			PdfObject pdfObject = reader.getPdfObject(i);
			if(pdfObject.isArray()){
				PdfArray pdfArray = (PdfArray) pdfObject;
				System.out.println(pdfArray);
			}
		}
	}

	@Test
	public void testInfo() throws IOException {
		reader = new PdfReader(this.pathToPDFFile);
		HashMap<String, String> infoMap = reader.getInfo();
		infoMap.forEach((k,v) -> System.out.println(k + "--" + v));
		reader.close();
	}

}
