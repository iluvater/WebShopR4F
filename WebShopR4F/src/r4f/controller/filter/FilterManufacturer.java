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
public class FilterManufacturer implements FilterInterface {

	private List<String> manufacturers;

	/**
	 * Constructor that creates a new lists of manufacturers
	 */
	public FilterManufacturer() {
		manufacturers = new ArrayList<String>();
	}

	/**
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter(String, String, String, String, String, String)
	 */
	@Override
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport,
			String tableColor, String tableSize) {
		String sql = "( ";

		for (int i = 0; i< manufacturers.size(); i++) {
			String string = manufacturers.get(i);
			sql = sql + tableManufacturer + ".name = \"" + string + "\"";
			if(i != manufacturers.size() -1 ){
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
		return "manufacturer";
	}

	/**
	 * @return the manufacturers
	 */
	public List<String> getManufacturers() {
		return manufacturers;
	}

	/**
	 * @param manufacturers
	 *            the manufacturers to set
	 */
	public void setManufacturers(List<String> manufacturers) {
		this.manufacturers = manufacturers;
	}

}
