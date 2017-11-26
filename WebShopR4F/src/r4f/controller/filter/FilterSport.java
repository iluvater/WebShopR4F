package r4f.controller.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterSport implements FilterInterface {
	
	private List<String> sports;
	
	public FilterSport(){
		sports = new ArrayList<String>();
	}
	
	/**
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter(String, String, String, String, String)
	 */
	@Override
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport,
			String tableColor) {
		String sql = "";
		
		for (String string : sports) {
			sql = sql + " AND " + tableSport + ".name = \"" + string + "\"";
		}
		
		return sql;
	}
	
	/**
	 * @see r4f.controller.filter.FilterInterface#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "sport";
	}
	
	/**
	 * 
	 * @param sports the sport to set
	 */
	public void setSports(List<String> sports){
		this.sports = sports;
	}
	
	/**
	 * 
	 * @return the list of sports
	 */
	public List<String> getSports(){
		return sports;
	}
}
