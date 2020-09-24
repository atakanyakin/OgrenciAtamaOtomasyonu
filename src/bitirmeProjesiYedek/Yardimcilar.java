package bitirmeProjesiYedek;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;

public class Yardimcilar {
	public static Random sayi_uret = new Random();
	public static final int cros_sayisi = 4;
	static public  double[] genelOrtalamaHesapla(Kromozom kd[]){
		
		double k_genel_ortalama[]= new double[kd.length];
		for(int k = 0 ; k<kd.length ; k++){//Kromozomlarýn kendi Ortalama hesaplanacak..			
			k_genel_ortalama[k] = kd[k].ortalamaHesapla();
		}		
		return k_genel_ortalama;
	}
	
	static public double[][] hocalarOrtalamaHesapla(Kromozom kd[]){
		
		double hoca_ortalama[][] = new double[kd.length][kd[0].hoca_sayisi];
		
		for(int k = 0 ; k<kd.length ; k++){//Hocalara ait öðrencilerin ortalama hesaplanacak..		
			
			for(int i = 0 ; i<kd[0].hoca_sayisi ; i++){				
				hoca_ortalama[k][i] = kd[k].hocaOrtalamaHesapla(i);				
			}	
		}
		return hoca_ortalama;		
		
	}
	static public double[] puanHesaplama(Kromozom kd[],int ogrenci_sayisi){
		
		double k_genel_ortalama[]= genelOrtalamaHesapla(kd);		
		double hoca_ortalama[][] =hocalarOrtalamaHesapla(kd);
		double h_dusmesi_gereken_ogrenci_sayisi_ortalamasi= ogrenci_sayisi/kd[0].hoca_sayisi;	
		
		
		
		
		

		double k_puan[] = new double[kd.length];	
		
		for(int k = 0 ; k<kd.length ; k++){//Puan Hesaplama
			
			
			int h_ogrenci_sayisi [] = new int [kd[0].hoca_sayisi];
			for(int i = 0 ; i<h_ogrenci_sayisi.length ; i++){
					h_ogrenci_sayisi[i] = 0;		
			}
			
			for(int i = 0 ; i<ogrenci_sayisi ; i++){	
				h_ogrenci_sayisi[kd[k].ogrenciler[i].hoca_id]++;		
			}			
	
			k_puan[k] = 0;
			double puan = 0;
			for(int i = 0 ; i<h_ogrenci_sayisi.length ; i++){
				
				k_puan[k]+= Math.pow((k_genel_ortalama[k])-(hoca_ortalama[k][i]), 2);
			
				puan+=Math.pow((h_ogrenci_sayisi[i])-(h_dusmesi_gereken_ogrenci_sayisi_ortalamasi), 2);

			}
			
			k_puan[k] = Math.sqrt(k_puan[k]/(ogrenci_sayisi)+Math.pow(puan, 2));
			
		}
		
		return k_puan;
	}
	
	static public int[] sayilariKaristir(){
		
		int sayilar [] = new int[cros_sayisi];
		for(int i = 0 ; i<cros_sayisi ; i++){
			sayilar[i] = i;
		}
		
		for(int i = 0 ; i<30 ; i++){
			int sayi1 = sayi_uret.nextInt(cros_sayisi);
			int sayi2 = sayi_uret.nextInt(cros_sayisi);
			
			int tmp = sayilar[sayi1];
			sayilar[sayi1] = sayilar[sayi2];
			sayilar[sayi2] = tmp;			
		}
		return sayilar;
		
	}
	static public Kromozom[] crosla(Kromozom kd[]){
		int sayilar [] = Yardimcilar.sayilariKaristir();
		
		Kromozom cros1[] = Genetik.crossover(kd[sayilar[0]], kd[sayilar[1]]);
		Kromozom cros2[] = Genetik.crossover(kd[sayilar[2]], kd[sayilar[3]]);
		Kromozom croslar[] = {cros1[0],cros1[1],cros2[0],cros2[1]};
		
		return croslar;
	}
	static public void yeniNesil(Kromozom kd[],int ogrenci_sayisi){
		Kromozom croslar[] = Yardimcilar.crosla(kd);

		double k_yeni_puan[] = Yardimcilar.puanHesaplama(croslar, ogrenci_sayisi);
		for(int i = 0 ; i<cros_sayisi ; i++){
			croslar[i].puan = k_yeni_puan[i];
			
		}
		double en_iyi_puan = kd[0].puan;
		double en_kotu_puan = kd[9].puan;
		
		for(int i = 0 ; i<cros_sayisi ; i++){
			
			if(croslar[i].puan < en_iyi_puan){
				
				kd[9] = croslar[i];
				
				
			}else if(croslar[i].puan > en_kotu_puan){
				//Ýþlem yapýlmicak..
				
			}else{
				
				int sayi1 = sayi_uret.nextInt(10);
				
				if(!croslar[i].equals(kd[sayi1]))
					kd[sayi1] = croslar[i];
			}				
		}
		
	}
	


}
