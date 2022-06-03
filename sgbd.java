package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.util.Scanner;

public class Abrir_Fechar_Conexao {
	//credenciais BD local para testes
	private static final String BD_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String BD_USUARIO = "postgres";
	private static final String BD_SENHA = "1234";

	public static Connection bd_conectar(String url, String usuario, String senha){
		Connection bd;
		try{
			bd = DriverManager.getConnection(url, usuario, senha);
			return bd; /*BD conectado com sucesso*/
		} catch (SQLException e){
			System.out.println("Falha ao conectar ao BD. Verifique as credenciais e tente novamente.");
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}

	public static boolean executar_sql(Connection bd, String cmd){
		Statement comando;
			
		try {
			comando = bd.createStatement();
			comando.execute(cmd);
			System.out.println("Comando SQL executado. BD atualizado.");
			return true;
		} catch (SQLException e) {
			System.out.println("Error ao executar o comando SQL.");
			e.printStackTrace();
			return false;
		}
	}
	
	public static short busca_usuario(Connection bd, String nome, String email) {
		Statement stmt = null;
		
		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select * from public.\"usuario\" ;" ); /*seleciona a tabela USUARIO*/
			
			while (resultado.next()) { /*verifica cada linha da tabela*/
				if (resultado.getString("nome").equals(nome)) {
					stmt.close();
					resultado.close();
					return 1;
				}
				if (resultado.getString("email").equals(email)) {
					stmt.close();
					resultado.close();
					return 2;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	public static boolean cadastrar_usuario(Connection bd, String nome, String senha, String email) {
		
		switch (busca_usuario(bd, nome, email)) {
		case -1:
			System.out.println("Nao foi possivel verificar o banco de dados.");
			return false;
		case 0:
			break;
		case 1:
			System.out.println("Usuario ja cadastrado.");
			return false;
		case 2:
			System.out.println("E-Mail ja cadastrado.");
			return false;
		}
		
		java.util.Date data = new java.util.Date(System.currentTimeMillis());
		java.sql.Date sqlDate1 = new java.sql.Date(data.getTime());		
		Timestamp ts = new Timestamp(sqlDate1.getTime());
		
		String cmd = "insert into Usuario (nome, senha, email, data_inscricao)values(?,?,?,?)";
        	PreparedStatement stmt;
		try {
			stmt = bd.prepareStatement(cmd);
			stmt.setString(1,nome);
	        stmt.setString(2,senha);
	        stmt.setString(3,email);
	        stmt.setTimestamp(4, ts);
	        stmt.execute();
	        stmt.close();
		} catch (SQLException e) {
			System.out.println("Erro ao criar usuario.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main (String[] args){
		Connection bd = bd_conectar(BD_URL, BD_USUARIO, BD_SENHA);
		
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Cadastrar Usuario");
			System.out.println("Nome: ");
			String nome = scan.nextLine();
			System.out.println("Senha: ");
			String senha = scan.nextLine();
			System.out.println("Email: ");
			String email = scan.nextLine();
			
			cadastrar_usuario(bd, nome, senha, email);
		}
		try {bd.close();} catch (SQLException e) {e.printStackTrace();}
	}
}
