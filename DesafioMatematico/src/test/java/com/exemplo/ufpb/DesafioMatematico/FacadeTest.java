package com.exemplo.ufpb.DesafioMatematico;
import static org.junit.Assert.*;
import java.io.File;
import org.junit.*;
import com.exemplo.ufpb.DesafioMatematico.exececao.ExcecaoDesafioMatematico;
public class FacadeTest {
	private Facade facade;
	private Jogo jogo;
	@Before
	public void iniciar() {
		this.facade = new Facade();
		this.jogo = this.facade.getJogo();
	}
	@After
	public void finalizar() {
		File file = new File("desafioMatematico.txt");
		if (file.exists()) {
			file.delete();
		}
	}
	@Test
	public void criarJogoAcabado() {
		assertFalse("O jogo iniciou acabado.", this.jogo.isAcabou());
	}
	@Test
	public void finalizaJogo() {
		assertTrue("O jogo deve iniciar finalizado.", this.jogo.getFinaliza());
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void finalizaJogoAntesDeIniciar() {
		this.jogo.finaliza();
	}
	@Test
	public void escolheNivelFacilEndVerificaFinaliza(){
		this.jogo.setNivel(true);
		assertFalse("Esperava-se que finaliza estive se verdadeiro", this.jogo.getFinaliza());
	}
	@Test
	public void escolheNivelDificilEndVerificaFinaliza(){
		this.jogo.setNivel(false);
		assertFalse("Esperava-se que finaliza estive se verdadeiro", this.jogo.getFinaliza());
	}
	@Test
	public void naoEscolheNivelFacilEndVerificaFinaliza(){
		assertTrue("O jogo deve iniciar finalizado", this.jogo.getFinaliza());
	}
	@Test
	public void naoEscolheNivelDificilEndVerificaFinaliza(){
		assertTrue("O jogo deve iniciar finalizado", this.jogo.getFinaliza());
	}
	@Test
	public void informaNome() {
		this.jogo.setNome("Vagner");
		assertEquals("Vagner", this.jogo.getNome());
	}
	@Test
	public void naoInformaNome() {
		assertEquals("null", this.jogo.getNome());
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void incrementaPontuacaoSemEscolheNivel() {
		this.jogo.setPonto();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void incrementaPontuacaoSemCriarQuestoes() {
		this.jogo.setNivel(true);
		this.jogo.setPonto();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void incrementaPontuacaoSemRespondeQuestao() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		this.jogo.escolheQuestao();
		this.jogo.setPonto();
	}
	@Test
	public void escolherNivelFacil() {
		this.jogo.setNivel(true);
		assertTrue("Esperava que o jogo utilizasse o nível fácil.",this.jogo.isNivel());
	}
	@Test
	public void escolherNivelDificil() {
		this.jogo.setNivel(false);
		assertFalse("Esperava que o jogo utilizasse o nível difícil.",this.jogo.isNivel());
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void criarQuestoesSemNivel() {
		this.jogo.criarQuestoes();
	}
	@Test
	public void criarQuestaoFacilEndEscolheQuestaoFacil(){
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		assertTrue("Esperava que a questão utilizasse o nível fácil.", this.jogo.escolheQuestao().getNivelQuestao());
	@Test
	public void criarQuestaoDificilEndEscolheQuestaoDificil(){
		this.jogo.setNivel(false);
		this.jogo.criarQuestoes();
		assertFalse("Esperava que a questão utilizasse nivel dificil", this.jogo.escolheQuestao().getNivelQuestao());
		}
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void escolherQuestaoSemNivel() {
		this.jogo.escolheQuestao();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void escolherQuestaoSemCriarQuestoes() {
		this.jogo.setNivel(true);
		this.jogo.escolheQuestao();
	}
	@Test
	public void finalizaJogoEndEscolheDeNovo() {
		this.escolheNivelFacilEndVerificaFinaliza();
		this.jogo.finaliza();
		this.jogo.setNivel(false);
		this.jogo.criarQuestoes();
		assertFalse("Esperava que a questão utilizasse o nível difícil.", this.jogo.escolheQuestao().getNivelQuestao());
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void finalizaJogoEndEscolheDeNovoSemEscolheNivel() {
		this.escolheNivelFacilEndVerificaFinaliza();
		this.jogo.finaliza();
		this.jogo.criarQuestoes();
		this.jogo.escolheQuestao();
	}
	@Test
	public void escolherNivelDiferenteDuasVezes() {
		this.jogo.setNivel(true);
		this.jogo.finaliza();
		this.jogo.setNivel(false);
		assertFalse("Esperava que jogador utilizasse o nível difícil.",this.jogo.isNivel());
	}
	@Test
	public void escolherNivelIgualDuasVezes() {
		this.jogo.setNivel(true);
		this.jogo.finaliza();
		this.jogo.setNivel(true);
		assertTrue("Esperava que jogador utilizasse o nível difícil.",this.jogo.isNivel());
	}
	@Test
	public void verificaPontuacaoAposFinalizarJogo() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		this.jogo.finaliza();
		assertEquals(0, this.jogo.getPonto());
		this.jogo.setNivel(false);
		assertFalse("Esperava que jogador utilizasse o nível difícil.", this.jogo.isNivel());
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void verificaListQuestaoAposFinalizarJogo() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		this.jogo.finaliza();
		this.jogo.getListQuestao();
	}
	@Test
	public void finalizarJogoSemCriarQuestoes() {
		this.jogo.setNivel(true);
		this.jogo.finaliza();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void escolherNivelDuasVezesSemFinalizar() {
		this.jogo.setNivel(true);
		this.jogo.setNivel(false);
	}
	@Test
	public void gerarQuestaoFacil() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		Questao q = this.jogo.escolheQuestao();
		assertTrue("Esperava que a questão seja fácil.", q.getNivelQuestao());
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void escolherQuestaoSemGerarQuestaoFacil() {
		this.jogo.setNivel(true);
		this.jogo.escolheQuestao();
	}
	@Test
	public void verificaQuestaoCorreta() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		Questao q = this.jogo.escolheQuestao();
		assertTrue(q.verificaResposta(q.getResposta()));
		this.jogo.verificaResposta(q.getResposta());
		assertTrue("Esperava que a pontuação fosse 1.", this.jogo.getPonto() == 1);
	}
	@Test
	public void verificaQuestaoCorretaEndPorcentamAcertos() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		Questao q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		assertTrue("Esperava-se que a porsentagem de acertos fosse diferente de .0", this.jogo.getPorcentagemAcertos() != 0);
	}
	@Test
	public void verificaQuestaoIncorretaEndPorcentamAcertos() {
		this.verificaQuestaoIncorreta();
		assertTrue("Esperava-se que a porcentagem de acertos fosse igual a 0.", this.jogo.getPorcentagemAcertos() == 0);
	}
	@Test
	public void verificaQuestaoIncorreta(){
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		Questao q = this.jogo.escolheQuestao();
		assertFalse(q.verificaResposta("Resposta errada!"));
		this.jogo.verificaResposta("Resposta errada!");
		assertTrue("Esperava que a pontuação fosse 0.", this.jogo.getPonto() == 0);
	}
	@Test
	public void recebeQuestaoNula() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		this.jogo.escolheQuestao();
		assertFalse("Esperava um valor falso.", this.jogo.verificaResposta(null));
	}
	@Test
	public void respondeDuasVezesAhMesmaQuestao() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		Questao q = this.jogo.escolheQuestao();
		assertTrue(q.verificaResposta(q.getResposta()));
		this.jogo.verificaResposta(q.getResposta());
		this.jogo.verificaResposta(q.getResposta());
		assertTrue("Esperava que a pontuação fosse 1.", this.jogo.getPonto() == 1);
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void questaoEscolhidaNaoSairDaLista() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		Questao q = this.jogo.escolheQuestao();
		for (Questao q1 : this.jogo.getListQuestao()) {
			if (q1.equals(q)) {
				throw new ExcecaoDesafioMatematico("Questão não deveria ter sido removido.");
			}
		}
	}
	@Test
	public void questaoRespondidaSairDaLista() {
		this.criarQuestaoFacilEndEscolheQuestaoFacil();
		Questao q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		for (Questao q1 : this.jogo.getListQuestao()) {
			if (q1.equals(q)) {
				fail("Questão deveria ter sido removida da lista.");
			}
		}
	}
	@Test
	public void respondeTodasQuestoesEndAcabaJogo() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		Questao q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		assertTrue("Deveria ter acabado o jogo.", this.jogo.isAcabou());
	}
	@Test
	public void respondeTodasQuestoesEndVerListaResposta() {
		this.respondeTodasQuestoesEndAcabaJogo();
		this.jogo.listResposta();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void naoRespondeTodasQuestoesEndVerListaResposta() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		Questao q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		this.jogo.listResposta();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void naoEscolheNivelEndVerListaResposta() {
		this.jogo.criarQuestoes();
		Questao q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		this.jogo.listResposta();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void naoCriarQuestoesEndVerListaResposta() {
		this.jogo.setNivel(true);
		Questao q = this.jogo.escolheQuestao();
		this.jogo.verificaResposta(q.getResposta());
		this.jogo.listResposta();
	}
	@Test
	public void respondeTodasQuestoesEndVerPontuacao() {
		this.respondeTodasQuestoesEndAcabaJogo();
		assertTrue("Deveria estar diferente de 0", this.jogo.getPonto() != 0);
	}
	@Test
	public void respondeTodasQuestoesEndVerFinalizacao() {
		this.respondeTodasQuestoesEndAcabaJogo();
		assertTrue("Deveria estar finalizado o jogo.", this.jogo.getFinaliza());
	}
	@Test
	public void respondeTodasQuestoesEndVerEscolheuNivel() {
		this.respondeTodasQuestoesEndAcabaJogo();
		assertFalse(this.jogo.isEscolheuNivel());
	}
	@Test
	public void respondeTodasQuestoesEndVerCriarQuestao() {
		this.respondeTodasQuestoesEndAcabaJogo();
		assertFalse(this.jogo.isCriarQuestao());
	}
	@Test
	public void respondeTodasQuestoesEndVerEscolheQuestao() {
		this.respondeTodasQuestoesEndAcabaJogo();
		assertFalse(this.jogo.isEscolherQuestao());
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void salvaSemTerIniciadoJogo() {
		this.facade.salvaJogo();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void retomaJogoSemTerIniciadoJogo() {
		this.facade.retomaJogo();
	}
	@Test
	public void salvaJogoDuasVezes() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		this.jogo.escolheQuestao();
		this.facade.salvaJogo();
		this.facade.salvaJogo();
	}
	@Test
	public void retomaJogoSalvo() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		this.jogo.escolheQuestao();
		this.facade.salvaJogo();
		this.facade.retomaJogo();
	}
	@Test
	public void retomaJogoDuasVezes() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		this.jogo.escolheQuestao();
		this.facade.salvaJogo();
		this.facade.retomaJogo();
		this.facade.retomaJogo();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void retomaJogoSemSalva() {
		this.facade.retomaJogo();
	}
	@Test
	public void novaPartida() {
		this.jogo.setNivel(true);
		this.jogo.criarQuestoes();
		this.jogo.escolheQuestao();
		this.jogo.verificaResposta(null);
	}
	@Test
	public void salvaPartidaEndIniciarNovaPartida() {
		this.novaPartida();
		this.facade.salvaJogo();
		this.novaPartida();
	}
	@Test
	public void salvaPartidaEndRetomaJogo() {
		this.novaPartida();
		this.facade.salvaJogo();
		this.facade.retomaJogo();
	}
	@Test(expected = ExcecaoDesafioMatematico.class)
	public void novaPartidaDuasVezesTest() {
		this.novaPartida();
		this.novaPartida();
	}
}