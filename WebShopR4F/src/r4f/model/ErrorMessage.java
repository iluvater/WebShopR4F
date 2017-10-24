package r4f.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
		case 115:
			this.errorMessage= "Bitte geben Sie einen Preis ein.";
			break;
		case 116:
			this.errorMessage= "Bitte geben Sie eine Größe ein.";
			break;
		case 117:
			this.errorMessage= "Bitte geben Sie eine Bezeichnung ein.";
			break;
		case 118:
			this.errorMessage= "Bitte geben Sie eine Beschreibung ein.";
			break;
		case 119:
			this.errorMessage= "Bitte geben Sie einen Hersteller ein.";
			break;
		case 120:
			this.errorMessage= "Bitte geben Sie eine Farbe ein.";
			break;
		case 121:
			this.errorMessage= "Bitte geben Sie eine Kategorie ein.";
			break;
		case 122:
			this.errorMessage= "Es ist ein Fehler während der Artikelerfassung aufgetreten. Bitte versuchen Sie es später erneut.";
			break;
		default:
			this.errorMessage= "Unbekannter Fehler.";
			break;
		}
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("./WebContent/erros.ini")));
		String zeile="";
		
		
		try {
			while((zeile= bReader.readLine() )!= null){
				String split[] = zeile.split(";");
				if(split[0].equals(Integer.toString(errorCode))){
					
				}
			}
		} catch (IOException e) {
			
			this.errorMessage="Fehlermeldungen konnten nicht geladen werden.";
		}
		
	}
	
	private ErrorMessage(int errorCode, String errorMessage){
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
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
