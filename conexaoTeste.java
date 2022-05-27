package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Teste {
	// URL_BD == "jdbc:MaintenaceDatabase://host:port/DatabaseRestriction"
	private static final String URL_BD = "jdbc:" + "postgresql://" + "ec2-54-86-224-85.compute-1.amazonaws.com" + ":" +"5432" + "/" + "d8jnfv529iios4";
	private static final String USER = "obfotxtfenewnb";
	private static final String PASSWORD = "d423f724b4c3d1d15bbc5d59bce5e59fc10338dc3f6678b7afeff3c3fb524b45";
	
	public static void main (String[] args){
	
		try {
			Connection conexao = DriverManager.getConnection(URL_BD, USER, PASSWORD);
			System.out.println("DBserver succesfully connected.");
			
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
