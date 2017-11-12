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

	public boolean createImageInDB(Part part){
		
		InputStream inputStream;
		try {
			inputStream = part.getInputStream();
			return super.getDbConnection().createImageInDB(inputStream, part.getContentType());		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	public Image getImage(int id){
		return super.getDbConnection().getImage(id);		
	}

}
