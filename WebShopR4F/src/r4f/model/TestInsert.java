package r4f.model;

public class TestInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatenbankVerbindung db = new DatenbankVerbindung();
				
		Benutzer b = new Benutzer(1, "vorname", "nachname", "email123", "password", "strasse", "hausnummer", 12345, "Stadt", "Herr");
		
		b = db.CreateBenutzerInDB(b);
		
		System.out.println(b.getId());
		
	}

}
