package pantryPal.client.View;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.*;

/**
 * MockHttpExchange
 */
public class MockHttpExchange extends HttpExchange {
    URI mockURI;
    String mockRequestMethod;

    MockHttpExchange(URI mockURI,  String mockRequestMethod) {
        this.mockURI = mockURI;
        this.mockRequestMethod = mockRequestMethod;
    }

	@Override
	public Headers getRequestHeaders() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getRequestHeaders'");
	}

	@Override
	public Headers getResponseHeaders() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getHttpContext'");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'close'");
	}

	@Override
	public InputStream getRequestBody() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getRequestBody'");
	}

	@Override
	public OutputStream getResponseBody() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getResponseBody'");
	}

	@Override
	public void sendResponseHeaders(int rCode, long responseLength) throws IOException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'sendResponseHeaders'");
	}

	@Override
	public InetSocketAddress getRemoteAddress() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getRemoteAddress'");
	}

	@Override
	public int getResponseCode() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getResponseCode'");
	}

	@Override
	public InetSocketAddress getLocalAddress() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getLocalAddress'");
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getProtocol'");
	}

	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getAttribute'");
	}

	@Override
	public void setAttribute(String name, Object value) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setAttribute'");
	}

	@Override
	public void setStreams(InputStream i, OutputStream o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setStreams'");
	}

	@Override
	public HttpPrincipal getPrincipal() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getPrincipal'");
	}
}