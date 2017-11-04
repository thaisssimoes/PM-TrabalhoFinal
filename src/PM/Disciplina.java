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
	
	static Map<String,String> informacaoesDeDisciplinas = new HashMap<String,String>();
	
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
        
		Scanner leitorDeHistorico = new Scanner(historicoRefinado);
		String codigo;
		
		while (leitorDeHistorico.hasNextLine()) {
            
			String disciplinaRetiradaDaLista = leitorDeHistorico.nextLine();
			Scanner leitorTeste = new Scanner(disciplinaRetiradaDaLista);
			codigo = leitorTeste.next();		
			for (String key : informacaoesDeDisciplinas.keySet()) {
				if(codigo.equals(key) && !codigo.equals("HTD0058")){
					String[] testando = disciplinaRetiradaDaLista.split("-");
					if(codigo.equals("TIN0110")){
			            informacaoesDeDisciplinas.put(key, new String(testando[2]));		        	
					}
					else{
						informacaoesDeDisciplinas.put(key, new String(testando[1]));
					}								
				}
				else if(codigo.equals(key) && codigo.equals("HTD0058")){
					disciplinaRetiradaDaLista = leitorDeHistorico.nextLine();
					disciplinaRetiradaDaLista = leitorDeHistorico.nextLine();
					String[] testando = disciplinaRetiradaDaLista.split("-");
					informacaoesDeDisciplinas.put(key, new String(testando[1]));
					
				}
			
			}
						
			
		}

        }

	}


	

