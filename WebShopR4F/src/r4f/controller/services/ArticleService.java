/**
 * 
 */
package r4f.controller.services;

import java.sql.SQLException;
import java.util.List;

import r4f.controller.filter.FilterList;
import r4f.model.Article;

/**
 * @author Ture
 *
 */
public class ArticleService extends Service{
	
		/**
	 * This method creates a new Article in the database
	 * @param article article that should be created
	 * @return returns the created article returns null if the article could be created
	 */
	public Article createArtikelInDB(Article article){
		int id;
		
		id = super.getDbConnection().createArticleInDB(article);
		for (String color : article.getColor()) {
			super.getDbConnection().createColorMapping(color, id);
		}
		for(Integer size : article.getSize()){
			super.getDbConnection().createSizeMapping(size, id);
		}
		
		return super.getDbConnection().getArticle(id);
	}
	
	/**
	 * this method selects all article that are stored in the database
	 * @return returns a list with all article
	 */
	public List<Article> getArticleList(FilterList filter){
		return super.getDbConnection().getArticleList(filter);
	}
	
	/**
	 * This method selects an article from the database
	 * @param id the id of the article that should be selected
	 * @return returns the article return null if no article was selected
	 */
	public Article getArticle(int id){
		return super.getDbConnection().getArticle(id);
	}
	
	/**
	 * this method updates an article in the database
	 * @param article the article that should be update with all new values
	 */
	public void updateArticleInDB(Article article) {
		try {
			super.getDbConnection().updateArticleInDB(article);
			super.getDbConnection().deleteColorMapping(article);
			super.getDbConnection().deleteSizeMapping(article);
			for (String color : article.getColor()) {
				super.getDbConnection().createColorMapping(color, article.getId());
			}
			for(Integer size : article.getSize()){
				super.getDbConnection().createSizeMapping(size, article.getId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	/**
	 * This method updates an article in the database
	 * @param article the article that should be updated it should already contain the new values
	 * @return return true if no error occurred during the update
	 */
	public boolean updateArticleinDB(Article article) {
		try{
			super.getDbConnection().updateArticleInDB(article);
			super.getDbConnection().deleteColorMapping(article);
			super.getDbConnection().deleteSizeMapping(article);
			for (String color : article.getColor()) {
				super.getDbConnection().createColorMapping(color, article.getId());
			}
			for(Integer size : article.getSize()){
				super.getDbConnection().createSizeMapping(size, article.getId());
			}
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Article> getArticleListSearch(String searchPattern) {
		return super.getDbConnection().getArticleListSearch(searchPattern);
	}
}
