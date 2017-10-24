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
	 */
	public ErrorMessage(int errorCode){
		this.setErrorCode(errorCode);
		switch (errorCode) {
		case 100:
			this.errorMessage= "Bitte geben Sie Ihren Vornamen ein.";
			break;
		case 101:
			this.errorMessage= "Während der Registrierung ist ein Fehler aufgetreten. Bitte Versuchen Sie es erneut";
			break;
		case 102:
			this.errorMessage= "Bitte Geben Sie das Datum im richtigen Format DD.MM.JJJJ oder JJJJ-MM-DD ein.";
			break;
		case 103:
			this.errorMessage= "Diese E-Mail ist bereits registriert. Melden Sie sich an.";
			break;
		case 104:
			this.errorMessage= "Bitte wählen Sie eine gültige Anrede aus.";
			break;
		case 105:
			this.errorMessage= "Bitte geben Sie eine gültige Postleitzahl ein.";
			break;
		case 106:
			this.errorMessage= "Bitte geben Sie ein gültige E-Mail ein.";
			break;
		case 107:
			this.errorMessage= "Bitte geben Sie Ihren Nachnamen ein.";
			break;
		case 108:
			this.errorMessage= "Bitte geben Sie ihre E-Mail ein.";
			break;
		case 109:
			this.errorMessage= "Bitte geben Sie ein Passwort ein.";
			break;
		case 110:
			this.errorMessage= "Bitte geben Sie eine Straße ein.";
			break;
		case 111:
			this.errorMessage= "Bitte geben Sie eine Hausnummer ein.";
			break;
		case 112:
			this.errorMessage= "Bitte geben Sie einen Ort ein.";
			break;
		case 113:
			this.errorMessage= "Bitte geben Sie ihr Geburtsdatum ein.";
			break;
		case 114:
			this.errorMessage= "Das Passwort oder die E-Mail ist flasch.";
			break;
		default:
			this.errorMessage= "Unbekannter Fehler.";
			break;
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
