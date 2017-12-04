/**
 * 
 */
package r4f.model;

/**
 * @author Ture
 *
 */
public class Authorization {
	private int id;
	private String name;
	private String description;
	
	/**
	 * Constructor for being a bean
	 */
	public Authorization(){
		name = null;
		description = null;
	}
	
	/**
	 * Constructor for an authorization that is stored in the database
	 * @param name the name to set
	 * @param description the description to set
	 */
	public Authorization(String name, String description){
		this.name = name;
		this.description = description;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
