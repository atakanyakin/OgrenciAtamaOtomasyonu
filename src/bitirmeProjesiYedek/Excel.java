package bitirmeProjesiYedek;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
static public String[] hocaDosyaOku() throws IOException{
		
		
		
		FileInputStream dosya = new FileInputStream("C:\\Users\\ataka\\workspace\\bitirmeProjesiYedek\\hocalar.xlsx");
		
		Workbook wb = new XSSFWorkbook(dosya);
		Sheet sheet = wb.getSheet("Hocalar");
		
		int satir_sayisi = sheet.getLastRowNum();
		String hocalar[] = new String[satir_sayisi+1];
		for (int i = 0; i <= satir_sayisi; i++) {
			hocalar[i] = (sheet.getRow(i).getCell(0)).toString();
		}
		
		return hocalar;
	}
	static public int toplamOgrenciSayisi() throws IOException{
		int toplam_ogr_sayisi = 0;
		
		FileInputStream dosya = new FileInputStream("C:\\Users\\ataka\\workspace\\bitirmeProjesiYedek\\hocalar.xlsx");
		
		Workbook wb = new XSSFWorkbook(dosya);
		Sheet sheet = wb.getSheet("Ogrenciler");
		
		 toplam_ogr_sayisi = sheet.getLastRowNum();
		
		return toplam_ogr_sayisi;
	}
	static public Ogrenci ogrenciGetir(int indis){
		
		FileInputStream dosya = null;
		try {
			dosya = new FileInputStream("C:\\Users\\ataka\\workspace\\bitirmeProjesiYedek\\hocalar.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(dosya);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheet("Ogrenciler");
		
		String ogrenci_no = sheet.getRow(indis+1).getCell(0).toString();
		String ogrenci_isim = sheet.getRow(indis+1).getCell(1).toString();
		double ogrenci_not = Double.parseDouble(sheet.getRow(indis+1).getCell(2).toString());
		
		
		
		return new Ogrenci(ogrenci_no,ogrenci_isim,ogrenci_not);
	}
	static public Ogrenci[] tumOgrenciGetir(int ogrenci_sayisi){
		
		Ogrenci ogrenciler[] = new Ogrenci[ogrenci_sayisi];
		for(int i = 0 ; i<ogrenci_sayisi ; i++){
			ogrenciler[i] = ogrenciGetir(i);
			ogrenciler[i].id = i;
		}
		return ogrenciler;
		
	}
	static public void excelYazdir(Kromozom k , String[] hocalar) throws IOException{
		
		Workbook wb = new XSSFWorkbook();//Workbook bir arayüz xssfworkbook bir sýnýf.
		
		Sheet sheet = wb.createSheet("Sonuc");// Sayfa oluþturma.
		Row row = sheet.createRow(0);
		int dizisi[] = new int[hocalar.length];
		for(int i = 0 ; i<hocalar.length ; i++){			
			dizisi[i] = 0;
		}
		
		for(int i = 0 ; i<k.ogrenciler.length ; i++){
			
			String isim = k.ogrenciler[i].isim;
			int hocasi = k.ogrenciler[i].hoca_id;//Hangi satir oldugu
			int satir = dizisi[hocasi]+1;
			int hucre = hocasi;			
			
			Row r =sheet.getRow(satir);
			if( r == null){
				r = sheet.createRow(satir);				
			}
			r.createCell(hucre).setCellValue(isim);
			dizisi[hocasi]++;
		}
		for(int i = 0 ; i<hocalar.length ; i++){			
			row.createCell(i).setCellValue(hocalar[i]+" ->" + dizisi[i]);			
		}

		
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\ataka\\workspace\\bitirmeProjesiYedek\\sonuc.xlsx");//
		
		
		wb.write(fileOut);//Dosyayý yazdýrma iþlemi..
		fileOut.close();//Dosyayý bitir.
		
	}

}
