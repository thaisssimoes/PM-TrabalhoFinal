package PM;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;


public class LeitorDePDF {


	    public static void main(String[] args) throws IOException{
	    	File historicoEscolarCaminho = new File("C:/Users/tsuba/Documents/Faculdade/historicoEscolarCRAprovados.pdf");        
	    	String texto= extrairPDF(historicoEscolarCaminho);
	    	System.out.print("TESTE" + texto);
	    
	    }

	    public static String extrairPDF(File historicoEscolarCaminho) throws IOException {
	        PDDocument historicoEscolar = PDDocument.load(historicoEscolarCaminho);        
	        PDFTextStripper pdfStripper = new PDFTextStripper();
	        String text = pdfStripper.getText(historicoEscolar);
	        historicoEscolar.close();
	        return text;
	    }

   }
	

