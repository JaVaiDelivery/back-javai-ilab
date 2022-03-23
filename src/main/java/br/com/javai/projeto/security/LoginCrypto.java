package br.com.javai.projeto.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class LoginCrypto {
	public static String encriptografar(String senha) throws Exception {
		String strChave = "123456789012345678901234567890 1";
		Key chave = new SecretKeySpec(strChave.getBytes(), "AES");
		
		Cipher cipher = Cipher.getInstance("AES");
		
		cipher.init(Cipher.ENCRYPT_MODE, chave);
		
		cipher.update(senha.getBytes());
		
		String senhaCripto = new String(cipher.doFinal(), "UTF-8");
		
		StringBuilder cryptoHex = new StringBuilder();
		
		return cryptoHex.toString();
	}
}
