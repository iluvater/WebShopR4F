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

	/**
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter()
	 */
	@Override
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport, String tableColor, String tableSize) {
		if(category == null || category.equals("")){
			return "";
		}else{
			return " " + tableCategory + ".name = \"" + category + "\"";
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
	
	/**
	 * @see r4f.controller.filter.FilterInterface#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "category";
	}

}
