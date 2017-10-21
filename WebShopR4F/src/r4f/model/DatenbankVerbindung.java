/**
 * Class for making a connection to the database
 */
package r4f.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/**
 * @author Ture
 *
 */
public class DatenbankVerbindung {
	private static Connection conn = null;

	int i;

	// Hostname
	private static String dbHost = "localhost";

	// Port -- Standard: 3306
	private static String dbPort = "3306";

	// Datenbankname
	private static String database = "webshop_db";

	// Datenbankuser
	private static String dbUser = "techuser";

	// Datenbankpasswort
	private static String dbPassword = "Winter2017!";

	public DatenbankVerbindung() {
		try {

			// Datenbanktreiber für ODBC Schnittstellen laden.
			// Für verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur ODBC-Datenbank 'sakila' herstellen.
			// Es wird die JDBC-ODBC-Brücke verwendet.
			conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + ":" + dbPort + "/" + database + "?" + "user="
					+ dbUser + "&password=" + dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Treiber nicht gefunden");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connect nicht moeglich");
			e.printStackTrace();
		}
	}

	private static Connection getInstance() {
		if (conn == null)
			new DatenbankVerbindung();
		return conn;
	}

	/**
	 * This method creates a new Benutzer in the database
	 * 
	 * @param benutzer
	 *            Benutzer that should be created in DB
	 * @return Benutzer that is a parameter but the ID is filled. If benutzer is
	 *         null Benutzer was not created in DB
	 */
	public void CreateBenutzerInDB(Benutzer benutzer) {

		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `Benutzer` (`id`, `email`, `vorname`, `nachname`, `geburtstdatum`, `password`, `strasse`, "
								+ "`hausnummer`, `postleitzahl`, `stadt`, `anrede`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setString(1, benutzer.getEmail());
				preparedStatement.setString(2, benutzer.getVorname());
				preparedStatement.setString(3, benutzer.getNachname());
				preparedStatement.setDate(4, new Date(benutzer.getGeburtstdatum().getTime()));
				preparedStatement.setString(5, benutzer.getPassword());
				preparedStatement.setString(6, benutzer.getStrasse());
				preparedStatement.setString(7, benutzer.getHausnummer());
				preparedStatement.setString(8, benutzer.getPostleitzahl());
				preparedStatement.setString(9, benutzer.getStadt());
				preparedStatement.setString(10, benutzer.getAnrede());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method select a Benutzer from the database selected by the email
	 * 
	 * @param email
	 *            Email of the user that should be get from the database.
	 * @return the user with all Attributes that are stored in the database
	 */
	public Benutzer getBenutzer(String email) {
		Benutzer benutzer = null;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM benutzer WHERE email='" + email + "'";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					String vorname = result.getString("vorname");
					String nachname = result.getString("nachname");
					Date geburtstdatum = result.getDate("geburtstdatum");
					String password = result.getString("password");
					String strasse = result.getString("strasse");
					String hausnummer = result.getString("hausnummer");
					String postleitzahl = result.getString("postleitzahl");
					String stadt = result.getString("stadt");
					String anrede = result.getString("anrede");
					benutzer = new Benutzer(id, vorname, nachname, email, geburtstdatum, password, strasse, hausnummer,
							postleitzahl, stadt, anrede);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return benutzer;
	}

	/**
	 * This method creates a new article in the database
	 * 
	 * @param artikel
	 *            the article that should be created
	 * @return return the id of the created article if the articel was not
	 *         created the method return -1
	 */
	public int createArtikelInDB(Artikel artikel) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `Artikel` (`id`, `bezeichnung`, `beschreibung`, `groesse`, `preis`, `hersteller`, `farbe`, `erfassungsdatum`, `kategorie`) "
								+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, null, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, artikel.getBezeichnung());
				preparedStatement.setString(2, artikel.getBeschreibung());
				preparedStatement.setInt(3, artikel.getGroesse());
				preparedStatement.setDouble(4, artikel.getPreis());
				preparedStatement.setInt(5, getHerstellerId(artikel.getHersteller()));
				preparedStatement.setString(6, artikel.getFarbe());
				preparedStatement.setInt(7, getKategorieId(artikel.getKategorie()));

				int zeilen = preparedStatement.executeUpdate();

				if (zeilen != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						return result.getInt(1);
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		} else {
			return -1;
		}
	}
	
	
	/**
	 * This method selects an article from the database 
	 * @param id the id of the article that should be selected
	 * @return returns the selected article if there is no article with this id in the database
	 */
	public Artikel getArtikel(int id) {
		Artikel artikel = null;

		conn = getInstance();
		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT a.id, a.bezeichnung, a.beschreibung, a.groesse, a.preis, h.bezeichnung as hersteller, a.farbe, a.erfassungsdatum, k.bezeichnung as kategorie"
						+ " FROM artikel AS a INNER JOIN kategorien AS k INNER JOIN hersteller AS h"
						+ " WHERE a.kategorie = k.id AND a.hersteller = h.id AND a.id = " + id;
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					id = result.getInt("id");
					String bezeichnung = result.getString("bezeichnung");
					String beschreibung = result.getString("beschreibung");
					int groesse = result.getInt("groesse");
					double preis = result.getDouble("preis");
					String hersteller = result.getString("hersteller");
					String farbe = result.getString("farbe");
					Date erfassungsdatum = result.getDate("erfassungsdatum");
					String kategorie = result.getString("kategorie");
					
					artikel = new Artikel(id, bezeichnung, beschreibung, groesse, preis, hersteller, farbe, erfassungsdatum, kategorie);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return artikel;
	}

	/**
	 * This method gets the id to the string of a kategorie
	 * 
	 * @param kategorie
	 *            kategorie for which the id should be selected from the
	 *            database
	 * @return the id of the kategorie
	 */
	public int getKategorieId(String kategorie) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM kategorien WHERE bezeichnung='" + kategorie + "'";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	/**
	 * This method selects the id of a Hersteller
	 * 
	 * @param hersteller
	 *            the hersteller for whicht the id should be selected
	 * @return the id of the hersteller
	 */
	public int getHerstellerId(String hersteller) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM hersteller WHERE bezeichnung='" + hersteller + "'";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

}
