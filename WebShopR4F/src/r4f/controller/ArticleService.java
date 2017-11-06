/**
 * 
 */
package r4f.controller;

import r4f.model.Article;
import r4f.model.DatabaseConnection;

/**
 * @author Ture
 *
 */
public class ArticleService {
	
	DatabaseConnection dbVerbindung;
	
	/**
	 * Constructor that establishes a connection to the database
	 */
	public ArticleService(){
		dbVerbindung = new DatabaseConnection(); 
	}
	
	/**
	 * This method creates a new Article in the database
	 * @param artikel article that should be created
	 * @return returns the created article returns null if the article could be created
	 */
	public Article createArtikelInDB(Article artikel){
		int id;
		
		id = dbVerbindung.createArticleInDB(artikel);
		
		return dbVerbindung.getArticle(id);
	}
}
