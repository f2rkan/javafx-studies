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
			//"jdbc:mysql://ServerIPAdresi/db_ismi", "kullanýcýAdý", "sifre"
			conn = DriverManager.getConnection("jdbc:mysql://localhost/projemdb", "root", "mysql");
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
	public static String MD5Sifrele(String icerik) {
		try {
			//getInstance icerisine hangi algoritmayý kullanacagýný yazarsýn
			MessageDigest md = MessageDigest.getInstance("MD5");
			//buradan gonderilen ifade daha sonra bayt bayt okunur
			//þifreleme algoritmalarý bayt dizisi olarak calýsýr
			byte[] sifrelenmis = md.digest(icerik.getBytes());
			//bayt bayt okudugumuz yapýyý arada 1 boþluk olacak þekilde integer yapýsýna donusturuyor
			BigInteger no = new BigInteger(1, sifrelenmis);
			//daha sonra da bunun HEX degerini hesaplarýz
			//kac karakter alýyorsa onu yazarsýn toString yapýsýnýn icine
			String hashIcerik = no.toString(16);
			//hash yapýsý 32 karaktere kadar gider
			while(hashIcerik.length() < 32) {
				hashIcerik = "0" + hashIcerik;
			}
			return hashIcerik;
			//þifreleme algoritmalarýnýn kendi iclerineki matematiksel dönüþümü vardýr; bunlardan birinde problem olmasý durumunda döndürülen exception, RuntimeException olarak degisir
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
