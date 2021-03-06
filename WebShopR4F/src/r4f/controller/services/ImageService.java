/**
 * 
 */
package r4f.controller.services;

import java.io.InputStream;
import java.sql.SQLException;

import r4f.model.Image;

/**
 * @author Ture
 *
 */
public class ImageService extends Service{
	
	/**
	 * This method creates an image in the database
	 * @param inputStream The input stream with the content of the image
	 * @param contentType the content type of the image
	 * @param mainImage indicates whether an images is the main image of an article or not
	 * @param articleId the id of the article who�s image this is
	 * @return returns the id of the created image
	 */
	public int createImageInDB(InputStream inputStream, String contentType, boolean mainImage, int articleId){
		return super.getDbConnection().createImageInDB(inputStream, contentType, mainImage, articleId);		
	}
	
	/**
	 * This method selects an image from the database
	 * @param id the id of the image that should be selected
	 * @return returns the image
	 */
	public Image getImage(int id){
		return super.getDbConnection().getImage(id);		
	}
	
	/**
	 * This method updates an image in the database
	 * @param imageId the id of the image
	 * @param imageStream the new content
	 * @param imageType the new content type
	 * @param mainImage indicates whether an image is the mainImage of an article or not
	 * @return returns true if no error occurred during the update
	 */
	public boolean updateImageInDB(int imageId, InputStream imageStream, String imageType, boolean mainImage) {
		try{
			super.getDbConnection().updateImageInDB(imageId, imageStream, imageType, mainImage);
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

}
