package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class Principal {
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
		boolean resposta = false;
			
		try {
			comando = bd.createStatement();
			comando.execute(cmd);
			System.out.println("Comando SQL executado. BD atualizado.");
			resposta = true;
		} catch (SQLException e) {
			System.out.println("Error ao executar o comando SQL.");
			e.printStackTrace();
		}
		return resposta;
	}
	
	public static boolean busca_nome(Connection bd, String nome) {
		Statement stmt = null;
		boolean resposta = false;

		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select nome from usuario;" ); /*seleciona a tabela USUARIO*/
			
			while (resultado.next()) { /*verifica cada linha da tabela*/
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
	
	public static boolean busca_email(Connection bd, String email) {
		Statement stmt = null;
		boolean resposta = false;
		
		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select email from usuario;" ); /*seleciona a tabela USUARIO*/
			
			while (resultado.next()) /*verifica cada linha da tabela*/
				if (resultado.getString("email").equals(email))
					resposta = true;
		stmt.close();
		resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta;
	}
	
	public static boolean login(Connection bd, String email, String senha) {
		Statement stmt = null;
		boolean resposta = false;
		
		try {
			stmt = bd.createStatement();
			ResultSet resultado = stmt.executeQuery( "select email, senha from usuario" ); /*seleciona a tabela USUARIO*/
			
			while (resultado.next()) /*verifica cada linha da tabela*/
				if (resultado.getString("email").equals(email))
					if (resultado.getString("senha").equals(senha)){
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

	public static boolean cadastrar_usuario(Connection bd, String nome, String senha, String email) {
		if (busca_nome(bd,nome)) {
			System.out.println("Usuario ja cadastrado.");
			return false;
		} if (busca_email(bd,email)) {
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
		final Connection bd = bd_conectar(BD_URL, BD_USUARIO, BD_SENHA);
		
		String nome = "Giuliano";
		String senha = "Senha.123";
		String email = "email@yahoo.com.br";
		
		//cadastrar_usuario(bd, nome, senha, email);
		if(login(bd, email, senha)) System.out.println("Acertou senha"); else System.out.println("Errou senha");

		try {bd.close();} catch (SQLException e) {e.printStackTrace();}
		
		System.out.println("finalizou");
	}
}