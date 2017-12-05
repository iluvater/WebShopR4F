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

import r4f.controller.filter.FilterList;

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

			// Datenbanktreiber f�r ODBC Schnittstellen laden.
			// F�r verschiedene ODBC-Datenbanken muss dieser Treiber
			// nur einmal geladen werden.
			Class.forName("com.mysql.jdbc.Driver");

			// Verbindung zur ODBC-Datenbank 'sakila' herstellen.
			// Es wird die JDBC-ODBC-Br�cke verwendet.
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
	 * @param addressId
	 *            Id of the address of the user
	 * @return returns the id of the created user returns -1 if there was no
	 *         user created
	 */
	public int createUserInDB(User user) {

		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `user` (`id`, `email`, `firstName`, `lastName`, `birthday`, `password`, `salutation`, `shoppingBasket`, `wishlist`)"
								+ " VALUES (NULL, ?, ?, ?, ?, ?, ?, null, null)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, user.getEmail());
				preparedStatement.setString(2, user.getFirstName());
				preparedStatement.setString(3, user.getLastName());
				preparedStatement.setDate(4, new Date(user.getBirthday().getTime()));
				preparedStatement.setString(5, user.getPassword());
				preparedStatement.setString(6, user.getSalutation());

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
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT u.*, r.name , a.street, a.houseNumber, a.postCode, a.city "
						+ "FROM user AS u INNER JOIN role AS r ON r.id = u.role INNER JOIN address AS a ON a.user = u.id "
						+ "WHERE a.masterData='1' AND email=?";
				
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, email);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
					int wishlist = result.getInt("wishlist");
					String role = result.getString("name");
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role, wishlist);
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
			PreparedStatement preparedStatement;
			try {


				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT u.*, r.name , a.street, a.houseNumber, a.postCode, a.city "
						+ "FROM user AS u INNER JOIN role AS r ON r.id = u.role INNER JOIN address AS a ON a.user = u.id "
						+ "WHERE a.masterData='1' AND id=?";
				
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
					int wishlist = result.getInt("wishlist");
					String role = result.getString("name");
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role, wishlist);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	/**
	 * This method updates a User in the database the new values should be in
	 * the parameter user This method does not updates the address of the user.
	 * you can use updateAddressInDb instead
	 * 
	 * @param user
	 *            user that should be updated
	 */
	public void updateUserInDB(User user) throws SQLException {
		conn = getInstance();

		if (conn != null) {

			PreparedStatement preparedStatement = conn
					.prepareStatement("UPDATE `user` SET `email` = ?, `firstName` = ?, `lastName` = ?, "
							+ "`birthday` = ?, `password` = ?, `salutation` = ?, `shoppingBasket` = ?, "
							+ "`role`= ?  `wishlist` = ? WHERE `user`.`id` = ?");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setDate(4, new Date(user.getBirthday().getTime()));
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getSalutation());
			preparedStatement.setInt(7, user.getShoppingBasket());
			preparedStatement.setInt(8, getRoleId(user.getRole()));
			preparedStatement.setInt(9, user.getWishlist());
			preparedStatement.setInt(10, user.getId());

			preparedStatement.executeUpdate();
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
				preparedStatement.setInt(6, getColorId(artikel.getColor()));
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
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT a.id, a.name, a.description, a.size, a.price, m.name as manufacturer, co.name as color, a.entryDate, a.image, ca.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS ca INNER JOIN manufacturer AS m INNER JOIN sport AS s INNER JOIN color as co"
						+ " WHERE a.category = ca.id AND a.manufacturer = m.id AND a.sport = s.id AND a.color = co.id AND a.id = ?";
				
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
	public List<Article> getArticleList(FilterList filter) {
		List<Article> articleList = new ArrayList<Article>();

		conn = getInstance();
		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT a.id, a.name, a.description, a.size, a.price, m.name as manufacturer, co.name as color, a.entryDate, a.image, ca.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS ca ON a.category = ca.id"
						+ " INNER JOIN manufacturer AS m ON a.manufacturer = m.id"
						+ " INNER JOIN sport AS s ON a.sport = s.id"
						+ " INNER JOIN color AS co ON a.color = co.id" 
						+ filter.getSQLFilter("a", "ca", "m", "s", "co");
				System.out.println(sql);
				
				preparedStatement = conn.prepareStatement(sql);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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

	public List<Article> getArticleListSearch(String searchPattern) {
		List<Article> articleList = new ArrayList<Article>();

		conn = getInstance();
		if (conn != null) {
			try {
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT a.id, a.name, a.description, a.size, a.price, m.name as manufacturer, co.name as color, a.entryDate, a.image, ca.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS ca ON a.category = ca.id"
						+ " INNER JOIN manufacturer AS m ON a.manufacturer = m.id"
						+ " INNER JOIN sport AS s ON a.sport = s.id"
						+ " INNER JOIN color AS co ON a.color = co.id" 
						+ " WHERE LOWER(a.id) LIKE LOWER('%" + searchPattern + "%') OR LOWER(a.name) LIKE LOWER('%" + searchPattern + "%') Or LOWER(a.description) LIKE LOWER('%" + searchPattern + "%')";
				System.out.println(sql);
				PreparedStatement statement = conn.prepareStatement(sql);
				
				ResultSet result = statement.executeQuery();

				// Ergebniss�tze durchfahren.
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
	 * 
	 * @param article
	 *            the article that should be updated it should contain all new
	 *            values
	 */
	public void updateArticleInDB(Article article) throws SQLException {
		conn = getInstance();

		if (conn != null) {

			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE `article` SET `name` = ?, `description` = ?, `size` = ?, "
							+ "`price` = ?, `manufacturer` = ?, `color` = ?, `category` = ?, "
							+ "`sport` = ?, `image` = ?" + " WHERE `article`.`id` = ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, article.getName());
			preparedStatement.setString(2, article.getDescription());
			preparedStatement.setInt(3, article.getSize());
			preparedStatement.setDouble(4, article.getPrice());
			preparedStatement.setInt(5, getManufacturerId(article.getManufacturer()));
			preparedStatement.setInt(6, getColorId(article.getColor()));
			preparedStatement.setInt(7, getCategoryId(article.getCategory()));
			preparedStatement.setInt(8, getSportId(article.getSport()));
			preparedStatement.setInt(9, article.getImage());

			preparedStatement.setInt(10, article.getId());

			preparedStatement.executeUpdate();

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
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM role WHERE name=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, role);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM category WHERE name=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, category);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM manufacturer WHERE name=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, manufacturer);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM sport WHERE name=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, sport);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
	 * This method selects the id of a color
	 * @param color the color which�s id should be selected
	 * @return returns the id of the color
	 */
	public int getColorId(String color){
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM color WHERE name=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, color);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
			PreparedStatement preparedStatement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM shoppingbasketitem WHERE shoppingBasket=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
						"INSERT INTO `shoppingbasketitem` (`id`, `article`, `shoppingBasket`, `amount`) "
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
			PreparedStatement preparedStatement;
			Blob blob = null;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM image WHERE id=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
	
	/**
	 * This method updates an image in the database
	 * @param imageId the id of the image that should be updated
	 * @param imageStream the new content
	 * @param imageType the new content type of the image
	 * @throws SQLException an SQLException will be thrown if any error occurred
	 */
	public void updateImageInDB(int imageId, InputStream imageStream, String imageType) throws SQLException {
		conn = getInstance();

		if (conn != null) {

			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE `image` SET `image` = ?, `type` = ? "
							+ " WHERE `image`.`id` = ? ",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setBlob(1, imageStream);
			preparedStatement.setString(2, imageType);
			preparedStatement.setInt(3, imageId);
			


			preparedStatement.executeUpdate();

		}	
	}
	
	/**
	 * This method create a new address in the database
	 * 
	 * @param street
	 *            street of the new address
	 * @param houseNumber
	 *            house number of the new address
	 * @param postCode
	 *            post code of the new address
	 * @param city
	 *            city of the new address
	 * @return return the id of the created entry in the data base. returns -1
	 *         if no entry was created
	 */
	public int createAddressInDB(int userId, String street, String houseNumber, String postCode, String city,
			boolean masterData) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO address (street, houseNumber, postCode, city, masterData, user) values (?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, street);
				preparedStatement.setString(2, houseNumber);
				preparedStatement.setString(3, postCode);
				preparedStatement.setString(4, city);
				preparedStatement.setBoolean(5, masterData);
				preparedStatement.setInt(6, userId);

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
	 * This method updates the master data address of an user
	 * @param userId the id of user who's address should be updated
	 * @param street the new street
	 * @param houseNumber the new houseNumber
	 * @param postCode the new postCode
	 * @param city the new City
	 * @throws SQLException
	 */
	public void updateAddressInDB(int userId, String street, String houseNumber, String postCode, String city) throws SQLException {
		conn = getInstance();

		if (conn != null) {

			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE `address` SET `street` = ?, `houseNumber` = ?, `postCode` = ?, "
							+ "`city` = ? WHERE `address`.`user` = ? AND `masterData`= ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, street);
			preparedStatement.setString(2, houseNumber);
			preparedStatement.setString(3, postCode);
			preparedStatement.setString(4, city);
			preparedStatement.setInt(5, userId);
			preparedStatement.setBoolean(6,true);


			preparedStatement.executeUpdate();

		}	
	}
	
	
	public Address getAddress(int userId, boolean masterData) {
		Address address = null;

		conn = getInstance();

		if (conn != null) {
			try {
				String sql = "SELECT * FROM address WHERE user=? AND masterData  =?";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				
				preparedStatement.setInt(1, userId);
				preparedStatement.setBoolean(2, masterData);
				
				ResultSet result = preparedStatement.executeQuery(sql);

				// Ergebniss�tze durchfahren.
				if (result.next()) {
					String street = result.getString("street");
					String houseNumber = result.getString("houseNumber");
					String postCode = result.getString("postCode");
					String city = result.getString("city");
					int id = result.getInt("id");
					
					address = new Address(id, street, houseNumber, postCode, city);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	
	/**
	 * This method creates an new order in the database
	 * @param order the order that should be created
	 * @return the id of the created order if an error occurred during the creation it will return -1
	 */
	public int createOrderInDB(Order order) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO orders (id, entryDate, user, deliveryAddress, billingAddress, paymentMethod) values (null, null, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, order.getUser().getId());
				preparedStatement.setInt(2, order.getDeliveryAddress().getId());
				preparedStatement.setInt(3, order.getBillingAddress().getId());
				preparedStatement.setInt(4, getPaymentMethodId(order.getPaymentMethod()));

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
	 * This method selects the id of an paymentMethod
	 * @param paymentMethod the payment methods who�s id should be selected
	 * @return the id of the payment method if no id was found it will return -1
	 */
	private int getPaymentMethodId(String paymentMethod) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM paymentMethod WHERE name=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, paymentMethod);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebniss�tze durchfahren.
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
	 * This methdo creates a new OrderItem for an order in the database
	 * @param orderId the id of the order
	 * @param item the item that should be created
	 */
	public void createOrderItemInDB(int orderId, OrderItem item) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO orderitem (order, position, amount, price, article) values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, orderId);
				preparedStatement.setInt(2, item.getPosition());
				preparedStatement.setInt(3, item.getAmount());
				preparedStatement.setDouble(4, item.getArticle().getPrice());
				preparedStatement.setInt(5, item.getArticle().getId());

				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		} 
	}
	
	/**
	 * This method selects all order for a certain user
	 * @param userId the id of the user
	 * @return returns a list of all orders from the user
	 */
	public List<Order> getOrderList(int userId) {
		List<Order> orderList = new ArrayList<Order>();

		conn = getInstance();
		if (conn != null) {
			try {
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT o.*, p.name AS paymentMethod"
						+ " FROM orders AS o INNER JOIN paymentmethod AS p ON o.paymentMethod = p.id"
						+ " WHERE o.user = ?"
						+ " ORDER BY entryDate";
				System.out.println(sql);
				PreparedStatement statement = conn.prepareStatement(sql);
				
				statement.setInt(1, userId);
				
				ResultSet result = statement.executeQuery(sql);

				// Ergebniss�tze durchfahren.
				while (result.next()) {
					Order order;
					int id = result.getInt("id");
					Date entryDate = result.getDate("entryDate");
					Address deliveryAddress = getAddress(result.getInt("deliveryAddress"));
					Address billingAddress = getAddress(result.getInt("billingAddress"));
					String paymentMethod = result.getString("paymentMethod");
					List<OrderItem> orderItems = getOrderItems(id);

					order = new Order(id, entryDate, deliveryAddress, billingAddress, paymentMethod, orderItems);
					orderList.add(order);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderList;
	}
	
	/**
	 * This method selects a list of items for a certain order
	 * @param orderId the id of the order
	 * @return returns the list with all items of the order
	 */
	public List<OrderItem> getOrderItems(int orderId) {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		conn = getInstance();
		if (conn != null) {
			try {
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT o.*"
						+ " FROM orderitem AS o"
						+ " WHERE o.orders = ? ORDER BY position";
				System.out.println(sql);
				PreparedStatement statement = conn.prepareStatement(sql);
				
				statement.setInt(1, orderId);
				
				ResultSet result = statement.executeQuery(sql);

				// Ergebniss�tze durchfahren.
				while (result.next()) {
					OrderItem orderItem;
					int position = result.getInt("position");
					int amount = result.getInt("amount");
					double price = result.getDouble("price");
					Article article = getArticle(result.getInt("article"));

					orderItem = new OrderItem(position, amount, price, article);
					orderItems.add(orderItem);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderItems;
	}

	/**
	 * This method selects a address from the database
	 * @param id the id of the address
	 * @return returns the address with the id 
	 */
	public Address getAddress(int id) {
		Address address = null;

		conn = getInstance();

		if (conn != null) {
			try {
				String sql = "SELECT * FROM address WHERE id= ?";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				
				preparedStatement.setInt(1, id);
				
				ResultSet result = preparedStatement.executeQuery(sql);

				// Ergebniss�tze durchfahren.
				if (result.next()) {
					String street = result.getString("street");
					String houseNumber = result.getString("houseNumber");
					String postCode = result.getString("postCode");
					String city = result.getString("city");
					id = result.getInt("id");
					
					address = new Address(id, street, houseNumber, postCode, city);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return address;
	}
	
	/**
	 * This method creates a new wishlist item in the databse
	 * @param article the article which should be the item
	 * @param wishlistId the id of the wishlist
	 * @return the id of the created item
	 */
	public int createWishlistItemInDB(Article article, int wishlistId){
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `wishlistitem` (`id`, `article`, `wishlist`) "
								+ "VALUES (NULL, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, article.getId());
				preparedStatement.setInt(2, wishlistId);

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
	 * This method creates a wishlist for a user
	 * @param userId the id of the user who�s wishlsit should be created
	 * @return the id of the created wishlist
	 */
	public int createWishlistInDB(int userId){
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `wishlist` (`id`, `user`) " + "VALUES (NULL, ?)",
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
	 * This method selects an wishlist from the database
	 * @param wishlistId the id of the wishlist that should be selected
	 * @return the wishlist
	 */
	public Wishlist getWishlist(int wishlistId){
		Wishlist wishlist = new Wishlist(wishlistId);

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM wishlistitem WHERE wishlist=?";
				
				statement = conn.prepareStatement(sql);
				statement.setInt(1, wishlistId);
				ResultSet result = statement.executeQuery();

				// Ergebniss�tze durchfahren.
				while (result.next()) {
					Article item = getArticle(result.getInt("article"));
					item.setId(result.getInt("id"));

					wishlist.addItem(item);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return wishlist;
	}
	
	/**
	 * This method deletes all items of a wishlist in the database
	 * @param wishlistId the id of the wishlist which�s items should be deleted
	 */
	public void deleteAllItemsOfWishlistInDB(int wishlistId){
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"DELETE FROM `wishlistitem` WHERE `wishlist` = ?");
				preparedStatement.setInt(1, wishlistId);

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method selects a list of all roles from the database
	 * @return returns a list of all roles
	 */
	public List<Role> getRoleList(){
		List<Role> roles = new ArrayList<Role>() ;
		
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM role";
				
				statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();

				// Ergebniss�tze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");

					Role role = new Role(id, name);
					roles.add(role);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return roles;
	}
	
	/**
	 * This method selects a list of all authorizations from the database
	 * @return returns a list of all authorizations
	 */
	public List<Authorization> getAuthorizationList(){
		List<Authorization> authorizations = new ArrayList<Authorization>() ;
		
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM authorization";
				
				statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();

				// Ergebniss�tze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");

					Authorization authorization = new Authorization(id, name, description);
					authorizations.add(authorization);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return authorizations;
	}
	
	/**
	 * This method returns a list of all mappings of roles and authorizations
	 * @return returns a list of all mappings
	 */
	public List<AuthorizationMapping> getAuthorizationMapping(){
		List<AuthorizationMapping> mappings = new ArrayList<AuthorizationMapping>() ;
		
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {
				

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT m.id AS id, r.id AS r_id, r.name AS role, a.id AS a_id, a.name AS authorization, a.description AS description "
						+ "FROM mappingroleauthorization AS m "
						+ "INNER JOIN role AS r ON m.role = r.id "
						+ "INNER JOIN authorization AS a ON m.authorization = a.id";
				
				statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();

				// Ergebniss�tze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					Role role = new Role(result.getInt("r_id"), result.getString("role"));
					Authorization authorization = new Authorization(result.getInt("a_id"), result.getString("authorization"), result.getString("description"));
					
					AuthorizationMapping mapping = new AuthorizationMapping(id, role, authorization);
					
					mappings.add(mapping);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return mappings;
	}
	
	/**
	 * This method creates a new role in the database
	 * @param role the role that should be created
	 * @return the id of the created role returns -1 if no role was created
	 */
	public int createRole(Role role){
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `role` (`id`, `name`) " + "VALUES (NULL, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, role.getName());

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
	 * This method creates a new mapping in the database
	 * @param mapping the mapping that should be created
	 * @return returns the id of the new mapping returns -1 if no mapping was created
	 */
	public int createAuthorizationMapping(AuthorizationMapping mapping){
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `role` (`id`, `authorization`, `role`) " + "VALUES (NULL, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, mapping.getAuthorization().getId());
				preparedStatement.setInt(2, mapping.getRole().getId());

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
	 * This method deletes an mapping of a role and an authorization in the database
	 * @param mapping the mapping that should be deleted
	 */
	public void deleteAuthorizationMapping(AuthorizationMapping mapping){
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"DELETE FROM `mappingroleauthorization` WHERE `id` = ?");
				preparedStatement.setInt(1, mapping.getId());

				preparedStatement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
