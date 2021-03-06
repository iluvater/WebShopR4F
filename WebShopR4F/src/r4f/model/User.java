package r4f.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents a user that is registred to the webshop.
 * 
 * @author Ture
 *
 *
 */
public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private Date birthday;
	private String password;
	private String street;
	private String houseNumber;
	private String postCode;
	private String city;
	private String salutation;
	private int shoppingBasket;
	private int wishlist;
	private List<Role> role;
	private String confirmationCode;

	/**
	 * Constructor that should be used when the id is known
	 * 
	 * @param id
	 *            the id to set
	 * @param firstName
	 *            the firstName to set
	 * @param lastName
	 *            the lastName to set
	 * @param email
	 *            the email to set
	 * @param birthday
	 *            the birthday to set
	 * @param password
	 *            the password to set
	 * @param street
	 *            the street to set
	 * @param houseNumber
	 *            the houseNumber to set
	 * @param postCode
	 *            the postCode to set
	 * @param city
	 *            the city to set
	 * @param salutation
	 *            the salutation to set
	 * @param shoppingBasket
	 *            the shoppingBasket to set
	 * @param wishlist
	 *            the wish list to set
	 * @param role
	 *            the role to set
	 */
	public User(int id, String firstName, String lastName, String email, Date birthday, String password, String street,
			String houseNumber, String postCode, String city, String salutation, int shoppingBasket, List<Role> role,
			int wishlist) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		setEmail(email);
		this.password = password;
		this.street = street;
		this.houseNumber = houseNumber;
		setPostCode(postCode);
		this.city = city;
		setSalutation(salutation);
		this.shoppingBasket = shoppingBasket;
		this.wishlist = wishlist;
		this.role = role;
		confirmationCode = User.getConfirmationCode(6);
	}

	/**
	 * Constructor that should be used when id is not created from db
	 * 
	 * @param firstName
	 *            the firstName to set
	 * @param lastName
	 *            the lastName to set
	 * @param email
	 *            the email to set
	 * @param birthday
	 *            the birthday to set
	 * @param password
	 *            the password to set
	 * @param street
	 *            the street to set
	 * @param houseNumber
	 *            the houseNumber to set
	 * @param postCode
	 *            the postCode to set
	 * @param city
	 *            the city to set
	 * @param salutation
	 *            the salutation to set
	 */
	public User(String firstName, String lastName, String email, Date birthday, String password, String street,
			String houseNumber, String postCode, String city, String salutation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		setEmail(email);
		this.password = password;
		this.street = street;
		this.houseNumber = houseNumber;
		setPostCode(postCode);
		this.city = city;
		setSalutation(salutation);
		confirmationCode = User.getConfirmationCode(6);
	}

	/**
	 * constructor used for being a bean
	 */
	public User() {
		this.firstName = null;
		this.lastName = null;
		this.birthday = null;
		this.email = null;
		this.password = null;
		this.street = null;
		this.houseNumber = null;
		this.postCode = null;
		this.city = null;
		this.salutation = null;
		confirmationCode = User.getConfirmationCode(6);
		this.role = new ArrayList<Role>();
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
		if (checkEmail(email)) {
			this.email = email;
		} else {
			this.email = null;
		}

	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber
	 *            the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode
	 *            the postCode to set
	 */
	public void setPostCode(String postCode) {
		if (checkPostCode(postCode)) {
			this.postCode = postCode;
		} else {
			this.postCode = null;
		}

	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * @param salutation
	 *            the salutation to set
	 */
	public void setSalutation(String salutation) {
		if (checkSalutation(salutation)) {
			this.salutation = salutation;
		} else {
			this.salutation = null;
		}

	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean checkPassword(String password) {
		if (this.password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks whether an email has the right format or not
	 * 
	 * @param email
	 *            possible email that should be checked
	 * @return returns true when the email matches the pattern returns false
	 *         when the email does not match the pattern
	 */
	public static boolean checkEmail(String email) {
		if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This mehtod checks whether a salutation ist correct or not
	 * 
	 * @param salutation
	 *            salutation String that should be checked
	 * @return return true when the String salutation is either Herr or Frau
	 *         return false when not
	 */
	public static boolean checkSalutation(String salutation) {
		if (salutation.equals("Herr") || salutation.equals("Frau")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks whether a postCode has the right format or not
	 * 
	 * @param postCode
	 *            postCode String that should be check
	 * @return returns true when the String postCode contains only 5 digits
	 */
	public static boolean checkPostCode(String postCode) {
		if (postCode.matches("\\d{5}")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks whether a date is valid or not
	 * 
	 * @param date
	 *            the date to check
	 * @return returns true if the date is older than 18 years
	 */
	@SuppressWarnings("deprecation")
	public static boolean checkBirthday(Date date) {
		Date today = new Date();
		today.setYear(today.getYear() - 14);
		if (today.compareTo(date) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the shoppingBasket
	 */
	public int getShoppingBasket() {
		return shoppingBasket;
	}

	/**
	 * @param shoppingBasket
	 *            the shoppingBasket to set
	 */
	public void setShoppingBasket(int shoppingBasket) {
		this.shoppingBasket = shoppingBasket;
	}

	/**
	 * @return the role
	 */
	public List<Role> getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(List<Role> role) {
		this.role = role;
	}

	/**
	 * @return the wishlist
	 */
	public int getWishlist() {
		return wishlist;
	}

	/**
	 * @param wishlist
	 *            the wishlist to set
	 */
	public void setWishlist(int wishlist) {
		this.wishlist = wishlist;
	}

	/**
	 * @return the confirmationCode
	 */
	public String getConfirmationCode() {
		return confirmationCode;
	}

	/**
	 * This method generates a new confirmation string
	 * 
	 * @param length
	 *            the length of the confirmation code
	 * @return the code
	 */
	public static String getConfirmationCode(int length) {
		String sb = "";
		for (int i = 0; i < length; i++) {
			sb = sb + (Integer.toString(((int) (Math.random() * 10 - 1))));
		}
		return sb.toString();
	}

}
