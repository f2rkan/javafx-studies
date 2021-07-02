package com.JavaMySQL.Util;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.management.RuntimeErrorException;
public class VeritabaniUtil {
	
	static Connection conn = null;
	public static Connection Baglan() {
		try {
			//"jdbc:mysql://ServerIPAdresi/db_ismi", "kullan�c�Ad�", "sifre"
			conn = DriverManager.getConnection("jdbc:mysql://localhost/projemdb", "root", "mysql");
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
	public static String MD5Sifrele(String icerik) {
		try {
			//getInstance icerisine hangi algoritmay� kullanacag�n� yazars�n
			MessageDigest md = MessageDigest.getInstance("MD5");
			//buradan gonderilen ifade daha sonra bayt bayt okunur
			//�ifreleme algoritmalar� bayt dizisi olarak cal�s�r
			byte[] sifrelenmis = md.digest(icerik.getBytes());
			//bayt bayt okudugumuz yap�y� arada 1 bo�luk olacak �ekilde integer yap�s�na donusturuyor
			BigInteger no = new BigInteger(1, sifrelenmis);
			//daha sonra da bunun HEX degerini hesaplar�z
			//kac karakter al�yorsa onu yazars�n toString yap�s�n�n icine
			String hashIcerik = no.toString(16);
			//hash yap�s� 32 karaktere kadar gider
			while(hashIcerik.length() < 32) {
				hashIcerik = "0" + hashIcerik;
			}
			return hashIcerik;
			//�ifreleme algoritmalar�n�n kendi iclerineki matematiksel d�n���m� vard�r; bunlardan birinde problem olmas� durumunda d�nd�r�len exception, RuntimeException olarak degisir
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
