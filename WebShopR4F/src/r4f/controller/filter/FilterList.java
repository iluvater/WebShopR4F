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
public class FilterList {
	private List<FilterInterface> filters;
	
	public FilterList(){
		filters = new ArrayList();
	}

	/**
	 * @return the filters
	 */
	public List<FilterInterface> getFilters() {
		return filters;
	}

	/**
	 * @param filters the filters to set
	 */
	public void setFilters(List<FilterInterface> filters) {
		this.filters = filters;
	}
	
	/**
	 * This creates the where clause for the selection of the articles
	 * @param tableArticle the name of the table
	 * @param tableCategory the name of the table category
	 * @param tableManufacturer the name of the table manufacturer
	 * @param tableSport the name of the table sport
	 * @param tableColor the name of the table color
	 * @return the where clause specified by this filter
	 */
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport, String tableColor){
		String filter = "";
		for (FilterInterface filterInterface : filters) {
			filter = filter + filterInterface.getSQLFilter(tableArticle, tableCategory, tableManufacturer, tableSport, tableColor);
		}
		return filter;
	}
	
	/**
	 * This method removes a filter from the list filters
	 * @param filterName the name of the filter that should be removed
	 */
	public void removeFilter(String filterName) {
		for (FilterInterface filterInterface : filters) {
			if(filterInterface.getType().equals(filterName)){
				filters.remove(filterInterface);
			}
		}
	}
	
	/**
	 * This method gets the Filter of a type
	 * @param filterName the name of the filter
	 * @return the filter with this type 
	 */
	public FilterInterface getFilter(String filterName){
		for (FilterInterface filterInterface : filters) {
			if(filterInterface.getType().equals(filterName)){
				return filterInterface;
			}
		}
		return null;
	}
}
