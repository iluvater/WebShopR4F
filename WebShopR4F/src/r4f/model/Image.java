/**
 * 
 */
package r4f.model;

import java.sql.Blob;

/**
 * @author Ture
 *	Class that represents an image that is stored in the database
 */
public class Image {
	
	private int id;
	private Blob imageBlob;
	private String type;
	private boolean mainImage;
	
	
	/**
	 * This constructor is for creating an Image that is already stored in the database
	 * @param id id to set
	 * @param imageBlob imageBlob to set
	 * @param type type to set
	 * @param mainImage indicates whether an image is the main image of an article or not
	 */
	public Image(int id, Blob imageBlob, String type, boolean mainImage) {
		this.setId(id);
		this.setImageBlob(imageBlob);
		this.setType(type);
		this.mainImage = mainImage;
	}
	
	/**
	 * This constructor is for creating an Image that is already stored in the database
	 * @param imageBlob imageBlob to set
	 * @param type type to set
	 */
	public Image( Blob imageBlob, String type) {
		this.setImageBlob(imageBlob);
		this.setType(type);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the imageBlob
	 */
	public Blob getImageBlob() {
		return imageBlob;
	}

	/**
	 * @param imageBlob the imageBlob to set
	 */
	public void setImageBlob(Blob imageBlob) {
		this.imageBlob = imageBlob;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the mainImage
	 */
	public boolean isMainImage() {
		return mainImage;
	}

	/**
	 * @param mainImage the mainImage to set
	 */
	public void setMainImage(boolean mainImage) {
		this.mainImage = mainImage;
	}

}
