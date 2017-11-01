package PM;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Disciplina {

	
	/**
	 * ?????????????????????????????????????????????????????????????????????
	 * 
	 * */
	//Tamanho Inicial da Lista
	int initialSize = 16;
	              
	//Valor do Load Factor
	double loadFactor = 0.75;
	
	
	double sizeToRehash = initialSize * loadFactor;
	
	/**
	 * 
	 * DEVERIA CRIAR O HASH FORA?
	 * 
	 * 
	 * */
	public static void importarListaDisciplinas(String caminhoLista) throws IOException{
		String codigoDisciplina;
		String nomeDisciplina;
		String disciplinaRetiradaDaLista;
		Map<String,String> informacaoesDeDisciplinas = new HashMap<String,String>();
		File listaDisciplina = new File(caminhoLista);
		String[] arrayDeDeCodigoENome; 
		
		try {

		        Scanner leitorDisciplinas = new Scanner(listaDisciplina);
		        
		        while (leitorDisciplinas.hasNextLine()) {		            
		        	disciplinaRetiradaDaLista = leitorDisciplinas.nextLine();		        	
		        	arrayDeDeCodigoENome = disciplinaRetiradaDaLista.split(":");		           		        	
		        	codigoDisciplina = arrayDeDeCodigoENome[0];
		            nomeDisciplina = arrayDeDeCodigoENome[1];	            		            
		            informacaoesDeDisciplinas.put(codigoDisciplina, new String(nomeDisciplina));		        	
		        	System.out.println(disciplinaRetiradaDaLista + "\n \n \n a: " + codigoDisciplina + "\n\n\n b: " + nomeDisciplina);
		        }
		        leitorDisciplinas.close();	        	
		        System.out.println(informacaoesDeDisciplinas);
		    } 
		    catch (IOException e) {
		        e.printStackTrace();
		    }
		 }
	
	
	
	/**
	 * 
	 * 
	 * 
	 * */
	
	//TODO
	
	public static void encontrarStatus(String historicoRefinado) throws IOException{
        
		Scanner leitorDisciplinas = new Scanner(historicoRefinado);
		String test =  "This is a sentence";
		String lastWord = test.substring(test.lastIndexOf(" ")+1);
		
		
		while (leitorDisciplinas.hasNextLine()) {
            
        	String disciplinaRetiradaDaLista = leitorDisciplinas.next();
        	

        	
        }

	}


	
}
