package cn.leadeon.common;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpServletResponseContentWrapper extends
		HttpServletResponseWrapper {

	protected ByteArrayServletOutputStream buffer;
	protected PrintWriter bufferWriter;
	protected boolean committed = false;

	public HttpServletResponseContentWrapper(HttpServletResponse response) {
		super(response);
		buffer = new ByteArrayServletOutputStream();
	}

	public void flushWrapper() throws IOException {
		if (bufferWriter != null)
			bufferWriter.close();
		if (buffer != null)
			buffer.close();
		byte[] content = wrap(buffer.toByteArray());
		getResponse().setContentLength(content.length);
		getResponse().getOutputStream().write(content);
		getResponse().flushBuffer();
		committed = true;
	}

	public byte[] wrap(byte[] content) throws IOException {
		return content;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return buffer;
	}

	/**
	 * The default behavior of this method is to return getWriter() on the
	 * wrapped response object.
	 */

	@Override
	public PrintWriter getWriter() throws IOException {
		if (bufferWriter == null) {
			bufferWriter = new PrintWriter(new OutputStreamWriter(buffer, this
					.getCharacterEncoding()));
		}
		return bufferWriter;
	}

	@Override
	public void setBufferSize(int size) {
		buffer.enlarge(size);
	}

	@Override
	public int getBufferSize() {
		return buffer.size();
	}

	@Override
	public void flushBuffer() throws IOException {
	}

	@Override
	public boolean isCommitted() {
		return committed;
	}

	@Override
	public void reset() {
		getResponse().reset();
		buffer.reset();
	}

	@Override
	public void resetBuffer() {
		getResponse().resetBuffer();
		buffer.reset();
	}

}
