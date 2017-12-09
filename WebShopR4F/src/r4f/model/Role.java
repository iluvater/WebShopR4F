/**
 * 
 */
package r4f.model;

/**
 * @author Ture
 *
 */
public class Role {
	private int id;
	private String name;
	private String description;
	
	/**
	 * Constructor for being a bean
	 */
	public Role(){
		name = null;
		description = null;
	}
	
	/**
	 * constructor for a role that is stored in the database
	 * @param id the id of the role
	 * @param name the name of the role
	 * @param description the description of the role
	 */
	public Role(int id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	/**
	 * constructor for a role that is not stored in the database yet
	 * @param name the name of the role
	 * @param description the description of the role
	 */
	public Role( String name, String description){
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
