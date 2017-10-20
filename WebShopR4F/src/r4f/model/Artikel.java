package r4f.model;

import java.util.Date;

/**
 * Class that represents a articel
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
	
	/**
	 * Constructor that initilizes all attributes
	 * @param id id to set
	 * @param bezeichnung bezeichnung to set
	 * @param beschreibung beschreibung to set
	 * @param groesse groesse to set
	 * @param preis preis to set
	 * @param hersteller hersteller to set
	 * @param farbe farbe to set
	 * @param erfassungsdatum erfassungsdatum to set
	 * @param kategorie kategorie to set
	 */
	public Artikel(int id, String bezeichnung, String beschreibung, int groesse, double preis, String hersteller, String farbe, Date erfassungsdatum, String kategorie){
		this.id = id;
		this.bezeichnung=bezeichnung;
		this.beschreibung=beschreibung;
		this.groesse=groesse;
		this.preis=preis;
		this.hersteller=hersteller;
		this.farbe=farbe;
		this.erfassungsdatum=erfassungsdatum;
		this.kategorie=kategorie;
	}
	
	/**
	 * Constructor that can be used to store all attributes that are requiered for creating an article in the database
	 * @param bezeichnung bezeichnung to set
	 * @param beschreibung beschreibung to set
	 * @param groesse groesse to set
	 * @param preis preis to set
	 * @param hersteller hersteller to set
	 * @param farbe farbe to set
	 * @param kategorie kategorie to set
	 */
	public Artikel(String bezeichnung, String beschreibung, int groesse, double preis, String hersteller, String farbe, String kategorie){
		this.bezeichnung=bezeichnung;
		this.beschreibung=beschreibung;
		this.groesse=groesse;
		this.preis=preis;
		this.hersteller=hersteller;
		this.farbe=farbe;
		this.kategorie=kategorie;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param bezeichnung the bezeichnung to set
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
	 * @param beschreibung the beschreibung to set
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
	 * @param groesse the groesse to set
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
	 * @param preis the preis to set
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
	 * @param hersteller the hersteller to set
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
	 * @param farbe the farbe to set
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
	 * @param erfassungsdatum the erfassungsdatum to set
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
	 * @param kategorie the kategorie to set
	 */
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	
	
}
