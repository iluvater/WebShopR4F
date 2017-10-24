/**
 * 
 */
package r4f.controller;

import r4f.model.Artikel;
import r4f.model.DatenbankVerbindung;

/**
 * @author Ture
 *
 */
public class ArtikelService {
	
	DatenbankVerbindung dbVerbindung;
	
	/**
	 * Constructor that establishes a connection to the database
	 */
	public ArtikelService(){
		dbVerbindung = new DatenbankVerbindung(); 
	}
	
	/**
	 * This method creates a new Artikel in the database
	 * @param artikel article that should be created
	 * @return returns the created article returns null if the article could be created
	 */
	public Artikel createArtikelInDB(Artikel artikel){
		int id;
		
		id = dbVerbindung.createArtikelInDB(artikel);
		
		return dbVerbindung.getArtikel(id);
	}
}
