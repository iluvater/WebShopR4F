package r4f.model;

import java.util.Date;

import r4f.controller.services.EmailService;

public class TestInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		DatabaseConnection db = new DatabaseConnection();
				
//		Article a = new Article("Bezeichung", "beschreibung", 45, 49.99, "Hummel", "farbe", "Herren", "Laufen");
		
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
		
		EmailService e = new EmailService();
		e.sendContactMail("test", "hello");

		
	}

}
