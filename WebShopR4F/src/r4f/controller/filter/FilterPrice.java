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
public class FilterPrice implements FilterInterface {
	
	private List<int[]> prices;
	
	public FilterPrice(){
		prices = new ArrayList<int[]>();
	}

	/** 
	 * @see r4f.controller.filter.FilterInterface#getSQLFilter(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getSQLFilter(String tableArticle, String tableCategory, String tableManufacturer, String tableSport,
			String tableColor, String tableSize) {
		String sql = " ( ";

		for (int i = 0; i<prices.size(); i++) {
			int priceSpan[] = prices.get(i);
			if(priceSpan[0] == -1){
				sql = sql + tableArticle + ".price <= " +  priceSpan[1] + " ";
			}else{
				if(priceSpan[1] == -1){
					sql = sql + tableArticle + ".price >= " +  priceSpan[0] + " ";
				}else{
					sql = sql + tableArticle + ".price BETWEEN " + priceSpan[0] + " AND " + priceSpan[1] + " ";
				}
			}
			if(i != prices.size() -1 ){
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
		return "price";
	}

	/**
	 * @return the prices
	 */
	public List<int[]> getPrices() {
		return prices;
	}

	/**
	 * @param prices the prices to set
	 */
	public void setPrices(List<int[]> prices) {
		this.prices = prices;
	}

}
