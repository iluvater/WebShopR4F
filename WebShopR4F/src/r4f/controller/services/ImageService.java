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
	 * @return returns the id of the created image
	 */
	public int createImageInDB(InputStream inputStream, String contentType, boolean mainImage){
		return super.getDbConnection().createImageInDB(inputStream, contentType, mainImage);		
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
	 * @param imageid the id of the image
	 * @param imageStream the new content
	 * @param imageType the new content type
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
