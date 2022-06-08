package com.sgbd.filmeteca;

import com.sgbd.filmeteca.sgbd.*;

//import java.sql.ResultSet;
//import java.sql.SQLException;

public class Execucao {
	
	public static void main (String[] args){

		String nome = "Giuliano";
		String senha = "Senha.123";
		String email = "email@yahoo.com.br";

		Banco.conectar();

		//Pesquisa.nome(Banco.getConexao(), nome);
		Usuario.cadastrar(Banco.getConexao(), nome, senha, email);

		/*
		boolean r;
		r = Pesquisa.nome(Banco.getConexao(), nome);
		if (r) System.out.println("achou nome");
		r = Pesquisa.email(Banco.getConexao(), email);
		if (r) System.out.println("achou email");

		Usuario.cadastrar(Banco.getConexao(), nome, senha, email);
		if(Usuario.login(Banco.getConexao(), email, senha)) System.out.println("Acertou senha"); else System.out.println("Errou senha");

		
		ResultSet todoMundo = Pesquisa.todosUsuarios(Banco.getConexao());
		try {
			while (todoMundo.next())
				System.out.println(todoMundo.getString("nome"));
			todoMundo.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/

		Banco.desconectar();
		
		System.out.println("finalizou");
	}
}