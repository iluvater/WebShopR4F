/**
 * Class for making a connection to the database
 */
package r4f.model;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Driver;

/**
 * @author Ture
 *
 */
public class DatabaseConnection {
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

	public DatabaseConnection() {
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
			new DatabaseConnection();
		return conn;
	}

	/**
	 * This method creates a new User in the database
	 * 
	 * @param user
	 *            User that should be created in DB
	 * @return returns the id of the created user returns -1 if there was no
	 *         user created
	 */
	public int createUserInDB(User user) {

		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `user` (`id`, `email`, `firstName`, `lastName`, `birthday`, `password`, `street`, "
								+ "`houseNumber`, `postCode`, `city`, `salutation`, `shoppingBasket`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, user.getEmail());
				preparedStatement.setString(2, user.getFirstName());
				preparedStatement.setString(3, user.getLastName());
				preparedStatement.setDate(4, new Date(user.getBirthday().getTime()));
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.setString(6, user.getStreet());
				preparedStatement.setString(7, user.getHouseNumber());
				preparedStatement.setString(8, user.getPostCode());
				preparedStatement.setString(9, user.getCity());
				preparedStatement.setString(10, user.getSalutation());

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
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
	 * This method selects an user from the database selected by the email
	 * 
	 * @param email
	 *            Email of the user that should be get from the database.
	 * @return the user with all Attributes that are stored in the database
	 */
	public User getUser(String email) {
		User user = null;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT u.*, r.name  FROM user AS u INNER JOIN role AS r ON r.id = u.role WHERE email='"
						+ email + "'";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					Date birthday = result.getDate("birthday");
					String password = result.getString("password");
					String street = result.getString("street");
					String houseNumber = result.getString("houseNumber");
					String postCode = result.getString("postCode");
					String city = result.getString("city");
					String salutation = result.getString("salutation");
					int shoppingBasket = result.getInt("shoppingBasket");
					String role = result.getString("name");
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	/**
	 * This method selects an user from the database selected by the email
	 * 
	 * @param id
	 *            Id of the user that should be get from the database.
	 * @return the user with all Attributes that are stored in the database
	 */
	public User getUser(int id) {
		User user = null;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT u.*, r.name  FROM user AS u INNER JOIN role AS r ON r.id = u.role WHERE u.id='"
						+ id + "'";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					id = result.getInt("id");
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String email = result.getString("email");
					Date birthday = result.getDate("birthday");
					String password = result.getString("password");
					String street = result.getString("street");
					String houseNumber = result.getString("houseNumber");
					String postCode = result.getString("postCode");
					String city = result.getString("city");
					String salutation = result.getString("salutation");
					int shoppingBasket = result.getInt("shoppingBasket");
					String role = result.getString("name");
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	/**
	 * This method updates a User in the database the new values should be in
	 * the parameter user
	 * 
	 * @param user
	 *            user that should be updated
	 */
	public void updateUserInDB(User user) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("UPDATE `user` SET `email` = ?, `firstName` = ?, `lastName` = ?, "
								+ "`birthday` = ?, `password` = ?, `street` = ?, `houseNumber` = ?, "
								+ "`postCode` = ?, `city` = ?, `salutation` = ?, `shoppingBasket` = ?"
								+ "`role`= ? WHERE `user`.`id` = ?", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, user.getEmail());
				preparedStatement.setString(2, user.getFirstName());
				preparedStatement.setString(3, user.getLastName());
				preparedStatement.setDate(4, new Date(user.getBirthday().getTime()));
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.setString(6, user.getStreet());
				preparedStatement.setString(7, user.getHouseNumber());
				preparedStatement.setString(8, user.getPostCode());
				preparedStatement.setString(9, user.getCity());
				preparedStatement.setString(10, user.getSalutation());
				preparedStatement.setInt(11, user.getShoppingBasket());
				preparedStatement.setInt(12, getRoleId(user.getRole()));
				preparedStatement.setInt(13, user.getId());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method creates a new article in the database
	 * 
	 * @param artikel
	 *            the article that should be created
	 * @return returns the id of the created article if the article was not
	 *         created the method return -1
	 */
	public int createArticleInDB(Article artikel) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement(
								"INSERT INTO `Article` (`id`, `name`, `description`, `size`, `price`, `manufacturer`, `color`, `entryDate`, `category`, `sport`) "
										+ "VALUES (NULL, ?, ?, ?, ?, ?, ?, null, ?, ?)",
								Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, artikel.getName());
				preparedStatement.setString(2, artikel.getDescription());
				preparedStatement.setInt(3, artikel.getSize());
				preparedStatement.setDouble(4, artikel.getPrice());
				preparedStatement.setInt(5, getManufacturerId(artikel.getManufacturer()));
				preparedStatement.setString(6, artikel.getColor());
				preparedStatement.setInt(7, getCategoryId(artikel.getCategory()));
				preparedStatement.setInt(8, getSportId(artikel.getSport()));

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
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
	 * 
	 * @param id
	 *            the id of the article that should be selected
	 * @return returns the selected article if there is no article with this id
	 *         in the database
	 */
	public Article getArticle(int id) {
		Article article = null;

		conn = getInstance();
		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT a.id, a.name, a.description, a.size, a.price, m.name as manufacturer, a.color, a.entryDate, a.image, c.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS c INNER JOIN manufacturer AS m INNER JOIN sport AS s"
						+ " WHERE a.category = c.id AND a.manufacturer = m.id AND a.sport = s.id AND a.id = " + id;
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");
					int size = result.getInt("size");
					double price = result.getDouble("price");
					String manufacturer = result.getString("manufacturer");
					String color = result.getString("color");
					Date entryDate = result.getDate("entryDate");
					String category = result.getString("category");
					String sport = result.getString("sport");
					int image = result.getInt("image");

					article = new Article(id, name, description, size, price, manufacturer, color, entryDate, category,
							sport, image);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return article;
	}

	/**
	 * This method selects all article in the Database
	 * 
	 * @return returns all ariticle in the database
	 */
	public List<Article> getArticleList() {
		List<Article> articleList = new ArrayList<Article>();

		conn = getInstance();
		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT a.id, a.name, a.description, a.size, a.price, m.name as manufacturer, a.color, a.entryDate, a.image, c.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS c INNER JOIN manufacturer AS m INNER JOIN sport AS s"
						+ " WHERE a.category = c.id AND a.manufacturer = m.id AND a.sport = s.id";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					Article article;
					int id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");
					int size = result.getInt("size");
					double price = result.getDouble("price");
					String manufacturer = result.getString("manufacturer");
					String color = result.getString("color");
					Date entryDate = result.getDate("entryDate");
					String category = result.getString("category");
					String sport = result.getString("sport");
					int image = result.getInt("image");

					article = new Article(id, name, description, size, price, manufacturer, color, entryDate, category,
							sport, image);
					articleList.add(article);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return articleList;
	}

	/**
	 * This method updates an article in the database
	 * @param article the article that should be updated it should contain all new values
	 */
	public void updateArticleInDB(Article article) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("UPDATE `article` SET `name` = ?, `description` = ?, `size` = ?, "
								+ "`price` = ?, `manufacturer` = ?, `color` = ?, `category` = ?, "
								+ "`sport` = ?, `image` = ?"
								+ " WHERE `article`.`id` = ?", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, article.getName());
				preparedStatement.setString(2, article.getDescription());
				preparedStatement.setInt(3, article.getSize());
				preparedStatement.setDouble(4, article.getPrice());
				preparedStatement.setInt(5, getManufacturerId(article.getManufacturer()));
				preparedStatement.setString(6, article.getColor());
				preparedStatement.setInt(7, getCategoryId(article.getCategory()));
				preparedStatement.setInt(8, getSportId(article.getSport()));
				preparedStatement.setInt(9, article.getImage());
				
				preparedStatement.setInt(10, article.getId());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method selects the id of a role from the database
	 * 
	 * @param role
	 *            name of the role for which the id should be selected
	 * @return the id of the role, returns -1 if there is no role with this name
	 */
	public int getRoleId(String role) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM role WHERE name='" + role + "'";
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
	 * This method gets the id to the string of a category
	 * 
	 * @param category
	 *            category for which the id should be selected from the database
	 * @return the id of the category
	 */
	public int getCategoryId(String category) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM category WHERE name='" + category + "'";
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
	 * This method selects the id of a manufacturer
	 * 
	 * @param manufacturer
	 *            the manufacturer for which the id should be selected
	 * @return the id of the manufacturer
	 */
	public int getManufacturerId(String manufacturer) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM manufacturer WHERE name='" + manufacturer + "'";
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
	 * This method selects the id of a sport
	 * 
	 * @param sport
	 *            the sport which id should be selected
	 * @return returns the id of the sport
	 */
	public int getSportId(String sport) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM sport WHERE name='" + sport + "'";
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
	 * This method creates a shopping Basket in the database
	 * 
	 * @param userId
	 *            id of the user for who the shoppingBasket should be created
	 * @return returns the id of the shopping basket
	 */
	public int createShoppingBasketInDB(int userId) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `shoppingbasket` (`id`, `user`) " + "VALUES (NULL, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, userId);

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
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
	 * 
	 * @param id
	 *            id of the shopping basket that should be selected from the
	 *            database
	 * @return returns the shopping basket of the user or null if there is no
	 *         shopping basket for the userId
	 */
	public ShoppingBasket getShoppingBasket(int id) {
		ShoppingBasket shoppingBasket = new ShoppingBasket(id);

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM shoppingbasketitem WHERE shoppingBasket='" + id + "'";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				while (result.next()) {
					ShoppingBasketItem item = new ShoppingBasketItem();
					item.setAmount(result.getInt("amount"));
					item.setId(result.getInt("id"));

					int aritcleId = result.getInt("article");
					item.setArticle(this.getArticle(aritcleId));

					shoppingBasket.getItems().add(item);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return shoppingBasket;
	}

	/**
	 * This method deletes all items of a shopping basket in the database
	 * 
	 * @param id
	 *            id of the shopping basket for which all items should be
	 *            deleted
	 */
	public void deleteAllItemsOfShoppingBasketInDB(int id) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"DELETE FROM `shoppingbasketitem` WHERE `shoppingBasket` = ?", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, id);

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method creates a new entry in the database of an shopping basket
	 * item
	 * 
	 * @param shoppingBasketItem
	 *            that should be created
	 * @param shoppingBasketId
	 *            the id of the shopping Basket the item belongs to
	 * @return returns the id of the created item
	 * 
	 */
	public int createShoppingBasketItem(ShoppingBasketItem shoppingBasketItem, int shoppingBasketId) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `shoppingbasketitem` (`id`, `article`, `shoppingBasket`, àmount`) "
								+ "VALUES (NULL, ?, ? , ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, shoppingBasketItem.getArticle().getId());
				preparedStatement.setInt(2, shoppingBasketId);
				preparedStatement.setInt(3, shoppingBasketItem.getAmount());

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
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
	 * This method checks whether an authorization is included in a role or not
	 * 
	 * @param roleId
	 *            role in which the authorization should be included
	 * @param authorization
	 *            authorization to check
	 * @return returns true if the authorization is in the role include and
	 *         return false if not
	 */
	public boolean checkAuthorizationInDB(int roleId, String authorization) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement("SELECT m.role, a.authorization, a.name "
						+ "FROM mappingroleauthorization AS m INNER JOIN authorization AS a "
						+ "ON m.authorization = a.id" + " WHERE a.name = ? AND m.role = ?");

				preparedStatement.setString(1, authorization);
				preparedStatement.setInt(2, roleId);

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * This method creates an image in the database
	 * 
	 * @param inputStream
	 *            the inputstream containing the image
	 * @param contentType
	 *            the contenttype of the image e.g. image/jpeg
	 * @return returns true if the image was created in the database and false
	 *         if not
	 */
	public int createImageInDB(InputStream inputStream, String contentType) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO image (image, type) values (?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(2, contentType);

				if (inputStream != null) {
					// fetches input stream of the upload file for the blob
					// column
					preparedStatement.setBlob(1, inputStream);
				}

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
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
	 * This method selects an image from the database
	 * 
	 * @param id
	 *            id of the image that should be selected
	 * @return returns the image returns null if no image was selected
	 */
	public Image getImage(int id) {
		Image image = null;

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			Statement query;
			Blob blob = null;
			try {
				query = conn.createStatement();

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM image WHERE id='" + id + "'";
				ResultSet result = query.executeQuery(sql);

				// Ergebnissätze durchfahren.
				if (result.next()) {
					blob = result.getBlob("image");
					image = new Image(id, blob, result.getString("type"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return image;
	}

}
