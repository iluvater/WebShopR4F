package r4f.model;

import java.sql.Date;

public class TestInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatenbankVerbindung db = new DatenbankVerbindung();
				
		Benutzer b = new Benutzer(1, "vorname", "nachname", "email3@test.com", new Date(1),  "password", "strasse", "hausnummer", "12345", "Stadt", "Herr");
		
		db.CreateBenutzerInDB(b);
		b = db.getBenutzer("email3@test.com");
		System.out.println(b.getId());
		
	}

}
