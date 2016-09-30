package com.ly.test.helloWorld.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class JSONUtils {

	public static String parseRequest(HttpServletRequest request) throws IOException {
		String jsonStr = null;
		int contentLength = request.getContentLength();
		if (contentLength > 0) {
			byte[] buffer = new byte[contentLength];
			for (int i = 0; i < contentLength;) {
				int readlen = request.getInputStream().read(buffer, i, contentLength - i);
				if (readlen == -1) {
					break;
				}
				i += readlen;
			}
			String charEncoding = request.getCharacterEncoding();
			if (charEncoding == null) {
				charEncoding = "UTF-8";
			}
			jsonStr = new String(buffer, charEncoding);
		}
		return jsonStr;
	}
}
