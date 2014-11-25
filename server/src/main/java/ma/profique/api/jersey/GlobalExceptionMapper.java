package ma.profique.api.jersey;

import ma.profique.util.ClientException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 */
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

	protected Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public Response toResponse(Throwable exception) {
		log.log(Level.SEVERE, "Exception: "+exception, exception);

		if (exception instanceof ClientException) {
			return ResultFactory.resultClientErrorWithCrossOrigin(new ErrorResponse(exception));
		} else {
			return ResultFactory.resultServerErrorWithCrossOrigin(new ErrorResponse(exception));
		}
	}
}
