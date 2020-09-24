package bitirmeProjesiYedek;

import java.util.Random;

public class Genetik {
	static Random sayi_uret = new Random();

	public static Kromozom[] crossover(Kromozom k1, Kromozom k2){

		Kromozom croslar[] = new Kromozom[2];
		int ogrenci_sayisi = k1.ogrenciler.length;
		int sayi1 = sayi_uret.nextInt(ogrenci_sayisi);
		int sayi2 = sayi_uret.nextInt(ogrenci_sayisi);
		
		while(sayi1 == sayi2){			
			sayi2 = sayi_uret.nextInt(ogrenci_sayisi);			
		}
		int buyuk = sayi1, kucuk=sayi2;
		if(sayi1<sayi2){
			
			buyuk=sayi2;
			kucuk=sayi1;
			
		}
		croslar[0] = new Kromozom(ogrenci_sayisi);
		croslar[0].hoca_sayisi = k1.hoca_sayisi;
		
		croslar[1] = new Kromozom(ogrenci_sayisi);
		croslar[1].hoca_sayisi = k1.hoca_sayisi;
		
		
		for(int i = 0 ; i<kucuk ; i++){			
			croslar[0].ogrenciler[i] = new Ogrenci();
			croslar[0].ogrenciler[i].kopyala(k1.ogrenciler[i]);
			
			croslar[1].ogrenciler[i] = new Ogrenci();
			croslar[1].ogrenciler[i].kopyala(k2.ogrenciler[i]);
			
		}
		for(int i = kucuk ; i<buyuk ; i++){			
			croslar[0].ogrenciler[i] = new Ogrenci();
			croslar[0].ogrenciler[i].kopyala(k2.ogrenciler[i]);
			
			croslar[1].ogrenciler[i] = new Ogrenci();
			croslar[1].ogrenciler[i].kopyala(k1.ogrenciler[i]);
		}
		for(int i = buyuk ; i<ogrenci_sayisi ; i++){			
			croslar[0].ogrenciler[i] = new Ogrenci();
			croslar[0].ogrenciler[i].kopyala(k1.ogrenciler[i]);
			
			croslar[1].ogrenciler[i] = new Ogrenci();
			croslar[1].ogrenciler[i].kopyala(k2.ogrenciler[i]);
		}	
		
		mutasyon(croslar[0]);
		mutasyon(croslar[1]);		

		return croslar;
		
	}
	
	static public void  mutasyon(Kromozom c1){
		
		int sayi1 = sayi_uret.nextInt(c1.ogrenciler.length);
		int sayi2 = sayi_uret.nextInt(c1.ogrenciler.length);
		int sayi3 = sayi_uret.nextInt(c1.hoca_sayisi);
		int sayi4 = sayi_uret.nextInt(c1.hoca_sayisi);
		
		while(sayi1 == sayi2){			
			sayi2 = sayi_uret.nextInt(c1.ogrenciler.length);			
		}
		
		c1.ogrenciler[sayi1].hoca_id = sayi3;
		c1.ogrenciler[sayi2].hoca_id = sayi4;

	}
	
}
