package bitirmeProjesiYedek;

import bitirmeProjesiYedek.Ogrenci;

public class Ogrenci {
	
	
	
	public int id;
	public String numara;
	public String isim;
	public double ort;
	public int hoca_id;
	
	public Ogrenci(){
		
	}
	public Ogrenci(String num , String isim , double ort ){
		
		this.numara = num;
		this.isim = isim;
		this.ort = ort;
		
	}
	
	public void kopyala(Ogrenci o1){
		id = o1.id;
		isim = o1.isim;
		ort = o1.ort;
		hoca_id = o1.hoca_id;	
		
	}

}
