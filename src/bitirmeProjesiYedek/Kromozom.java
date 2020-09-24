package bitirmeProjesiYedek;

import bitirmeProjesiYedek.Ogrenci;

public class Kromozom {
	
	public Ogrenci [] ogrenciler;
	public double puan;
	public int hoca_sayisi;
	
	public Kromozom(int eleman_sayisi){
		
		ogrenciler = new Ogrenci[eleman_sayisi];

	}
	
	public boolean equals (Kromozom k){
		
		for(int i = 0 ; i<ogrenciler.length ;i++){
			
			if(ogrenciler[i].hoca_id != k.ogrenciler[i].hoca_id){
				return true;
			}
					
			
		}
		return false;
	}
	
	public double ortalamaHesapla(){//Kromozomlarýn kendi ortalamasýný hesapladýk
		
		double toplam = 0;
		for(int i = 0 ; i<ogrenciler.length ; i++){		
			
			toplam+= ogrenciler[i].ort;					
		
		}
		return toplam/ogrenciler.length;
		
	}
	public double hocaOrtalamaHesapla(int hoca_id){
		
		double toplam = 0;
		int ogrenci_sayisi = 0;
		for(int i = 0 ; i<ogrenciler.length ; i++){	
			
			if(ogrenciler[i].hoca_id == hoca_id){
				
				toplam+= ogrenciler[i].ort;	
				ogrenci_sayisi++;
				
			}			
		
		}
		if(ogrenci_sayisi == 0)
			return 0;
		return toplam/(ogrenci_sayisi);
		
	}

}
