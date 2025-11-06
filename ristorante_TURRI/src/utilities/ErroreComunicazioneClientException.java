package utilities;

public class ErroreComunicazioneClientException extends Exception{
	private static final long serialVersionUID = 1L;

	public ErroreComunicazioneClientException() {
		super("Errore nella comunicazione con il client");
	}
}