import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe para reconhecimento de informações referentes às disciplinas no
 * Historico Escolar.
 * 
 * @author grupoPM
 * 
 */

public class Disciplina {

	/**
	 * Atributos da classe
	 * 
	 */
	private Map<String, String> informacaoesDeDisciplinas = new HashMap<String, String>();
	private Scanner leitorDeCodigo;
	private Scanner leitorDeHistorico;

	/**
	 * 
	 * Este método lê e armazena em hashmap uma lista .txt de disciplinas e códigos
	 * que será usada posteriormente.
	 * 
	 * @param caminhoLista(String)
	 *            : endereço da lista na máquina
	 * 
	 */
	void importarListaDisciplinas(String caminhoLista) throws IOException {
		String codigoDisciplina;
		String nomeDisciplina;
		String disciplinaRetiradaDaLista;

		File listaDisciplina = new File(caminhoLista);
		String[] arrayDeCodigoENome;

		try {
			Scanner leitorDisciplinas = new Scanner(listaDisciplina);
			while (leitorDisciplinas.hasNextLine()) {
				disciplinaRetiradaDaLista = leitorDisciplinas.nextLine();
				arrayDeCodigoENome = disciplinaRetiradaDaLista.split(":");
				codigoDisciplina = arrayDeCodigoENome[0];
				nomeDisciplina = arrayDeCodigoENome[1];
				informacaoesDeDisciplinas.put(codigoDisciplina, new String(nomeDisciplina));
			}
			leitorDisciplinas.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este método procura encontrar o status de aprovação do aluno em uma
	 * disciplina e armazená-lo no hashmap
	 * 
	 * @param historicoRefinado
	 *            (String) : um bloco menor [apenas com as disciplinas] do historico
	 *            escolar
	 * 
	 */
	void encontrarStatusDeAprovacao(String historicoRefinado) {

		leitorDeHistorico = new Scanner(historicoRefinado);
		String codigo;

		while (leitorDeHistorico.hasNextLine()) {
			String linhaAtual = leitorDeHistorico.nextLine();
			leitorDeCodigo = new Scanner(linhaAtual);
			codigo = leitorDeCodigo.next();
			for (String codigoChave : informacaoesDeDisciplinas.keySet()) {
				if (codigo.equals(codigoChave)) {
					if (codigo.equals("HTD0058")) {
						String novaLinhaAtual = linhaAtual.concat(leitorDeHistorico.nextLine())
								.concat(leitorDeHistorico.nextLine());
						String[] separadorDeStatus = novaLinhaAtual.split("-");
						informacaoesDeDisciplinas.put(codigoChave, new String(separadorDeStatus[1]));
					} else if (codigo.equals("TIN0110")) {
						String[] separadorDeStatus = linhaAtual.split("-");
						informacaoesDeDisciplinas.put(codigoChave, new String(separadorDeStatus[2]));
					} else {
						String[] separadorDeStatus = linhaAtual.split("-");
						informacaoesDeDisciplinas.put(codigoChave, new String(separadorDeStatus[1]));
					}
				}

			}

		}

	}

}
