package com.exemplo.ufpb.DesafioMatematico;

import java.io.Serializable;

public class Questao implements Serializable {

	private boolean nivel;
	private String resposta, questao, respostaCompleta;

	public Questao(boolean nivel, String questao, String resposta, String respostaCompleta) {
		this.nivel = nivel;
		this.questao = questao;
		this.resposta = resposta;
		this.respostaCompleta = respostaCompleta;
	}

	public boolean getNivelQuestao() {
		return this.nivel;
	}

	public String getResposta() {
		return this.resposta;
	}
	
	public String getRespostaCompleta() {
		return this.respostaCompleta;
	}

	public String getQuestao() {
		return this.questao;
	}

	public boolean verificaResposta(String resposta) {
		if (this.resposta.equals(resposta)) {
			return true;
		}
		return false;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questao other = (Questao) obj;
		if (nivel != other.nivel)
			return false;
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		if (resposta == null) {
			if (other.resposta != null)
				return false;
		} else if (!resposta.equals(other.resposta))
			return false;
		return true;
	}
}
