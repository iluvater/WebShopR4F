package r4f.model;

import java.util.Date;

/**
 * Class that represents a articel
 * 
 * @author Ture
 *
 */
public class Artikel {

	private int id;
	private String bezeichnung;
	private String beschreibung;
	private int groesse;
	private double preis;
	private String hersteller;
	private String farbe;
	private Date erfassungsdatum;
	private String kategorie;
	private String sportart;

	/**
	 * Constructor that initilizes all attributes
	 * 
	 * @param id
	 *            id to set
	 * @param bezeichnung
	 *            bezeichnung to set
	 * @param beschreibung
	 *            beschreibung to set
	 * @param groesse
	 *            groesse to set
	 * @param preis
	 *            preis to set
	 * @param hersteller
	 *            hersteller to set
	 * @param farbe
	 *            farbe to set
	 * @param erfassungsdatum
	 *            erfassungsdatum to set
	 * @param kategorie
	 *            kategorie to set
	 */
	public Artikel(int id, String bezeichnung, String beschreibung, int groesse, double preis, String hersteller,
			String farbe, Date erfassungsdatum, String kategorie, String sportart) {
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.groesse = groesse;
		this.preis = preis;
		this.hersteller = hersteller;
		this.farbe = farbe;
		this.erfassungsdatum = erfassungsdatum;
		this.kategorie = kategorie;
		this.sportart = sportart;
	}

	/**
	 * Constructor that can be used to store all attributes that are requiered
	 * for creating an article in the database
	 * 
	 * @param bezeichnung
	 *            bezeichnung to set
	 * @param beschreibung
	 *            beschreibung to set
	 * @param groesse
	 *            groesse to set
	 * @param preis
	 *            preis to set
	 * @param hersteller
	 *            hersteller to set
	 * @param farbe
	 *            farbe to set
	 * @param kategorie
	 *            kategorie to set
	 */
	public Artikel(String bezeichnung, String beschreibung, int groesse, double preis, String hersteller, String farbe,
			String kategorie, String sportart) {
		this.bezeichnung = bezeichnung;
		this.beschreibung = beschreibung;
		this.groesse = groesse;
		this.preis = preis;
		this.hersteller = hersteller;
		this.farbe = farbe;
		this.kategorie = kategorie;
		this.sportart = sportart;
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
	 * @return the bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * @param bezeichnung
	 *            the bezeichnung to set
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @param beschreibung
	 *            the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	/**
	 * @return the groesse
	 */
	public int getGroesse() {
		return groesse;
	}

	/**
	 * @param groesse
	 *            the groesse to set
	 */
	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	/**
	 * @return the preis
	 */
	public double getPreis() {
		return preis;
	}

	/**
	 * @param preis
	 *            the preis to set
	 */
	public void setPreis(double preis) {
		this.preis = preis;
	}

	/**
	 * @return the hersteller
	 */
	public String getHersteller() {
		return hersteller;
	}

	/**
	 * @param hersteller
	 *            the hersteller to set
	 */
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	/**
	 * @return the farbe
	 */
	public String getFarbe() {
		return farbe;
	}

	/**
	 * @param farbe
	 *            the farbe to set
	 */
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	/**
	 * @return the erfassungsdatum
	 */
	public Date getErfassungsdatum() {
		return erfassungsdatum;
	}

	/**
	 * @param erfassungsdatum
	 *            the erfassungsdatum to set
	 */
	public void setErfassungsdatum(Date erfassungsdatum) {
		this.erfassungsdatum = erfassungsdatum;
	}

	/**
	 * @return the kategorie
	 */
	public String getKategorie() {
		return kategorie;
	}

	/**
	 * @param kategorie
	 *            the kategorie to set
	 */
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	/**
	 * @return the sportart
	 */
	public String getSportart() {
		return sportart;
	}

	/**
	 * @param sportart
	 *            the sportart to set
	 */
	public void setSportart(String sportart) {
		this.sportart = sportart;
	}

	/**
	 * checks whether a string is a sportart or not
	 * 
	 * @param sportart
	 *            sportart to check
	 * @return returns true if the sportart to check is a sportart
	 */
	public static boolean checkSportart(String sportart) {
		if (sportart.equals("Laufen") || sportart.equals("Fussball") || sportart.equals("Basketball")
				|| sportart.equals("Golf") || sportart.equals("Schwimmen") || sportart.equals("Fahrrad")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks whether a string is a kategorie or not
	 * 
	 * @param kategorie
	 *            kategorie to check
	 * @return returns true if the kategorie to check is a kategorie
	 */
	public static boolean checkKategorie(String kategorie) {
		if (kategorie.equals("Herren") || kategorie.equals("Damen") || kategorie.equals("Kinder")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks whether a string is a hersteller or not
	 * @param hersteller hersteller to check
	 * @return returns true if the hersteller to check is a hersteller
	 */
	public static boolean checkHersteller(String hersteller) {
		if (hersteller.equals("Nike") || hersteller.equals("Asics") || hersteller.equals("Kempa")
				|| hersteller.equals("Hummel") || hersteller.equals("Adidas") || hersteller.equals("Puma")) {
			return true;
		} else {
			return false;
		}
	}

}
