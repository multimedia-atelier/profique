package ma.profique.api.jersey;

public class ErrorResponse {

	private String errorMessage = null;

	public ErrorResponse(Throwable throwable) {
		if (throwable != null) {
			errorMessage = throwable.toString();
		}
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
