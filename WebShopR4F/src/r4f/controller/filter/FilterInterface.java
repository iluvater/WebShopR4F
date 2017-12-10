/**
 * 
 */
package r4f.controller.filter;

/**
 * @author Ture
 *
 */
public interface FilterInterface {
	
	/**
	 * This method is implemented in all filter classes
	 * @return returns a string that can be used as a part of a SQL statement to restrict the selection
	 */
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport, String tableColor, String size);
	
	/**
	 * This method is implemented in all filter classes 
	 * @return returns the type of the filter
	 */
	public String getType();

}
