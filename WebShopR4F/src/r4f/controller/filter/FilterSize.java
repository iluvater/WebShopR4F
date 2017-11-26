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
public class FilterSize implements FilterInterface {
	
	private List<Integer> sizes;
	
	public FilterSize(){
		sizes = new ArrayList<Integer>();
	}
	
	/**
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport,
			String tableColor) {
		String sql = "";

		for (int size : sizes) {
			sql = sql + " AND " + tableArticle + ".size = \"" + size + "\"";
		}

		return sql;
	}

	/**
	 * @see r4f.controller.filter.FilterInterface#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "sze";
	}

	/**
	 * @return the sizes
	 */
	public List<Integer> getSizes() {
		return sizes;
	}

	/**
	 * @param sizes the sizes to set
	 */
	public void setSizes(List<Integer> sizes) {
		this.sizes = sizes;
	}

}
