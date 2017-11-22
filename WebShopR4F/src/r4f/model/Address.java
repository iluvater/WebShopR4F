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
	
	/**
	 * This Constructor can be used if the address is already stored in the database
	 * @param id
	 * @param street
	 * @param houseNumber
	 * @param postCode
	 * @param city
	 */
	public Address(int id, String street, String houseNumber, String postCode, String city){
		this.id = id;
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
	}
	
	/**
	 * This Constructor can be used if the address is not stored in the database yet
	 * @param street
	 * @param houseNumber
	 * @param postCode
	 * @param city
	 */
	public Address(String street, String houseNumber, String postCode, String city){
		this.street = street;
		this.houseNumber = houseNumber;
		this.postCode = postCode;
		this.city = city;
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
	
}
