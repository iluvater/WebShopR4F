/**
 * 
 */
package r4f.model;

/**
 * @author Ture
 *
 */
public class AuthorizationMapping {
	private int id;
	private Role role;
	private Authorization authorization;
	
	/**
	 * Constructor for being a bean
	 */
	public AuthorizationMapping(){
		role = null;
		authorization = null;
	}
	
	/**
	 * This Constructor can be used if the Mapping is already stored in the database
	 * @param id
	 * @param role
	 * @param authorization
	 */
	public AuthorizationMapping(int id, Role role, Authorization authorization){
		this.id = id;
		this.role= role;
		this.authorization= authorization;
	}
	
	/**
	 * This Constructor can be used if the Mapping is already stored in the database
	 * @param role
	 * @param authorization
	 */
	public AuthorizationMapping(Role role, Authorization authorization){
		this.role= role;
		this.authorization= authorization;
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
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * @return the authorization
	 */
	public Authorization getAuthorization() {
		return authorization;
	}
	/**
	 * @param authorization the authorization to set
	 */
	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}
	

}
