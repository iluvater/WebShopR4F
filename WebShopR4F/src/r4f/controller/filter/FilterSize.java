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
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter(String, String, String, String, String, String)
	 */
	@Override
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport,
			String tableColor, String tableSize) {
		String sql = "( ";

		for (int i = 0; i< sizes.size(); i++) {
			Integer size = sizes.get(i);
			sql = sql + tableSize + ".size = \"" + size + "\"";
			if(i != sizes.size() -1 ){
				sql = sql + " OR ";
			}
		}
		sql = sql + " ) ";

		return sql;
	}

	/**
	 * @see r4f.controller.filter.FilterInterface#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "size";
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
