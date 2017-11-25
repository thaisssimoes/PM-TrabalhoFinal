package PM;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gerenciador {
	private static final String LISTA_DE_DISCIPLINAS_TXT = "lista de disciplinas.txt";
	private static final String HISTORICO_ESCOLAR_CR_APROVADOS_PDF = "historicoEscolarCRAprovados.pdf";
	private static final Path LISTA_DISCIPLINA_PATH = Paths.get(System.getProperty("user.dir"), LISTA_DE_DISCIPLINAS_TXT);
	private static final Path HISTORIO_ESCOLAR_PATH = Paths.get(System.getProperty("user.dir"), HISTORICO_ESCOLAR_CR_APROVADOS_PDF);
	private static final String MARCOINDICEINICIAL = "Situação Local\r\n";
	private static final String MARCOINDICEIFinal = "Coeficiente de Rendimento Geral";

	
	public static void main(String[] args) throws IOException {
		final File historicoEscolarDocumento = new File(HISTORIO_ESCOLAR_PATH.toString());
		Disciplina disciplina = new Disciplina();
		ManipuladorDePDF DocumentoPDF = new ManipuladorDePDF();
		Util utilitarios = new Util();
		
		String historicoEscolarExtraido = DocumentoPDF.extrairHistoricoEscolar(historicoEscolarDocumento);
		String historicoEscolarRefinado = DocumentoPDF.getBlocoDeDisciplinas(historicoEscolarExtraido,
				DocumentoPDF.recuperarIndexInicial(historicoEscolarDocumento), DocumentoPDF.recuperarIndexFinal(historicoEscolarDocumento));
		utilitarios.importarListaDisciplinas(LISTA_DISCIPLINA_PATH.toString());
		utilitarios.encontrarSituacao(historicoEscolarRefinado);
	}

}
