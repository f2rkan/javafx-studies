package application;

import java.time.LocalDate;
import java.util.Date;

public class Kayitlar_islem {
	private int id;
	private String kul_ad, islemAciklama;
	private double ucret;
	private Date islem_Tarih;
	
	
	Kayitlar_islem() {
		
	}
	Kayitlar_islem(int id, String kulad, String islemAciklama, double islemTutar, Date islem_Tarih){
		this.id = id;
		this.kul_ad = kulad;
		this.islemAciklama = islemAciklama;
		this.ucret = islemTutar;
		this.islem_Tarih = islem_Tarih;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKul_ad() {
		return kul_ad;
	}
	public void setKul_ad(String kul_ad) {
		this.kul_ad = kul_ad;
	}
	public String getIslemAciklama() {
		return islemAciklama;
	}
	public void setIslemAciklama(String islemAciklama) {
		this.islemAciklama = islemAciklama;
	}
	public double getUcret() {
		return ucret;
	}
	public void setUcret(double ucret) {
		this.ucret = ucret;
	}
	public Date getIslem_Tarih() {
		return islem_Tarih;
	}
	public void setIslem_Tarih(Date islem_Tarih) {
		this.islem_Tarih = islem_Tarih;
	}
	
}
