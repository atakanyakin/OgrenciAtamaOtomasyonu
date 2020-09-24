package bitirmeProjesiYedek;


import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class calisma2 {
	
	public calisma2(){
		
		int kromozom_sayisi = 10;
		int top_ogr_sayisi = 0;
		try {
			top_ogr_sayisi = Excel.toplamOgrenciSayisi();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		Kromozom kromozomlar[] = new Kromozom[kromozom_sayisi];
		String []hocalar = null ;
		try {
			hocalar = Excel.hocaDosyaOku();			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		Random sayi_uret = new Random();		
		
		Ogrenci tum_ogrenciler[] = Excel.tumOgrenciGetir(top_ogr_sayisi);
		for(int k = 0 ; k<kromozom_sayisi ; k++){//10 Adet Kromozom oluþturuldu.
			
			
			kromozomlar[k] = new Kromozom(top_ogr_sayisi);
			kromozomlar[k].hoca_sayisi = hocalar.length;
			for(int i = 0 ; i<top_ogr_sayisi ; i++){
				Ogrenci tmp_ogrenci = new Ogrenci();
				tmp_ogrenci.kopyala(tum_ogrenciler[i]);
				kromozomlar[k].ogrenciler[i] = tmp_ogrenci;//Öðrenci oluþturduk..	
				kromozomlar[k].ogrenciler[i].hoca_id= sayi_uret.nextInt(hocalar.length);
				
			}			
			
		}

		double k_puan2[] = Yardimcilar.puanHesaplama(kromozomlar, top_ogr_sayisi);		
		
		//-------------------------- ORTALAMA HESAPLAMA---------------------------------------
		for(int s = 0 ; s<10000 ; s++){
			double k_puan[] = Yardimcilar.puanHesaplama(kromozomlar, top_ogr_sayisi);
			for(int i = 0 ; i<10 ; i++){
				kromozomlar[i].puan = k_puan[i];
				
			}			
	
			Arrays.sort(kromozomlar, new Comparator<Kromozom>(){//Sýralama yaptýk
				
				public int compare(Kromozom k1, Kromozom k2){
					
					if(k1.puan > k2.puan)
						return +1;
					else if(k1.puan < k2.puan)
						return -1;
					else
						return 0;
				}
				
			});
	
			Yardimcilar.yeniNesil(kromozomlar, top_ogr_sayisi);
		}
		Arrays.sort(kromozomlar, new Comparator<Kromozom>(){//Sýralama yaptýk
			
			public int compare(Kromozom k1, Kromozom k2){
				
				if(k1.puan > k2.puan)
					return +1;
				else if(k1.puan < k2.puan)
					return -1;
				else
					return 0;
			}
			
		});
		for(int k = 0 ; k<10 ; k++){
			System.out.println("Kromozom " + k + " Puani : "+ kromozomlar[k].puan);
		}	
		
		try {
			Excel.excelYazdir(kromozomlar[0], hocalar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		new calisma2();

	}

}
