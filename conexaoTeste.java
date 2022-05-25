package com.api.filmeteca.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URI;
import java.net.URISyntaxException;

public class conexao {

	private static Connection conectar() throws URISyntaxException, SQLException{
		URI dbURI= new URI (System.getenv("ec2-54-86-224-85.compute-1.amazonaws.com")); //DATABASE_URL
		
		String usuario = dbURI.getUserInfo().split(":")[0];
		String senha = dbURI.getUserInfo().split(":")[1];
		String dbURL = "jdbc:postgresql://" + dbURI.getHost() + ';' + dbURI.getPort() + dbURI.getPath() + "?sslmode=require";
		
		return DriverManager.getConnection(dbURL, usuario, senha);
	}
	
	public static void main (String[] args){
		
		try {
			Connection conexao = conectar();
			System.out.println("Banco de Dados conectado com sucesso.");
			
			conexao.close();
		} catch (URISyntaxException e) {
			System.out.println("Error ao conectar com o bando de dados.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error ao conectar com o bando de dados.");
			e.printStackTrace();
		}
	}
}
