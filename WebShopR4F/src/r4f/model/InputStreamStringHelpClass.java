/**
 * 
 */
package r4f.model;

import java.io.InputStream;

/**
 * @author Ture
 *	This is a class that is used as a help class to map an input stream and a string of his content type
 */
public class InputStreamStringHelpClass {
	private InputStream inputStream;
	private String imageType;
	
	/**
	 * Constructor for general use
	 * @param inputStream the input stream to set
	 * @param imageType the imagetype to set
	 */
	public InputStreamStringHelpClass(InputStream inputStream, String imageType){
		this.inputStream = inputStream;
		this.imageType = imageType;
	}
	
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	/**
	 * @return the imageType
	 */
	public String getImageType() {
		return imageType;
	}
	/**
	 * @param imageType the imageType to set
	 */
	public void setImageType(String contentType) {
		this.imageType = contentType;
	}
	

}
