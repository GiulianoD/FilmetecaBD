package com.sgbd.filmeteca;

import com.sgbd.filmeteca.sgbd.*;

public class Execucao {
	
	public static void main (String[] args){
		Banco.conectar();

		String nome = "Giuliano";
		String senha = "Senha.123";
		String email = "email@yahoo.com.br";
		
		boolean r;
		r = Pesquisa.nome(Banco.getConexao(), nome);
		if (r) System.out.println("achou nome");
		r = Pesquisa.email(Banco.getConexao(), email);
		if (r) System.out.println("achou email");

		//cadastrar_usuario(bd, nome, senha, email);
		if(Usuario.login(Banco.getConexao(), email, senha)) System.out.println("Acertou senha"); else System.out.println("Errou senha");

		Banco.desconectar();
		
		System.out.println("finalizou");
	}
}