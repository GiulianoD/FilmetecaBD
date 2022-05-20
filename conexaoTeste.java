package com.api.filmeteca.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
  public static void main(String[] args) {
    String jdbcURL = "ec2-54-86-224-85.compute-1.amazonaws.com";
    String usuario = "obfotxtfenewnb";
    String senha = "d423f724b4c3d1d15bbc5d59bce5e59fc10338dc3f6678b7afeff3c3fb524b45";

    try{
      Connection conexao = DriverManager.getConnection(jdbcURL, usuario, senha);
      /*Statement statement = conexao.createStatement();*/

      System.out.println("Conectou");
      conexao.close();

    }catch(SQLException e){
      System.out.println("Nao conectou");
      e.printStackTrace();
      
    }
  }
}
