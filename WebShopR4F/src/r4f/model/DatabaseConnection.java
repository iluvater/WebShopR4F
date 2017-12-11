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
	 * @param addressId
	 *            Id of the address of the user
	 * @return returns the id of the created user returns -1 if there was no
	 *         user created
	 */
	public int createUserInDB(User user) {
		int userId;
		conn = getInstance();

		if (conn != null) {
			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `user` (`id`, `email`, `birthday`, `password`, `shoppingBasket`, `wishlist`)"
								+ " VALUES (NULL, ?, ?, ?, null, null)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, user.getEmail());
				preparedStatement.setDate(2, new Date(user.getBirthday().getTime()));
				preparedStatement.setString(3, user.getPassword());

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						return result.getInt(1);
					} else {
						userId = -1;
					}
				} else {
					userId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				userId = -1;
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			userId = -1;
		}
		return userId;
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
				String sql = "SELECT u.*, a.salutation, a.firstName, a.lastName, a.street, a.houseNumber, a.postCode, a.city "
						+ "FROM user AS u INNER JOIN address AS a ON a.user = u.id "
						+ "WHERE a.masterData='1' AND email=?";

				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, email);
				ResultSet result = preparedStatement.executeQuery();

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
					int wishlist = result.getInt("wishlist");
					List<Role> role = getRoleList(id);
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role, wishlist);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				String sql = "SELECT u.*, a.salutation, a.firstName, a.lastName, a.street, a.houseNumber, a.postCode, a.city "
						+ "FROM user AS u INNER JOIN address AS a ON a.user = u.id "
						+ "WHERE a.masterData='1' AND u.id=?";

				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet result = preparedStatement.executeQuery();

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
					int wishlist = result.getInt("wishlist");
					List<Role> role = getRoleList(id);
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role, wishlist);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE `user` SET `email` = ?, " + "`birthday` = ?, `password` = ?, `shoppingBasket` = ?, "
							+ " `wishlist` = ? WHERE `user`.`id` = ? ");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setDate(2, new Date(user.getBirthday().getTime()));
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.getShoppingBasket());
			preparedStatement.setInt(5, user.getWishlist());
			preparedStatement.setInt(6, user.getId());

			preparedStatement.executeUpdate();
			preparedStatement.close();

			conn.close();

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
		int articleId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `Article` (`id`, `name`, `description`, `price`, `manufacturer`, `entryDate`, `category`, `sport`) "
								+ "VALUES (NULL, ?, ?, ?, ?, null, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, artikel.getName());
				preparedStatement.setString(2, artikel.getDescription());
				preparedStatement.setDouble(3, artikel.getPrice());
				preparedStatement.setInt(4, getManufacturerId(artikel.getManufacturer()));
				preparedStatement.setInt(5, getCategoryId(artikel.getCategory()));
				preparedStatement.setInt(6, getSportId(artikel.getSport()));

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						articleId = result.getInt(1);
					} else {
						articleId = -1;
					}
				} else {
					articleId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				articleId = -1;
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			articleId = -1;
		}
		return articleId;
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
				String sql = "SELECT a.id, a.name, a.description, a.price, m.name as manufacturer, a.entryDate, ca.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS ca INNER JOIN manufacturer AS m INNER JOIN sport AS s"
						+ " WHERE a.category = ca.id AND a.manufacturer = m.id AND a.sport = s.id AND a.id = ?";

				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");
					List<Integer> sizes = getSizeList(id);
					double price = result.getDouble("price");
					String manufacturer = result.getString("manufacturer");
					List<String> color = getColorList(id);
					Date entryDate = result.getDate("entryDate");
					String category = result.getString("category");
					String sport = result.getString("sport");
					int mainImage = getImageId(id, true);
					List<Integer> images = getImageIdList(id);

					article = new Article(id, name, description, sizes, price, manufacturer, color, entryDate, category,
							sport, mainImage, images);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return article;
	}

	/**
	 * This method selects all article in the Database
	 * 
	 * @return returns all articles in the database
	 */
	public List<Article> getArticleList(FilterList filter) {
		List<Article> articleList = new ArrayList<Article>();

		conn = getInstance();
		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT DISTINCT a.id, a.name, a.description, a.price, m.name as manufacturer, a.entryDate, ca.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS ca ON a.category = ca.id"
						+ " INNER JOIN manufacturer AS m ON a.manufacturer = m.id"
						+ " INNER JOIN sport AS s ON a.sport = s.id"
						+ " INNER JOIN mappingarticlecolor as mac ON	a.id = mac.article"
						+ " INNER JOIN mappingarticlesize as mas ON	a.id = mas.article"
						+ " INNER JOIN color as co ON mac.color = co.id" + " INNER JOIN size as si ON mas.size = si.id "
						+ filter.getSQLFilter("a", "ca", "m", "s", "co", "si");
				System.out.println(sql);

				preparedStatement = conn.prepareStatement(sql);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					Article article;
					int id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");
					List<Integer> sizes = getSizeList(id);
					double price = result.getDouble("price");
					String manufacturer = result.getString("manufacturer");
					List<String> colors = getColorList(id);
					Date entryDate = result.getDate("entryDate");
					String category = result.getString("category");
					String sport = result.getString("sport");
					int mainImage = getImageId(id, true);
					List<Integer> images = getImageIdList(id);

					article = new Article(id, name, description, sizes, price, manufacturer, colors, entryDate,
							category, sport, mainImage, images);
					articleList.add(article);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return articleList;
	}

	/**
	 * This method selects list of all articles that match with the search
	 * pattern
	 * 
	 * @param searchPattern
	 *            the search pattern
	 * @return returns a list of the articles that matches with the search
	 *         pattern
	 */
	public List<Article> getArticleListSearch(String searchPattern) {
		List<Article> articleList = new ArrayList<Article>();

		conn = getInstance();
		if (conn != null) {
			try {
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT DISTINCT a.id, a.name, a.description, a.price, m.name as manufacturer, a.entryDate, ca.name as category, s.name as sport "
						+ " FROM article AS a INNER JOIN category AS ca ON a.category = ca.id"
						+ " INNER JOIN manufacturer AS m ON a.manufacturer = m.id"
						+ " INNER JOIN sport AS s ON a.sport = s.id" + " WHERE LOWER(a.id) LIKE LOWER('%"
						+ searchPattern + "%') OR LOWER(a.name) LIKE LOWER('%" + searchPattern
						+ "%') Or LOWER(a.description) LIKE LOWER('%" + searchPattern + "%')";
				System.out.println(sql);
				PreparedStatement statement = conn.prepareStatement(sql);

				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					Article article;
					int id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");
					List<Integer> sizes = getSizeList(id);
					double price = result.getDouble("price");
					String manufacturer = result.getString("manufacturer");
					List<String> colors = getColorList(id);
					Date entryDate = result.getDate("entryDate");
					String category = result.getString("category");
					String sport = result.getString("sport");
					int mainImage = getImageId(id, true);
					List<Integer> images = getImageIdList(id);

					article = new Article(id, name, description, sizes, price, manufacturer, colors, entryDate,
							category, sport, mainImage, images);
					articleList.add(article);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

			PreparedStatement preparedStatement = conn
					.prepareStatement("UPDATE `article` SET `name` = ?, `description` = ?, "
							+ "`price` = ?, `manufacturer` = ?, `category` = ?, " + "`sport` = ?"
							+ " WHERE `article`.`id` = ?", Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, article.getName());
			preparedStatement.setString(2, article.getDescription());
			preparedStatement.setDouble(3, article.getPrice());
			preparedStatement.setInt(4, getManufacturerId(article.getManufacturer()));
			preparedStatement.setInt(5, getCategoryId(article.getCategory()));
			preparedStatement.setInt(6, getSportId(article.getSport()));

			preparedStatement.setInt(7, article.getId());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

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

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
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

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
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

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
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

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	/**
	 * This method selects the id of a color
	 * 
	 * @param color
	 *            the color which´s id should be selected
	 * @return returns the id of the color
	 */
	public int getColorId(String color) {
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

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
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
		int shoppingBasketId;
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
						shoppingBasketId = result.getInt(1);
					} else {
						shoppingBasketId = -1;
					}
				} else {
					shoppingBasketId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				shoppingBasketId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			shoppingBasketId = -1;
		}
		return shoppingBasketId;
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
				String sql = "SELECT sh.amount, sh.id, sh.article, c.name AS color, s.size AS size "
						+ "FROM shoppingbasketitem AS sh INNER JOIN size AS s ON s.id = sh.size INNER JOIN color AS c ON c.id = sh.color "
						+ "WHERE shoppingBasket=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					ShoppingBasketItem item = new ShoppingBasketItem();
					item.setAmount(result.getInt("amount"));
					item.setId(result.getInt("id"));
					item.setColor(result.getString("color"));
					item.setSize(result.getInt("size"));

					int aritcleId = result.getInt("article");
					item.setArticle(this.getArticle(aritcleId));

					shoppingBasket.getItems().add(item);

				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		int itemId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `shoppingbasketitem` (`id`, `article`, `shoppingBasket`, `amount`, `size`, `color`) "
								+ "VALUES (NULL, ?, ? , ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, shoppingBasketItem.getArticle().getId());
				preparedStatement.setInt(2, shoppingBasketId);
				preparedStatement.setInt(3, shoppingBasketItem.getAmount());
				preparedStatement.setInt(4, getSizeId(shoppingBasketItem.getSize()));
				preparedStatement.setInt(5, getColorId(shoppingBasketItem.getColor()));

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						itemId = result.getInt(1);
					} else {
						itemId = -1;
					}
				} else {
					itemId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				itemId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			itemId = -1;
		}
		return itemId;
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
	public int createImageInDB(InputStream inputStream, String contentType, boolean mainImage, int articleId) {
		int imageId;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO image (image, type, mainImage, article) values (?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(2, contentType);
				preparedStatement.setBoolean(3, mainImage);
				preparedStatement.setInt(4, articleId);

				if (inputStream != null) {
					// fetches input stream of the upload file for the blob
					// column
					preparedStatement.setBlob(1, inputStream);
				}

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						imageId = result.getInt(1);
					} else {
						imageId = -1;
					}
				} else {
					imageId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				imageId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			imageId = -1;
		}
		return imageId;
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

				// Ergebnissätze durchfahren.
				if (result.next()) {
					blob = result.getBlob("image");
					image = new Image(id, blob, result.getString("type"), result.getBoolean("mainImage"));
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return image;
	}

	/**
	 * This method updates an image in the database
	 * 
	 * @param imageId
	 *            the id of the image that should be updated
	 * @param imageStream
	 *            the new content
	 * @param imageType
	 *            the new content type of the image
	 * @throws SQLException
	 *             an SQLException will be thrown if any error occurred
	 */
	public void updateImageInDB(int imageId, InputStream imageStream, String imageType, boolean mainImage)
			throws SQLException {
		conn = getInstance();

		if (conn != null) {

			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE `image` SET `image` = ?, `type` = ? " + " WHERE `image`.`id` = ?, `mainImage`= ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setBlob(1, imageStream);
			preparedStatement.setString(2, imageType);
			preparedStatement.setInt(3, imageId);
			preparedStatement.setBoolean(4, mainImage);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();
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
	public int createAddressInDB(int userId, String firstName, String lastName, String street, String houseNumber,
			String postCode, String city, boolean masterData, String salutation) {
		int addressId;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO address (firstName, lastName, street, houseNumber, postCode, city, masterData, user, salutation)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, street);
				preparedStatement.setString(4, houseNumber);
				preparedStatement.setString(5, postCode);
				preparedStatement.setString(6, city);
				preparedStatement.setBoolean(7, masterData);
				preparedStatement.setInt(8, userId);
				preparedStatement.setString(9, salutation);

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						addressId = result.getInt(1);
					} else {
						addressId = -1;
					}
				} else {
					addressId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				addressId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			addressId = -1;
		}
		return addressId;
	}

	/**
	 * This method updates the master data address of an user
	 * 
	 * @param userId
	 *            the id of user who's address should be updated
	 * @param street
	 *            the new street
	 * @param houseNumber
	 *            the new houseNumber
	 * @param postCode
	 *            the new postCode
	 * @param city
	 *            the new City
	 * @throws SQLException
	 */
	public void updateAddressInDB(int userId, String firstName, String lastName, String street, String houseNumber,
			String postCode, String city, String salutation) throws SQLException {
		conn = getInstance();

		if (conn != null) {

			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE `address` SET `street` = ?, `houseNumber` = ?, `postCode` = ?, "
							+ "`city` = ?, `firstname` = ?, `lastname` = ?, `salutation` = ? WHERE `address`.`user` = ? AND `masterData`= ?",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, street);
			preparedStatement.setString(2, houseNumber);
			preparedStatement.setString(3, postCode);
			preparedStatement.setString(4, city);
			preparedStatement.setString(5, firstName);
			preparedStatement.setString(6, lastName);
			preparedStatement.setString(7, salutation);
			preparedStatement.setInt(8, userId);
			preparedStatement.setBoolean(9, true);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();

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

				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				if (result.next()) {
					String salutation = result.getString("salutation");
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String street = result.getString("street");
					String houseNumber = result.getString("houseNumber");
					String postCode = result.getString("postCode");
					String city = result.getString("city");
					int id = result.getInt("id");

					address = new Address(id, firstName, lastName, street, houseNumber, postCode, city, salutation);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return address;
	}

	/**
	 * This method creates an new order in the database
	 * 
	 * @param order
	 *            the order that should be created
	 * @return the id of the created order if an error occurred during the
	 *         creation it will return -1
	 */
	public int createOrderInDB(Order order) {
		int orderId;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO orders (id, entryDate, user, deliveryAddress, billingAddress, paymentMethod, deliveryDate) values (null, null, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, order.getUser().getId());
				preparedStatement.setInt(2, order.getDeliveryAddress().getId());
				preparedStatement.setInt(3, order.getBillingAddress().getId());
				preparedStatement.setInt(4, getPaymentMethodId(order.getPaymentMethod()));
				preparedStatement.setDate(5, new Date(order.getDeliveryDate().getTime()));

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						orderId = result.getInt(1);
					} else {
						orderId = -1;
					}
				} else {
					orderId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				orderId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			orderId = -1;
		}
		return orderId;
	}

	/**
	 * This method selects the id of an paymentMethod
	 * 
	 * @param paymentMethod
	 *            the payment methods who´s id should be selected
	 * @return the id of the payment method if no id was found it will return -1
	 */
	public int getPaymentMethodId(String paymentMethod) {
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

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	/**
	 * This methdo creates a new OrderItem for an order in the database
	 * 
	 * @param orderId
	 *            the id of the order
	 * @param item
	 *            the item that should be created
	 */
	public void createOrderItemInDB(int orderId, OrderItem item) {
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			try {
				String sql = "INSERT INTO `orderitem` (`orders`, `position`, `article`, `amount`, `price`, `color`, `size`) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, orderId);
				preparedStatement.setInt(2, item.getPosition());
				preparedStatement.setInt(3, item.getArticle().getId());
				preparedStatement.setInt(4, item.getAmount());
				preparedStatement.setDouble(5, item.getArticle().getPrice());
				preparedStatement.setInt(6, getSizeId(item.getSize()));
				preparedStatement.setInt(7, getColorId(item.getColor()));

				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method selects all order for a certain user
	 * 
	 * @param userId
	 *            the id of the user
	 * @return returns a list of all orders from the user
	 */
	public List<Order> getOrderList(int userId) {
		List<Order> orderList = new ArrayList<Order>();

		conn = getInstance();
		if (conn != null) {
			try {
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT o.id, o.entryDate, o.deliveryAddress, o.billingAddress, o.deliveryDate, p.name AS paymentMethod"
						+ " FROM orders AS o INNER JOIN paymentmethod AS p ON o.paymentMethod = p.id"
						+ " WHERE o.user = ?" + " ORDER BY entryDate";
				System.out.println(sql);
				PreparedStatement statement = conn.prepareStatement(sql);

				statement.setInt(1, userId);

				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					Order order;
					int id = result.getInt("id");
					Date entryDate = result.getDate("entryDate");
					Address deliveryAddress = getAddress(result.getInt("deliveryAddress"));
					Address billingAddress = getAddress(result.getInt("billingAddress"));
					Date deliveryDate = result.getDate("deliveryDate");
					String paymentMethod = result.getString("paymentMethod");
					User user = getUser(userId);
					List<OrderItem> orderItems = getOrderItems(id);

					order = new Order(id, entryDate, deliveryAddress, billingAddress, paymentMethod, orderItems,
							deliveryDate, user);
					orderList.add(order);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return orderList;
	}

	/**
	 * This method selects an order from the database
	 * 
	 * @param orderId
	 *            the id of the order that should be selected
	 * @return returns the order or null if no order was selected
	 */
	public Order getOrder(int orderId) {
		Order order = null;

		conn = getInstance();
		if (conn != null) {
			try {
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT o.id, o.entryDate, o.deliveryAddress, o.billingAddress, o.deliveryDate, o.user, p.name AS paymentMethod"
						+ " FROM orders AS o INNER JOIN paymentmethod AS p ON o.paymentMethod = p.id"
						+ " WHERE o.id = ?" + " ORDER BY entryDate";
				System.out.println(sql);
				PreparedStatement statement = conn.prepareStatement(sql);

				statement.setInt(1, orderId);

				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					Date entryDate = result.getDate("entryDate");
					Address deliveryAddress = getAddress(result.getInt("deliveryAddress"));
					Address billingAddress = getAddress(result.getInt("billingAddress"));
					Date deliveryDate = result.getDate("deliveryDate");
					String paymentMethod = result.getString("paymentMethod");
					User user = getUser(result.getInt("user"));
					List<OrderItem> orderItems = getOrderItems(id);

					order = new Order(id, entryDate, deliveryAddress, billingAddress, paymentMethod, orderItems,
							deliveryDate, user);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return order;
	}

	/**
	 * This method selects a list of items for a certain order
	 * 
	 * @param orderId
	 *            the id of the order
	 * @return returns the list with all items of the order
	 */
	public List<OrderItem> getOrderItems(int orderId) {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();

		conn = getInstance();
		if (conn != null) {
			try {
				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT o.position, o.amount, o.price, o.article, c.name AS color, s.size AS size"
						+ " FROM orderitem AS o INNER JOIN color AS c ON o.color = c.id INNER JOIN size AS s ON o.size = s.id"
						+ " WHERE o.orders = ? ORDER BY position";
				System.out.println(sql);
				PreparedStatement statement = conn.prepareStatement(sql);

				statement.setInt(1, orderId);

				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					OrderItem orderItem;
					int position = result.getInt("position");
					int amount = result.getInt("amount");
					double price = result.getDouble("price");
					Article article = getArticle(result.getInt("article"));
					String color = result.getString("color");
					int size = result.getInt("size");

					orderItem = new OrderItem(position, amount, price, article, size, color);
					orderItems.add(orderItem);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return orderItems;
	}

	/**
	 * This method selects a address from the database
	 * 
	 * @param id
	 *            the id of the address
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

				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				if (result.next()) {
					String salutation = result.getString("salutation");
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					String street = result.getString("street");
					String houseNumber = result.getString("houseNumber");
					String postCode = result.getString("postCode");
					String city = result.getString("city");
					id = result.getInt("id");

					address = new Address(id, firstName, lastName, street, houseNumber, postCode, city, salutation);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return address;
	}

	/**
	 * This method creates a new wishlist item in the database
	 * 
	 * @param wishlistItem
	 *            the article which should be the item
	 * @param wishlistId
	 *            the id of the wishlist
	 * @return the id of the created item
	 */
	public int createWishlistItemInDB(WishlistItem wishlistItem, int wishlistId) {
		int itemId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("INSERT INTO `wishlistitem` (`id`, `article`, `wishlist`, `size`, `color`) "
								+ "VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, wishlistItem.getArticle().getId());
				preparedStatement.setInt(2, wishlistId);
				preparedStatement.setInt(3, getSizeId(wishlistItem.getSize()));
				preparedStatement.setInt(4, getColorId(wishlistItem.getColor()));

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						itemId = result.getInt(1);
					} else {
						itemId = -1;
					}
				} else {
					itemId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				itemId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			itemId = -1;
		}
		return itemId;
	}

	/**
	 * This method creates a wishlist for a user
	 * 
	 * @param userId
	 *            the id of the user who´s wishlsit should be created
	 * @return the id of the created wishlist
	 */
	public int createWishlistInDB(int userId) {
		int wishlistId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `wishlist` (`id`, `user`) " + "VALUES (NULL, ?)", Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, userId);

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						wishlistId = result.getInt(1);
					} else {
						wishlistId = -1;
					}
				} else {
					wishlistId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				wishlistId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			wishlistId = -1;
		}
		return wishlistId;
	}

	/**
	 * This method selects an wishlist from the database
	 * 
	 * @param wishlistId
	 *            the id of the wishlist that should be selected
	 * @return the wishlist
	 */
	public Wishlist getWishlist(int wishlistId) {
		Wishlist wishlist = new Wishlist(wishlistId);

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT w.id, w.article, s.size AS size, c.name AS color FROM wishlistitem AS w "
						+ "INNER JOIN size AS s ON w.size = s.id " + "INNER JOIN color AS c ON w.color = c.id "
						+ "WHERE wishlist=?";

				statement = conn.prepareStatement(sql);
				statement.setInt(1, wishlistId);
				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					Article article = getArticle(result.getInt("article"));
					int size = result.getInt("size");
					String color = result.getString("color");

					WishlistItem wishlistitem = new WishlistItem(id, size, color, article);

					wishlist.addItem(wishlistitem);

				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return wishlist;
	}

	/**
	 * This method deletes all items of a wishlist in the database
	 * 
	 * @param wishlistId
	 *            the id of the wishlist which´s items should be deleted
	 */
	public void deleteAllItemsOfWishlistInDB(int wishlistId) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("DELETE FROM `wishlistitem` WHERE `wishlist` = ?");
				preparedStatement.setInt(1, wishlistId);

				preparedStatement.executeUpdate();
				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method selects a list of all roles from the database
	 * 
	 * @return returns a list of all roles
	 */
	public List<Role> getRoleList() {
		List<Role> roles = new ArrayList<Role>();

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM role";

				statement = conn.prepareStatement(sql);
				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");

					Role role = new Role(id, name, description);
					roles.add(role);

				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return roles;
	}

	public List<Role> getRoleList(int userId) {
		List<Role> roles = new ArrayList<Role>();

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT r.* FROM mappinguserrole AS m INNER JOIN role AS R ON m.role = r.id WHERE m.user = ?";

				statement = conn.prepareStatement(sql);
				statement.setInt(1, userId);
				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");

					Role role = new Role(id, name, description);
					roles.add(role);

				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return roles;
	}

	/**
	 * This method creates a new role in the database
	 * 
	 * @param role
	 *            the role that should be created
	 * @return the id of the created role returns -1 if no role was created
	 */
	public int createRole(Role role) {
		int roleId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `role` (`id`, `name`, `description`) " + "VALUES (NULL, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, role.getName());
				preparedStatement.setString(2, role.getDescription());

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						roleId = result.getInt(1);
					} else {
						roleId = -1;
					}
				} else {
					roleId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				roleId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			roleId = -1;
		}
		return roleId;
	}

	/**
	 * This method selects all sizes for an article
	 * 
	 * @param articleId
	 *            the id of the article which´s sizes should be selected
	 * @return the list of sizes for that article
	 */
	public List<Integer> getSizeList(int articleId) {
		List<Integer> sizes = new ArrayList<Integer>();

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT s.size AS size FROM mappingarticlesize AS m INNER JOIN size AS s ON s.id = m.size WHERE article = ? ORDER BY s.size";

				statement = conn.prepareStatement(sql);
				statement.setInt(1, articleId);
				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int size = result.getInt("size");

					sizes.add(size);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return sizes;
	}

	/**
	 * This method selects all colors of an article
	 * 
	 * @param articleId
	 *            the id of the article which´s color should be selected
	 * @return the list of colors
	 */
	public List<String> getColorList(int articleId) {
		List<String> colors = new ArrayList<String>();

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT c.name AS color FROM mappingarticlecolor AS m INNER JOIN color AS c ON c.id = m.color WHERE article = ? ORDER BY c.name";

				statement = conn.prepareStatement(sql);
				statement.setInt(1, articleId);
				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					String size = result.getString("color");

					colors.add(size);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return colors;
	}

	/**
	 * This method selects the id for a size from the database
	 * 
	 * @param size
	 *            the size which´s database should be selected
	 * @return returns the id of the size or -1 if no size was selected
	 */
	public int getSizeId(int size) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM size WHERE size=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, size);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	/**
	 * This method creates a mapping of a size and an article
	 * 
	 * @param size
	 *            the size
	 * @param articleId
	 *            the article
	 * @return the id of the mapping entry that was created returns null if no
	 *         entry was created
	 */
	public int createSizeMapping(int size, int articleId) {
		int mappingId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `mappingarticlesize` (`id`, `size`, `article`) " + "VALUES (NULL, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, getSizeId(size));
				preparedStatement.setInt(2, articleId);

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						mappingId = result.getInt(1);
					} else {
						mappingId = -1;
					}
				} else {
					mappingId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				mappingId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			mappingId = -1;
		}
		return mappingId;
	}

	/**
	 * This method creates a mapping of a color and an article
	 * 
	 * @param color
	 *            the color
	 * @param articleId
	 *            the article
	 * @return the id of the mapping entry that was created returns null if no
	 *         entry was created
	 */
	public int createColorMapping(String color, int articleId) {
		int mappingId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `mappingarticlecolor` (`id`, `color`, `article`) " + "VALUES (NULL, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, getColorId(color));
				preparedStatement.setInt(2, articleId);

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						mappingId = result.getInt(1);
					} else {
						mappingId = -1;
					}
				} else {
					mappingId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				mappingId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			mappingId = -1;
		}
		return mappingId;
	}

	/**
	 * This method deletes all mapping of that article to a size for a certain
	 * article
	 * 
	 * @param article
	 *            the article which´s mappings should be deleted
	 */
	public void deleteSizeMapping(Article article) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("DELETE FROM `mappingarticlesize` WHERE `article` = ?");
				preparedStatement.setInt(1, article.getId());

				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method deletes all mapping of that article to a color for a certain
	 * article
	 * 
	 * @param article
	 *            the article which´s mappings should be deleted
	 */
	public void deleteColorMapping(Article article) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("DELETE FROM `mappingarticlecolor` WHERE `article` = ?");
				preparedStatement.setInt(1, article.getId());

				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method selects a list of all images from the database
	 * 
	 * @param articleId
	 *            the id of the article which´s images should be created
	 * @return the list of the image ids
	 */
	public List<Integer> getImageIdList(int articleId) {
		List<Integer> images = new ArrayList<Integer>();

		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement statement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM image WHERE article = ? AND mainImage = ?";

				statement = conn.prepareStatement(sql);
				statement.setInt(1, articleId);
				statement.setBoolean(2, false);
				ResultSet result = statement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					int size = result.getInt("id");

					images.add(size);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return images;
	}

	/**
	 * This method selects the id of an images from the database
	 * 
	 * @param articleId
	 *            the id of the article which´s image id should be selected
	 * @param mainImage
	 *            boolean that specified whether the main image or not should be
	 *            created
	 * @return returns the id of the image or returns -1 if no images was
	 *         selected
	 */
	public int getImageId(int articleId, boolean mainImage) {
		int id = -1;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT id FROM image WHERE article=? and mainImage=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, articleId);
				preparedStatement.setBoolean(2, mainImage);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				if (result.next()) {
					id = result.getInt("id");
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	/**
	 * This method creates a mapping of a role and a user
	 * 
	 * @param user
	 *            the user
	 * @param role
	 *            the role
	 * @return returns the id of the new entry in the database, returns -1 if no
	 *         entry was created
	 */
	public int createRoleMapping(int userId, int roleId) {
		int mappingId;
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn.prepareStatement(
						"INSERT INTO `mappinguserrole` (`user`, `role`) " + "VALUES ( ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, roleId);

				int lines = preparedStatement.executeUpdate();

				if (lines != 0) {
					ResultSet result = preparedStatement.getGeneratedKeys();
					if (result.next()) {
						mappingId = result.getInt(1);
					} else {
						mappingId = -1;
					}
				} else {
					mappingId = -1;
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				mappingId = -1;
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			mappingId = -1;
		}
		return mappingId;
	}

	/**
	 * This method deletes all mappings of a user and his roles
	 * 
	 * @param user
	 *            the user who´s roles should be deleted
	 */
	public void deleteRoleMappings(User user) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("DELETE FROM `mappinguserrole` WHERE `user` = ?");
				preparedStatement.setInt(1, user.getId());

				preparedStatement.executeUpdate();
				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method selects a role from the database
	 * 
	 * @param roleId
	 *            the roleId
	 * @return the role or null if no role was selected
	 */
	public Role getRole(int roleId) {
		Role role = null;
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT * FROM role WHERE id=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, roleId);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				if (result.next()) {
					int id = result.getInt("id");
					String name = result.getString("name");
					String description = result.getString("description");

					role = new Role(id, name, description);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return role;
	}

	/**
	 * This method updates a role in the databasae
	 * 
	 * @param role
	 *            the rol containing the new values
	 * @throws SQLException
	 *             will be thrown if an error occures during update
	 */
	public void updateRoleInDB(Role role) throws SQLException {
		conn = getInstance();

		if (conn != null) {

			PreparedStatement preparedStatement = conn.prepareStatement(
					"UPDATE `role` SET `name`= ?, `description` = ? WHERE `role`.`id` = ? ",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(3, role.getId());
			preparedStatement.setString(1, role.getName());
			preparedStatement.setString(2, role.getDescription());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			conn.close();
		}
	}

	/**
	 * This method selects a list of all user from the database
	 * 
	 * @return a list of all user in the database
	 */
	public List<User> getUserList() {
		List<User> userList = new ArrayList<User>();
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT u.*, a.salutation, a.firstName, a.lastName, a.street, a.houseNumber, a.postCode, a.city "
						+ "FROM user AS u INNER JOIN address AS a ON a.user = u.id " + "WHERE a.masterData='1'";

				preparedStatement = conn.prepareStatement(sql);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					User user;
					int id = result.getInt("id");
					String email = result.getString("email");
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
					List<Role> role = getRoleList(id);
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role, wishlist);
					userList.add(user);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return userList;
	}

	/**
	 * This method deletes a mapping of a role and an user
	 * 
	 * @param userId
	 *            the id of the user
	 * @param roleId
	 *            the id of the role
	 */
	public void deleteUserRoleMapping(int userId, int roleId) {
		conn = getInstance();

		if (conn != null) {

			try {

				PreparedStatement preparedStatement = conn
						.prepareStatement("DELETE FROM `mappinguserrole` WHERE `user` = ? AND role =?");
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, roleId);

				preparedStatement.executeUpdate();
				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method returns a list of user that contain the search pattern in
	 * their last name, id, email
	 * 
	 * @param searchPattern
	 *            the search pattern
	 * @return the list of users
	 */
	public List<User> getUserListSearchPattern(String searchPattern) {
		List<User> userList = new ArrayList<User>();
		conn = getInstance();

		if (conn != null) {
			// Anfrage-Statement erzeugen.
			PreparedStatement preparedStatement;
			try {

				// Ergebnistabelle erzeugen und abholen.
				String sql = "SELECT u.*, a.salutation, a.firstName, a.lastName, a.street, a.houseNumber, a.postCode, a.city "
						+ "FROM `user` AS u INNER JOIN address AS a On a.user = u.id WHERE a.masterData='1' AND "
						+ "( u.id  LIKE " + searchPattern + " OR a.lastName LIKE " + searchPattern + " OR u.email LIKe "
						+ searchPattern + ");";

				preparedStatement = conn.prepareStatement(sql);
				ResultSet result = preparedStatement.executeQuery();

				// Ergebnissätze durchfahren.
				while (result.next()) {
					User user;
					int id = result.getInt("id");
					String email = result.getString("email");
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
					List<Role> role = getRoleList(id);
					user = new User(id, firstName, lastName, email, birthday, password, street, houseNumber, postCode,
							city, salutation, shoppingBasket, role, wishlist);
					userList.add(user);
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return userList;
	}
}
