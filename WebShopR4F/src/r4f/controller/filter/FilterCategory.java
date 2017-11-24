/**
 * 
 */
package r4f.controller.filter;

/**
 * @author Ture
 *
 */
public class FilterCategory implements FilterInterface {
	private String category;
	
	public FilterCategory(String category){
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter()
	 */
	@Override
	public String getSQLFilter(String tableName) {
		if(category == null || category.equals("")){
			return "";
		}else{
			return " AND " + tableName + ".category = \"" + category + "\"";
		}
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
