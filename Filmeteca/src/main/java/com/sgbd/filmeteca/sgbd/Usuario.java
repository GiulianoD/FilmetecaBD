package com.sgbd.filmeteca.sgbd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.sgbd.filmeteca.secure.*;

public class Usuario {
    public static boolean login(Connection bd, String email, String senha) {
		Statement stmt = null;
		boolean resposta = false;
		String senhaCodificada = Senhas.codificaString(senha);
		
		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select email, senha from usuario" ); //seleciona a tabela USUARIO
			
			while (resultado.next()) //verifica cada linha da tabela
				if (resultado.getString("email").equals(email))
					if (resultado.getString("senha").equals(senhaCodificada)){
						resposta = true;
						break;
					}
		stmt.close();
		resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta;
	}

	public static boolean cadastrar(Connection bd, String nome, String senha, String email) {
		boolean resposta = false;
		
		String senhaCodificada = Senhas.codificaString(senha);

		if (Pesquisa.nome(bd,nome))
			System.out.println("Usuario ja cadastrado.");
		if (Pesquisa.email(bd,email))
			System.out.println("E-Mail ja cadastrado.");
		
		java.util.Date data = new java.util.Date(System.currentTimeMillis());
		java.sql.Date sqlDate1 = new java.sql.Date(data.getTime());		
		Timestamp ts = new Timestamp(sqlDate1.getTime());
		
		String cmd = "insert into usuario (nome, senha, email, data_inscricao)values(?,?,?,?)";
        PreparedStatement stmt;
		try {
			stmt = bd.prepareStatement(cmd);
			stmt.setString(1,nome);
	        stmt.setString(2,senhaCodificada);
	        stmt.setString(3,email);
	        stmt.setTimestamp(4, ts);
	        stmt.execute();
	        stmt.close();
			resposta = true;
		} catch (SQLException e) {
			System.out.println("Erro ao criar usuario.");
			e.printStackTrace();
		}
		return resposta;
	}
}
