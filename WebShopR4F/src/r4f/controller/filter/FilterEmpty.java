/**
 * 
 */
package r4f.controller.filter;

/**
 * @author Ture
 * This filter class can be used if there is no filter needed
 */
public class FilterEmpty implements FilterInterface {

	/* (non-Javadoc)
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter(java.lang.String)
	 */
	@Override
	public String getSQLFilter(String tableName) {
		return "";
	}

}
