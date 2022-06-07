package com.sgbd.filmeteca.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
	private static final String usuario = "postgres";
	private static final String senha = "1234";
    private static Connection conexao;

    public static void conectar(){
		try{
			conexao = DriverManager.getConnection(url, usuario, senha);
            return;
		} catch (SQLException e){
			System.out.println("Falha ao conectar ao BD. Verifique as credenciais e tente novamente.");
			e.printStackTrace();
			System.exit(0);
		}
	}

    public static Connection getConexao(){
        return conexao;
    }

    public static void desconectar(){
        try {
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Falha ao desconectar do BD.");
            e.printStackTrace();
        }
    }
}
