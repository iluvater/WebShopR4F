package r4f.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import r4f.controller.services.EmailService;
import r4f.controller.services.HashService;

public class TestInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		DatabaseConnection db = new DatabaseConnection();
				
		List<Integer> size = new ArrayList<Integer>();
		List<String> color = new ArrayList<String>();
	Article a = new Article(12, "Bezeichung", "beschreibung", size, 49.99, "Hummel", color, new Date(), "Herren", "Laufen", 3, size);
	
	System.out.println(a.getEntryDate());
	System.out.println(a.getDeliveryDate());
		
//		int id = db.createArtikelInDB(a);
		
//		a = db.getArtikel(id);
//		System.out.println(a.getErfassungsdatum().toString());

//		DatabaseConnection db = new DatabaseConnection();
//				
//		User b = new User(1, "vorname", "nachname", "email3@test.com", new Date(1),  "password", "strasse", "hausnummer", "12345", "Stadt", "Herr");
//		
//		db.CreateBenutzerInDB(b);
//		b = db.getBenutzer("email3@test.com");
//		System.out.println(b.getId());
		
//		ErrorMessage error = new ErrorMessage(104);
//		System.out.println(error.getErrorMessage());
		
//		EmailService e = new EmailService();
//		e.sendContactMail("test", "hello");
//
//		HashService p = new HashService();
//		System.out.println(p.encrypt("1"));
//
//		System.out.println(p.encrypt("Test"));
//
//		System.out.println(p.encrypt("Tesfasdasdasdsadt"));
//
//		System.out.println(p.encrypt("Test"));
		
		
		
	}

}
