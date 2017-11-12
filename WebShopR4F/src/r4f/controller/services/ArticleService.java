/**
 * 
 */
package r4f.controller.services;

import java.util.List;

import r4f.model.Article;
import r4f.model.DatabaseConnection;

/**
 * @author Ture
 *
 */
public class ArticleService extends Service{
	
		/**
	 * This method creates a new Article in the database
	 * @param artikel article that should be created
	 * @return returns the created article returns null if the article could be created
	 */
	public Article createArtikelInDB(Article artikel){
		int id;
		
		id = super.getDbConnection().createArticleInDB(artikel);
		
		return super.getDbConnection().getArticle(id);
	}
	
	/**
	 * this method selects all article that are stored in the database
	 * @return returns a list with all article
	 */
	public List<Article> getArticleList(){
		return super.getDbConnection().getArticleList();
	}
	
	/**
	 * This method selects an article from the database
	 * @param id the id of the article that should be selected
	 * @return returns the article return null if no article was selected
	 */
	public Article getArticle(int id){
		return super.getDbConnection().getArticle(id);
	}
}
