package com.exemplo.ufpb.DesafioMatematico.arquivo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.exemplo.ufpb.DesafioMatematico.Jogo;

public class Gravador {

	public Jogo lerArquivo() throws IOException{
		ObjectInputStream object = null;
		try {
			object = new ObjectInputStream(new FileInputStream("desafioMatematico.txt"));
			return (Jogo) object.readObject();
		} catch (FileNotFoundException e) {
			throw new IOException("Arquivo não encontrado!");
		}catch (ClassNotFoundException e) {
			throw new IOException("Classe dos objetos não foram encontrados!",e);
		}finally{
			if(object != null){
				object.close();
			}
		}
	}
	public void salvaArquivo(Jogo jogo) throws IOException{
		ObjectOutputStream out = null;
		try{
			out = new ObjectOutputStream(new FileOutputStream("desafioMatematico.txt"));
			out.writeObject(jogo);
		}catch(FileNotFoundException e){
			throw new IOException("Arquivo não encontrado!");
		}catch(IOException e){
			throw e;
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
}
