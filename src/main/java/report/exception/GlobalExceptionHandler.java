package report.exception;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import javax.json.bind.JsonbException;
import javax.ws.rs.core.Response;

public class GlobalExceptionHandler {

    @ServerExceptionMapper
    public Response handleJsonbException(JsonbException e ){
        ApiError error =
                new ApiError.Builder(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage())
                        .build();
        return Response.serverError()
                .header("X-Error-", Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }

    @ServerExceptionMapper
    public Response handleDatePatternException(DateFormatException dateFormatException) {
        ApiError error =
                new ApiError.Builder(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), dateFormatException.getMessage())
                        .build();
        return Response.serverError()
                .header("X-Error-", Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }

    @ServerExceptionMapper
    public Response UnhandledOperationException(UnhandledOperationException unhandledOperationException) {
        ApiError error =
                new ApiError.Builder(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), unhandledOperationException.getMessage())
                        .build();
        return Response.serverError()
                .header("X-Error-", Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}
