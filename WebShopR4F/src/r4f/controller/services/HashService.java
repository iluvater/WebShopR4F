/**
 * 
 */
package r4f.controller.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * @author Ture
 *	This class converts plaintext password to a SHA256 hash
 */
public class HashService {
	
	public String encrypt(String password){
		MessageDigest messageDigest = null;
		String hash = null;
		try{
			messageDigest = MessageDigest.getInstance("SHA-256");
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		try {
			messageDigest.update(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		byte raw[] = messageDigest.digest();
		hash = (new BASE64Encoder()).encode(raw);
		return hash;
	}
}
