package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.net.URI;
import java.net.URISyntaxException;

public class Teste {
	private static final String URI_BD = "postgres://uaoqajtypqbpui:2bd8e016b21b8fdb6b2e716a55af85ebac9e421c038c52835bf64a8720150cdb@ec2-54-165-90-230.compute-1.amazonaws.com:5432/d4picedhjsu894";
	
	private static Connection conectar(String uri) throws URISyntaxException, SQLException{
		
		URI dbURI= new URI (System.getenv(uri));
		
		String usuario = dbURI.getUserInfo().split(":")[0];
		String senha = dbURI.getUserInfo().split(":")[1];
		String dbURL = "jdbc:postgresql://" + dbURI.getHost() + ':' + dbURI.getPort() + dbURI.getPath() + "?sslmode=require";
		
		return DriverManager.getConnection(dbURL, usuario, senha);
	}
	
	public static void main (String[] args){
	
		try {
			Connection conexao = conectar(URI_BD);
			System.out.println("DBserver succesfully connected.");
			
			conexao.close();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
