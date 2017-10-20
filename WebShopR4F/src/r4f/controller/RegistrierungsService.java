package r4f.controller;

import r4f.model.Benutzer;
import r4f.model.DatenbankVerbindung;

public class RegistrierungsService {
	
	DatenbankVerbindung dbVerbindung;
	
	/**
	 * Constructor that only initilazed a new Datenbank verbidnung
	 */
	public RegistrierungsService(){
		dbVerbindung = new DatenbankVerbindung();
	}
	
	/**
	 * 
	 * @param email email that is checked whether a user exists or not
	 * @return return true when a user with this email exists
	 *         returns false when there is no user with this email
	 */
	public boolean checkEmailExists(String email){
		Benutzer benutzer = dbVerbindung.getBenutzer(email);
		if(benutzer == null){
			return false;
		}else{
			return true;
		}
	}
	
	public Benutzer createBenutzerInDB(Benutzer benutzer){
		dbVerbindung.CreateBenutzerInDB(benutzer);
		
		benutzer = dbVerbindung.getBenutzer(benutzer.getEmail());
		return benutzer;
	}
}
