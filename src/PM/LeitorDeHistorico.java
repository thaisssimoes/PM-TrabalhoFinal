package PM;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class LeitorDeHistorico {


	    public static void main(String[] args) throws IOException{
	    	ArrayList<String> indexesComecoEFimDasMaterias = new ArrayList<String>(); //melhorar isso
	    	String indexComeco;
	    	String indexFim;
	    	
	    	
	    	File historicoEscolarCaminho = new File("C:/Users/tsuba/Documents/Faculdade/historicoEscolarCRAprovados.pdf");        
	    	String historicoEscolarExtraido= extrairPDF(historicoEscolarCaminho);
	    	
	    	indexesComecoEFimDasMaterias = buscadoresDeIndex(historicoEscolarExtraido); //melhorar isso
	    	
	    	indexComeco = indexesComecoEFimDasMaterias.get(0);
	    	indexFim = indexesComecoEFimDasMaterias.get(1);
	    	
	    	
	    	String historicoEscolarRefinado = refinadorDeConteudoDoHistoricoEscolar(historicoEscolarExtraido, Integer.valueOf(indexComeco), Integer.valueOf(indexFim));
	    	
	    	
	    	
	    	System.out.print(historicoEscolarRefinado);
	    
	    }

	    public static String extrairPDF(File historicoEscolarCaminho) throws IOException {
	        PDDocument historicoEscolar = PDDocument.load(historicoEscolarCaminho);        
	        PDFTextStripper pdfStripper = new PDFTextStripper();
	        String text = pdfStripper.getText(historicoEscolar);
	        historicoEscolar.close();
	        return text;
	    }
	    
	    //MELHORAR ISSO!!
	    public static ArrayList<String> buscadoresDeIndex (String historicoEscolarLido) throws IOException {
	    	ArrayList<String> indexesComecoEFimDasMaterias = new ArrayList<String>();

	    	String indexComeco = Integer.toString(historicoEscolarLido.indexOf("Situação Local"));
	    	String indexFim = Integer.toString(historicoEscolarLido.indexOf("Coeficiente de Rendimento Geral"));
	    	
	    	
	    	indexesComecoEFimDasMaterias.add(indexComeco);
	    	indexesComecoEFimDasMaterias.add(indexFim);
	    	
	    	return indexesComecoEFimDasMaterias;
	    }
	    
	    public static String refinadorDeConteudoDoHistoricoEscolar(String historicoEscolarLido, int indexComeco, int indexFim){
	    	
	    	
	    	String historicoEscolarRefinado= historicoEscolarLido.substring(indexComeco, indexFim);   	
	    	
	    	return historicoEscolarRefinado;
	    }

   }
	

