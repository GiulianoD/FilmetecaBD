package sgbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Abrir_Fechar_Conexao {
	//credenciais BD local para testes
	private static final String BD_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String BD_USUARIO = "postgres";
	private static final String BD_SENHA = "1234";

	private static Connection bd_conectar(String url, String usuario, String senha){
		Connection bd;
		try{
			bd = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Aberta a conexao com o BD.");
			return bd;
		} catch (SQLException e){
			System.out.println("Falha ao conectar ao BD. Tente novamente.");
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}

	private static void executar_sql(Connection bd, String cmd){
		Statement comando;
			
		try {
			comando = bd.createStatement();
			comando.execute(cmd);
			System.out.println("Comando SQL executado. BD atualizado.");
			return;
		} catch (SQLException e) {
			System.out.println("Error ao executar o comando SQL.");
			e.printStackTrace();
			return;
		}
	}

	public static void main (String[] args) throws SQLException{
		Connection bd = bd_conectar(BD_URL, BD_USUARIO, BD_SENHA);

		executar_sql(bd, "CREATE TABLE Filme(id serial);");
		executar_sql(bd, "DROP TABLE Filme;");

		bd.close();

		System.out.println("Tudo certo.");
	}
}
