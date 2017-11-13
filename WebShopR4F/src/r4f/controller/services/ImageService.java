/**
 * 
 */
package r4f.controller.services;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import r4f.model.Image;

/**
 * @author Ture
 *
 */
public class ImageService extends Service{

	public int createImageInDB(InputStream inputStream, String contentType){
		return super.getDbConnection().createImageInDB(inputStream, contentType);		
	}
	
	public Image getImage(int id){
		return super.getDbConnection().getImage(id);		
	}

}
