package com.exemplo.ufpb.DesafioMatematico;

import java.io.IOException;

import com.exemplo.ufpb.DesafioMatematico.arquivo.Gravador;
import com.exemplo.ufpb.DesafioMatematico.exececao.ExcecaoDesafioMatematico;

public class Facade {

	private Jogo jogo;
	private Gravador gravador;

	public Facade() {
		this.gravador = new Gravador();
		this.jogo = new Jogo();
	}

	public void novaPartida() {
		this.jogo.finaliza();
	}

	public void retomaJogo() throws ExcecaoDesafioMatematico {
		try {
			this.jogo = this.gravador.lerArquivo();
		} catch (IOException e) {
			throw new ExcecaoDesafioMatematico("Arquivo não existente!");
		}
	}

	public void salvaJogo() throws ExcecaoDesafioMatematico {
		if (this.jogo.isEscolheuNivel()) {
			try {
				this.gravador.salvaArquivo(this.jogo);
				this.jogo.setFinaliza();
			} catch (IOException e) {
				throw new ExcecaoDesafioMatematico("Jogo não foi salvo!");
			}
		} else {
			throw new ExcecaoDesafioMatematico("Jogo não foi inicializado!");
		}
	}

	public Jogo getJogo() {
		return this.jogo;
	}
}
