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
	
	/**
	 * Constructor for being a bean
	 */
	public Role(){
		name = null;
	}
	
	/**
	 * Constructor for an role that is already stored in the database
	 * @param id the id to set
	 * @param name the name to set
	 */
	public Role(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	/**
	 * Constructor for a role that is not stared in the database yet
	 * @param name the name to set
	 */
	public Role(String name){
		this.name = name;
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
	
	

}
