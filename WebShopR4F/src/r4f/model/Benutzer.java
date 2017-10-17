/**
 *  Class that represents a user that is registred to the webshop.
 */
package r4f.model;

import java.util.Date;

/**
 * @author Ture
 *
 */
public class Benutzer {

	private int id;
	private String vorname;
	private String nachname;
	private String email;
	private Date geburtstdatum;
	private String password;
	private String strasse;
	private String hausnummer;
	private int postleitzahl;
	private String stadt;
	private String anrede;

	/**
	 * 
	 * @param id
	 *            the id to set
	 * @param vorname
	 *            the vorname to set
	 * @param nachname
	 *            the nachname to set
	 * @param email
	 *            the email to set
	 * @param password
	 *            the password to set
	 * @param strasse
	 *            the strasse to set
	 * @param hausnummer
	 *            the hausnummer to set
	 * @param postleitzahl
	 *            the postleitzahl to set
	 * @param stadt
	 *            the stadt to set
	 * @param anrede
	 *            the anrede to set
	 */
	public Benutzer(int id, String vorname, String nachname, String email, String password, String strasse,
			String hausnummer, int postleitzahl, String stadt, String anrede) {
		this.id=id;
		this.vorname=vorname;
		this.nachname=nachname;
		this.email=email;
		this.password=password;
		this.strasse=strasse;
		this.hausnummer=hausnummer;
		this.postleitzahl=postleitzahl;
		this.stadt=stadt;
		this.anrede=anrede;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * @param vorname
	 *            the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * @return the geburtstdatum
	 */
	public Date getGeburtstdatum() {
		return geburtstdatum;
	}

	/**
	 * @param geburtstdatum
	 *            the geburtstdatum to set
	 */
	public void setGeburtstdatum(Date geburtstdatum) {
		this.geburtstdatum = geburtstdatum;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * @param strasse
	 *            the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	/**
	 * @return the hausnummer
	 */
	public String getHausnummer() {
		return hausnummer;
	}

	/**
	 * @param hausnummer
	 *            the hausnummer to set
	 */
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	/**
	 * @return the postleitzahl
	 */
	public int getPostleitzahl() {
		return postleitzahl;
	}

	/**
	 * @param postleitzahl
	 *            the postleitzahl to set
	 */
	public void setPostleitzahl(int postleitzahl) {
		this.postleitzahl = postleitzahl;
	}

	/**
	 * @return the anrede
	 */
	public String getAnrede() {
		return anrede;
	}

	/**
	 * @param anrede
	 *            the anrede to set
	 */
	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	/**
	 * @return the stadt
	 */
	public String getStadt() {
		return stadt;
	}

	/**
	 * @param stadt
	 *            the stadt to set
	 */
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * @param nachname
	 *            the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

}
