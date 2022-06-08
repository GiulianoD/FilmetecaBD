package com.sgbd.filmeteca.secure;

import org.mindrot.jbcrypt.BCrypt;

public class Senhas {
	private static int gerador = 27;

	public static String codificaString(String texto) {
		String salt = BCrypt.gensalt(gerador);
		return BCrypt.hashpw(texto, salt);
	}

	public static boolean checaSenha(String texto, String senha) {
		if(null == senha || !senha.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("me apaga depois");

		return BCrypt.checkpw(texto, senha);
	}
}
