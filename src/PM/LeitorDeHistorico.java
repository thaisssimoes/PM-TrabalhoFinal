package PM;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe destinada a ler, refinar e interpretar o histórico escolar do aluno
 * 
 * @author grupoPM
 * 
 * */

public class LeitorDeHistorico {


	    public static void main(String[] args) throws IOException{
	    	final String caminhoListaDisciplina = "C:/Users/tsuba/Documents/Codigos/Codigos Eclipse/PM-LeitorHistorico/lista de disciplinas.txt";
	    	final String historicoEscolarCaminho ="C:/Users/tsuba/Documents/Faculdade/historicoEscolarCRAprovados.pdf";
	    	ArrayList<Integer> indexesComecoEFimDasMaterias = new ArrayList<Integer>(); 
	    	int indexComeco;
	    	int indexFim;   	
	    	final File historicoEscolarDocumento = new File(historicoEscolarCaminho);        
	    	String historicoEscolarExtraido;
	    	String historicoEscolarRefinado;
	    	
	    	
	    	
	    	historicoEscolarExtraido= extrairPDF(historicoEscolarDocumento);
	    	indexesComecoEFimDasMaterias = buscadoresDeIndex(historicoEscolarExtraido); 
	     	indexComeco = indexesComecoEFimDasMaterias.get(0);
	    	indexFim = indexesComecoEFimDasMaterias.get(1);
	    	historicoEscolarRefinado = refinadorDeConteudoDoHistoricoEscolar(historicoEscolarExtraido,indexComeco, indexFim);
	    	
	    	
	    	
	    	Disciplina.importarListaDisciplinas(caminhoListaDisciplina);
	    	Disciplina.encontrarStatusDeAprovacao(historicoEscolarRefinado);
	    
	    }
	    
	    /**
	     * 
	     * Este método procura extrair o histórico escolar do documento PDF e transformá-lo em uma String
	     * 
	     * @param historicoEscolarCaminho (File) : É o caminho de onde o histórico está armazenado na máquina. 
	     * 
	     * @return String com o histórico extraido
	     * 
	     * */

	    public static String extrairPDF(File historicoEscolarCaminho) throws IOException {
	        PDDocument historicoEscolar = PDDocument.load(historicoEscolarCaminho);        
	        PDFTextStripper pdfStripper = new PDFTextStripper();
	        String historicoEscolarExtraido = pdfStripper.getText(historicoEscolar);
	        historicoEscolar.close();
	        return historicoEscolarExtraido;
	    }
	    
	    /**
	     * Este método procura encontrar o início e o fim aproximado do conteúdo de matérias do histórico escolar.
	     *  
	     * @param historicoEscolarExtraido (String): String com o histórico escolar completo, antes do refinamento
	     * 
	     * @return : ArrayList<Integer> com os valores correspondentes às posições inicial e final do bloco de aprovações
	     * 
	     * */
	        
	    public static ArrayList<Integer> buscadoresDeIndex (String historicoEscolarExtraido) throws IOException {
	    	ArrayList<Integer> indexesComecoEFimDasMaterias = new ArrayList<Integer>();
	    	int indexComeco = historicoEscolarExtraido.indexOf("Situação Local");
	    	int indexFim = historicoEscolarExtraido.indexOf("Coeficiente de Rendimento Geral");
	    	
	    	indexesComecoEFimDasMaterias.add(indexComeco);
	    	indexesComecoEFimDasMaterias.add(indexFim);
	    	
	    	return indexesComecoEFimDasMaterias;
	    }	    
	    
	    /**
	     * Este método se destina a separar o bloco de aprovações do restante das informações do histórico
	     * 
	     * @param historicoEscolarExtraido (String) : String com o histórico escolar completo, antes do refinamento
	     * @param indexComeco (Int) : Indicador do começo do texto refinado
	     * @param indexFim (Int) : Indicador do fim do texto refinado
	     * 
	     * @return Substring com o bloco de matérias separado do restante
	     * 
	     * */
	    
	    public static String refinadorDeConteudoDoHistoricoEscolar(String historicoEscolarExtraido, int indexComeco, int indexFim){
	  	    	
	    	String historicoEscolarRefinado= historicoEscolarExtraido.substring(indexComeco, indexFim);   		    	
	    	return historicoEscolarRefinado;
	    }

	    
	    	    
	    
   }
	

