package in.hp.java.libraryservice.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return isResponseStatus4xxOr5xx(response);
    }

    private boolean isResponseStatus4xxOr5xx(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (isResponseStatus4xxOr5xx(response)) {
            var responseBodyString = response.getBody().toString();
            throw new RestTemplateResponseException(response.getStatusCode(), responseBodyString,
                    responseBodyString);
        }
    }
}
