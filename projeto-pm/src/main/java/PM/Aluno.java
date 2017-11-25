package PM;

import lombok.Getter;
import lombok.Setter;

public class Aluno {

	/**
	 * Atributos da classe
	 * 
	 * */
	@Getter @Setter	
	private static String nome;
	@Getter @Setter
	private static String anoEntrada;
	@Getter @Setter
	private static String periodoAtual;
	@Getter @Setter
	private static boolean foiJubilado;
	@Getter @Setter
	private static int CRA;
	@Getter @Setter
	private static boolean formarNoPrazo; //verificar se ele pode se formar no prazo ou não
	@Getter @Setter
	private static int qtdeMateriasAprovadas; //contador para casos de integralização

}
