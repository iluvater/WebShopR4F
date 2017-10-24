package r4f.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  Class that represents a error Message
 * @author Ture
 *
 */
public class ErrorMessage {
	private int errorCode;
	private String errorMessage;
	
	/**
	 * this Constructor selects the errorMessage of the errorCode from the file errors.conf. 
	 * @param errorCode Code of the Error Message
	 */
	public ErrorMessage(int errorCode){
		this.setErrorCode(errorCode);

		try {
			BufferedReader bReader = new BufferedReader(new InputStreamReader(ErrorMessage.class.getResourceAsStream("errors.conf")));
			String zeile="";	
			
			boolean temp = true;
			while((zeile= bReader.readLine() )!= null && temp){
				String split[] = zeile.split(";");
				if(split[0].equals(Integer.toString(errorCode))){
					this.errorMessage=split[1];
					temp = false;
				}
			}
			bReader.close();
			if(temp){
				this.errorMessage="Unbekannter Fehler";
			}
		} catch (IOException e) {
			
			this.errorMessage="Fehlermeldungen konnten nicht geladen werden.";
		}
		
	}
	
	
	public ErrorMessage(){
		this.errorMessage=null;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
