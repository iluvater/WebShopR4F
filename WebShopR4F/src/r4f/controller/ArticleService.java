/**
 * 
 */
package r4f.controller;

import java.util.List;

import r4f.model.Article;
import r4f.model.DatabaseConnection;

/**
 * @author Ture
 *
 */
public class ArticleService {
	
	DatabaseConnection dbConnection;
	
	/**
	 * Constructor that establishes a connection to the database
	 */
	public ArticleService(){
		dbConnection = new DatabaseConnection(); 
	}
	
	/**
	 * This method creates a new Article in the database
	 * @param artikel article that should be created
	 * @return returns the created article returns null if the article could be created
	 */
	public Article createArtikelInDB(Article artikel){
		int id;
		
		id = dbConnection.createArticleInDB(artikel);
		
		return dbConnection.getArticle(id);
	}
	
	/**
	 * this method selects all article that are stored in the database
	 * @return returns a list with all article
	 */
	public List<Article> getArticleList(){
		return dbConnection.getArticleList();
	}
}
