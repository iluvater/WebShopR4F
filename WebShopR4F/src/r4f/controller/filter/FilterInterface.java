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
	 * @param tableArticle name of the table article
	 * @param tableCategory name of the table category
	 * @param tableManufacturer name of the table manufacturer
	 * @param tableSport name of the table sport
	 * @param tableColor name of the table color
	 * @param tableSize name of the table size
	 * @return returns a string that can be used as a part of a SQL statement to restrict the selection
	 */
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport, String tableColor, String tableSize);
	
	/**
	 * This method is implemented in all filter classes 
	 * @return returns the type of the filter
	 */
	public String getType();

}
