/**
 * MockHttpExchange mocks the HttpExchange class, for our custom exchanges.
 */
package pantryPal.server;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import com.sun.net.httpserver.*;

public class MockHttpExchange extends HttpExchange {
    URI mockURI;
    String mockRequestMethod;
	String info;

    public MockHttpExchange(String q, String mockRequestMethod) throws URISyntaxException {
        this.mockURI = new URI(null, null, null, -1, null, q, null);
        this.mockRequestMethod = mockRequestMethod;
    }

	@Override
	public Headers getRequestHeaders() {
		throw new UnsupportedOperationException("Unimplemented method 'getRequestHeaders'");
	}

	@Override
	public Headers getResponseHeaders() {
		throw new UnsupportedOperationException("Unimplemented method 'getResponseHeaders'");
	}

	@Override
	public URI getRequestURI() {
        return this.mockURI;
	}

	@Override
	public String getRequestMethod() {
		return mockRequestMethod;
	}

	@Override
	public HttpContext getHttpContext() {
		throw new UnsupportedOperationException("Unimplemented method 'getHttpContext'");
	}

	@Override
	public void close() {
		throw new UnsupportedOperationException("Unimplemented method 'close'");
	}

	@Override
	public InputStream getRequestBody() {
		InputStream is = new ByteArrayInputStream(info.getBytes());
		return is;
	}

	public void setInfo(String info) {
		this.info=info;
	}

	@Override
	public OutputStream getResponseBody() {
		throw new UnsupportedOperationException("Unimplemented method 'getResponseBody'");
	}

	@Override
	public void sendResponseHeaders(int rCode, long responseLength) throws IOException {
		throw new UnsupportedOperationException("Unimplemented method 'sendResponseHeaders'");
	}

	@Override
	public InetSocketAddress getRemoteAddress() {
		throw new UnsupportedOperationException("Unimplemented method 'getRemoteAddress'");
	}

	@Override
	public int getResponseCode() {
		throw new UnsupportedOperationException("Unimplemented method 'getResponseCode'");
	}

	@Override
	public InetSocketAddress getLocalAddress() {
		throw new UnsupportedOperationException("Unimplemented method 'getLocalAddress'");
	}

	@Override
	public String getProtocol() {
		throw new UnsupportedOperationException("Unimplemented method 'getProtocol'");
	}

	@Override
	public Object getAttribute(String name) {
		throw new UnsupportedOperationException("Unimplemented method 'getAttribute'");
	}

	@Override
	public void setAttribute(String name, Object value) {
		throw new UnsupportedOperationException("Unimplemented method 'setAttribute'");
	}

	@Override
	public void setStreams(InputStream i, OutputStream o) {
		throw new UnsupportedOperationException("Unimplemented method 'setStreams'");
	}

	@Override
	public HttpPrincipal getPrincipal() {
		throw new UnsupportedOperationException("Unimplemented method 'getPrincipal'");
	}
}