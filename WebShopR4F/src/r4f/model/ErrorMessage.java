package r4f.model;

/**
 *  Class that represents a error Message
 * @author Ture
 *
 */
public class ErrorMessage {
	private int errorCode;
	private String errorMessage;
	
	/**
	 * 
	 * @param errorCode Code of the Error Message
	 * @param errorMessage Message of the Error
	 */
	public ErrorMessage(int errorCode, String errorMessage){
		this.setErrorCode(errorCode);
		this.setErrorMessage(errorMessage);
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
