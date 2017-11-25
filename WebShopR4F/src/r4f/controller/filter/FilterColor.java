/**
 * 
 */
package r4f.controller.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ture
 *
 */
public class FilterColor implements FilterInterface {
	
	private List<String> colors;
	
	/**
	 * Constructor that creates a new list of colors
	 */
	public FilterColor(){
		colors = new ArrayList<String>();
	}
	
	/**
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport,
			String tableColor) {
		String sql = "";

		for (String string : colors) {
			sql = sql + " AND " + tableColor + ".name = \"" + string + "\"";
		}

		return sql;
	}

	/**
	 * @see r4f.controller.filter.FilterInterface#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "color";
	}

	/**
	 * @return the colors
	 */
	public List<String> getColors() {
		return colors;
	}

	/**
	 * @param colors the colors to set
	 */
	public void setColors(List<String> colors) {
		this.colors = colors;
	}

}
