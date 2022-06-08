package com.sgbd.filmeteca.sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
    private static final String url = "jdbc:postgresql://ec2-54-165-90-230.compute-1.amazonaws.com:5432/d4picedhjsu894";
	private static final String usuario = "uaoqajtypqbpui";
	private static final String senha = "2bd8e016b21b8fdb6b2e716a55af85ebac9e421c038c52835bf64a8720150cdb";
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

    public static boolean executarSql(String cmd){
		Statement comando;
		boolean resposta = false;
			
		try {
			comando = conexao.createStatement();
			comando.execute(cmd);
			resposta = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta;
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
