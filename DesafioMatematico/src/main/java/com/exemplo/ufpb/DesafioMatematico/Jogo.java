package com.exemplo.ufpb.DesafioMatematico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.ufpb.DesafioMatematico.exececao.ExcecaoDesafioMatematico;

public class Jogo implements Serializable {

	private boolean acabou, nivel, finalizado, escolheuNivel, criarQuestao,
			escolherQuestao, finalizouNivel, respostaCorreta;
	private List<Questao> lista;
	private Questao ques;
	private String nome, listQuestaoFinalizada;
	private int ponto;

	public Jogo() {
		this.lista = new ArrayList<Questao>();
		this.ponto = 0;
		this.nome = "null";
		this.finalizado = true;
		this.acabou = false;
		this.escolheuNivel = false;
		this.listQuestaoFinalizada = new String();
		this.finalizouNivel = false;
		this.respostaCorreta = false;
	}

	public boolean isAcabou() {
		return this.acabou;
	}

	public void setNivel(boolean nivel) throws ExcecaoDesafioMatematico {
		if (this.finalizado) {
			this.nivel = nivel;
			this.escolheuNivel = true;
			this.finalizado = false;
			this.finalizouNivel = false;
		} else {
			throw new ExcecaoDesafioMatematico("Deveria finaliza o jogo!");
		}
	}

	public boolean isNivel() {
		return this.nivel;
	}

	public void finaliza() throws ExcecaoDesafioMatematico {
		if (this.escolheuNivel) {
			this.finalizado = true;
			this.escolheuNivel = false;
			this.criarQuestao = false;
			this.escolherQuestao = false;
			this.finalizouNivel = true;
			this.acabou = true;
			this.ques = null;
			this.esvaziaList();
		} else {
			throw new ExcecaoDesafioMatematico("Deveria inicializa o jogo!");
		}
	}

	public void setFinaliza() {
		this.finalizado = true;
	}

	private void esvaziaList() {
		this.lista = new ArrayList<Questao>();
	}

	public boolean getFinaliza() {
		return this.finalizado;
	}

	public boolean verificaResposta(String resposta) {
		if (this.ques != null) {
			this.respostaCorreta = this.ques.verificaResposta(resposta);
			if (this.respostaCorreta) {
				this.setPonto();
				this.respostaCorreta = false;
				this.listQuestaoFinalizada += this.ques.getRespostaCompleta()
						+ "\n";
				this.lista.remove(this.ques);
				this.ques = null;
				if (this.lista.size() == 0) {
					this.finaliza();
				}
			}
			return this.respostaCorreta;
		}
		return false;
	}

	public String listResposta() {
		if (this.finalizouNivel) {
			return this.listQuestaoFinalizada;
		}
		throw new ExcecaoDesafioMatematico("Espera se que finalize o nivel");
	}

	public void setPonto() throws ExcecaoDesafioMatematico {
		if (this.escolheuNivel && this.criarQuestao && this.escolherQuestao && this.respostaCorreta) {
			this.ponto++;
		} else {
			throw new ExcecaoDesafioMatematico(
					"Deveria escolher o nivel do jogo primeiro");
		}
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public Questao escolheQuestao() throws ExcecaoDesafioMatematico {
		if (this.escolheuNivel && this.criarQuestao) {
			int pos = (int) ((this.lista.size() - 1) * Math.random());
			this.ques = this.lista.get(pos);
			this.escolherQuestao = true;
			return this.ques;
		} else {
			throw new ExcecaoDesafioMatematico(
					"Deveria escolher o nivel do jogo!");
		}
	}

	public void criarQuestoes() throws ExcecaoDesafioMatematico {
		if (this.escolheuNivel) {
			if (this.nivel) {
				this.addQuestaoFacil();
			} else {
				this.addQuestaoDificil();
			}
			this.criarQuestao = true;
		} else {
			throw new ExcecaoDesafioMatematico(
					"Deveria escolher o nivel do jogo!");
		}
	}

	private void addQuestaoFacil() {
		Questao q1 = new Questao(true, "1 ? 2 = 3", "+", "1 + 2 = 3");
		Questao q2 = new Questao(true, "2 + ? = 5", "3", "2 + 3 = 5");
		Questao q3 = new Questao(true, "89 ? 90", "<", "89 < 90");

		this.lista.add(q1);
		this.lista.add(q2);
		this.lista.add(q3);
	}

	private void addQuestaoDificil() {
		Questao q1 = new Questao(true, "x + 2 = 3", "1", "1 + 2 = 3");
		Questao q2 = new Questao(true, "2 + 1 x 2 = ?", "4", "2 + 1 x 2 = 4");
		Questao q3 = new Questao(true, "2x + 6 = 4", "-1", "2 x (-1) + 6 = 4");

		this.lista.add(q1);
		this.lista.add(q2);
		this.lista.add(q3);
	}

	public int getPonto() {
		return this.ponto;
	}

	public List<Questao> getListQuestao() throws ExcecaoDesafioMatematico {
		if (this.escolheuNivel && this.criarQuestao) {
			return this.lista;
		}
		throw new ExcecaoDesafioMatematico("Quest�es n�o criadas!");
	}

	public boolean isCriarQuestao() {
		return criarQuestao;
	}

	public boolean isEscolherQuestao() {
		return escolherQuestao;
	}

	public boolean isEscolheuNivel() {
		return this.escolheuNivel;
	}

	public boolean isFinalizouNivel() {
		return this.finalizouNivel;
	}
}
