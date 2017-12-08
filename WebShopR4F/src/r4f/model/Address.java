/**
 * 
 */
package r4f.model;

/**
 * @author Ture
 *
 */
public class Address {
	private int id;
	private String street;
	private String houseNumber;
	private String postCode;
	private String city;
	private String firstName;
	private String lastName;
	private String salutation;
	
	/**
	 * This Constructor can be used if the address is already stored in the database
	 * @param id
	 * @param street
	 * @param houseNumber
	 * @param postCode
	 * @param city
	 */
	public Address(int id, String firstName, String lastName, String street, String houseNumber, String postCode, String city, String salutation){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
		this.salutation = salutation;
	}
	
	/**
	 * This Constructor can be used if the address is not stored in the database yet
	 * @param street
	 * @param houseNumber
	 * @param postCode
	 * @param city
	 */
	public Address(String firstName, String lastName, String street, String houseNumber, String postCode, String city, String salutation){
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
		this.salutation = salutation;
	}
	
	/**
	 * Constructor that is needed for being a java bean
	 */
	public Address(){
		street = null;
		houseNumber = null;
		postCode = null;
		city = null;
	}
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
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
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}
	/**
	 * @param houseNumber the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * @param salutation the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	
}
