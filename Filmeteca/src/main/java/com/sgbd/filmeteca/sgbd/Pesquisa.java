package com.sgbd.filmeteca.sgbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pesquisa {
    public static ResultSet todosUsuarios(Connection bd){
		Statement stmt = null;

		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select * from usuario;" ); //seleciona a tabela USUARIO
			
			stmt.close();
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean nome(Connection bd, String nome) {
		Statement stmt = null;
		boolean resposta = false;

		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select nome from usuario;" ); //seleciona a tabela USUARIO
			
			while (resultado.next()) { //verifica cada linha da tabela
				if (resultado.getString("nome").equals(nome))
					resposta = true;
			}
			stmt.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta;
	}
	
	public static boolean email(Connection bd, String email) {
		Statement stmt = null;
		boolean resposta = false;
		
		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select email from usuario;" ); //seleciona a tabela USUARIO
			
			while (resultado.next()) //verifica cada linha da tabela
				if (resultado.getString("email").equals(email))
					resposta = true;
			stmt.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta;
	}
}
