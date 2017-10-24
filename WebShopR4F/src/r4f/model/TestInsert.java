package r4f.model;

public class TestInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatenbankVerbindung db = new DatenbankVerbindung();
				
		Artikel a = new Artikel("Bezeichung", "beschreibung", 45, 49.99, "Hummel", "farbe", "Herren", "Laufen");
		
		int id = db.createArtikelInDB(a);
		
		a = db.getArtikel(id);
		System.out.println(a.getErfassungsdatum().toString());
		
	}

}
