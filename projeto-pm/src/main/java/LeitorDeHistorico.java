import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * Classe destinada a ler, refinar e interpretar o histórico escolar do aluno
 * 
 * @author grupoPM
 * 
 */
public class LeitorDeHistorico {

	private static final String LISTA_DE_DISCIPLINAS_TXT = "lista de disciplinas.txt";
	private static final String HISTORICO_ESCOLAR_CR_APROVADOS_PDF = "historicoEscolarCRAprovados.pdf";
	private static final Path LISTA_DISCIPLINA_PATH = Paths.get(System.getProperty("user.dir"), LISTA_DE_DISCIPLINAS_TXT);
	private static final Path HISTORIO_ESCOLAR_PATH = Paths.get(System.getProperty("user.dir"), HISTORICO_ESCOLAR_CR_APROVADOS_PDF);

	public static void main(String[] args) throws IOException {
		final File historicoEscolarDocumento = new File(HISTORIO_ESCOLAR_PATH.toString());
		Disciplina disciplina = new Disciplina();

		String historicoEscolarExtraido = extrairHistoricoEscolar(historicoEscolarDocumento);
		String historicoEscolarRefinado = refinadorDeConteudoDoHistoricoEscolar(historicoEscolarExtraido,
				recuperarIndexInicial(historicoEscolarDocumento), recuperarIndexFinal(historicoEscolarDocumento));
		disciplina.importarListaDisciplinas(LISTA_DISCIPLINA_PATH.toString());
		disciplina.encontrarStatusDeAprovacao(historicoEscolarRefinado);
	}

	/**
	 * Método utilizado para recuperar o index inicial do doc
	 * 
	 * @param historicoEscolarDoc
	 * @return indexComeco
	 * @throws IOException
	 */
	public static int recuperarIndexInicial(File historicoEscolarDoc) throws IOException {
		int indexComeco;
		ArrayList<Integer> indexesComecoEFimDasMaterias = recuperarIndexesComecoEFim(historicoEscolarDoc);
		indexComeco = indexesComecoEFimDasMaterias.get(0);
		return indexComeco;
	}

	/**
	 * Método utilizado para recuperar o index final do doc
	 * 
	 * @param historicoEscolarDoc
	 * @return indexFim
	 * @throws IOException
	 */
	public static int recuperarIndexFinal(File historicoEscolarDoc) throws IOException {
		int indexFim;
		ArrayList<Integer> indexesComecoEFimDasMaterias = recuperarIndexesComecoEFim(historicoEscolarDoc);
		indexFim = indexesComecoEFimDasMaterias.get(1);
		return indexFim;
	}

	/**
	 * Método utilizado para recuperar os indexes de começo e de fim do doc
	 * 
	 * @param historicoEscolarDoc
	 * @return indexesComecoEFimDasMaterias
	 * @throws IOException
	 */
	private static ArrayList<Integer> recuperarIndexesComecoEFim(File historicoEscolarDoc) throws IOException {
		ArrayList<Integer> indexesComecoEFimDasMaterias = new ArrayList<Integer>();
		String historicoEscolarExtraido = extrairHistoricoEscolar(historicoEscolarDoc);
		indexesComecoEFimDasMaterias = buscadoresDeIndex(historicoEscolarExtraido);
		return indexesComecoEFimDasMaterias;
	}

	/**
	 * Método utilizado para extrarir o historico escolar do doc
	 * 
	 * @param historicoEscolarDoc
	 * @return historicoEscolarExtraido
	 * @throws IOException
	 */
	private static String extrairHistoricoEscolar(File historicoEscolarDoc) throws IOException {
		String historicoEscolarExtraido;
		historicoEscolarExtraido = extrairPDF(historicoEscolarDoc);
		return historicoEscolarExtraido;
	}

	/**
	 * 
	 * Este método procura extrair o histórico escolar do documento PDF e
	 * transformá-lo em uma String
	 * 
	 * @param historicoEscolarCaminho
	 *            (File) : É o caminho de onde o histórico está armazenado na
	 *            máquina.
	 * 
	 * @return String com o histórico extraido
	 * 
	 */
	public static String extrairPDF(File historicoEscolarCaminho) throws IOException {
		PDDocument historicoEscolar = PDDocument.load(historicoEscolarCaminho);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String historicoEscolarExtraido = pdfStripper.getText(historicoEscolar);
		historicoEscolar.close();
		return historicoEscolarExtraido;
	}

	/**
	 * Este método procura encontrar o início e o fim aproximado do conteúdo de
	 * matérias do histórico escolar.
	 * 
	 * @param historicoEscolarExtraido
	 *            (String): String com o histórico escolar completo, antes do
	 *            refinamento
	 * 
	 * @return : ArrayList<Integer> com os valores correspondentes às posições
	 *         inicial e final do bloco de aprovações
	 * 
	 */
	public static ArrayList<Integer> buscadoresDeIndex(String historicoEscolarExtraido) throws IOException {
		ArrayList<Integer> indexesComecoEFimDasMaterias = new ArrayList<Integer>();
		int indexComeco = historicoEscolarExtraido.indexOf("Situação Local\r\n") + 16;
		int indexFim = historicoEscolarExtraido.indexOf("Coeficiente de Rendimento Geral");

		indexesComecoEFimDasMaterias.add(indexComeco);
		indexesComecoEFimDasMaterias.add(indexFim);

		return indexesComecoEFimDasMaterias;
	}

	/**
	 * Este método se destina a separar o bloco de aprovações do restante das
	 * informações do histórico
	 * 
	 * @param historicoEscolarExtraido
	 *            (String) : String com o histórico escolar completo, antes do
	 *            refinamento
	 * @param indexComeco
	 *            (Int) : Indicador do começo do texto refinado
	 * @param indexFim
	 *            (Int) : Indicador do fim do texto refinado
	 * 
	 * @return Substring com o bloco de matérias separado do restante
	 * 
	 */
	public static String refinadorDeConteudoDoHistoricoEscolar(String historicoEscolarExtraido, int indexComeco,
			int indexFim) {
		String historicoEscolarRefinado = historicoEscolarExtraido.substring(indexComeco, indexFim);
		return historicoEscolarRefinado;
	}

}
