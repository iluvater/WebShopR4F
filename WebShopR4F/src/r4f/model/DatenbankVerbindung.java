/**
 * Class for making a connection to the database
 */
package r4f.model;

import java.sql.Connection;
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
	 * 
	 * @param benutzer
	 *            Benutzer that should be created in DB
	 * @return Benutzer that is a parameter but the ID is filled. If benutzer is null
	 *         Benutzer was not created in DB
	 */
	public Benutzer CreateBenutzerInDB(Benutzer benutzer) {

		conn = getInstance();

		if (conn!=null) {
			
			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `Benutzer` (`id`, `email`, `vorname`, `nachname`, `geburtstdatum`, `password`, `strasse`, "
								+ "`hausnummer`, `postleitzahl`, `stadt`, `anrede`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setString(1, benutzer.getEmail());
				preparedStatement.setString(2, benutzer.getVorname());
				preparedStatement.setString(3, benutzer.getNachname());
				preparedStatement.setString(4, "2017-07-01");
				preparedStatement.setString(5, benutzer.getPassword());
				preparedStatement.setString(6, benutzer.getStrasse());
				preparedStatement.setString(7, benutzer.getHausnummer());
				preparedStatement.setString(8, benutzer.getPostleitzahl());
				preparedStatement.setString(9, benutzer.getStadt());
				preparedStatement.setString(10, benutzer.getAnrede());

				preparedStatement.executeUpdate();

				benutzer = getBenutzer(benutzer.getEmail());

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return benutzer;
	}
	
	/**
	 * 
	 * @param email Email of the user that should be get from the database.
	 * @return the user with all Attributes that are stored in the database
	 */
	public Benutzer getBenutzer(String email) {
		Benutzer benutzer = null;
		  conn = getInstance();
		  
		  if(conn != null)
		    {
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
		          String password = result.getString("password");
		          String strasse = result.getString("strasse");
		          String hausnummer = result.getString("hausnummer");
		          String postleitzahl = result.getString("postleitzahl");
		          String stadt = result.getString("stadt");
		          String anrede = result.getString("anrede");
		          benutzer = new Benutzer(id, vorname, nachname, email, password, strasse, hausnummer, postleitzahl, stadt, anrede);
		        }
		      } catch (SQLException e) {
		        e.printStackTrace();
		      }
		    }
		  
		  return benutzer;
	}

}
