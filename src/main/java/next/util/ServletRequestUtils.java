package next.util;

import javax.servlet.ServletRequest;

public class ServletRequestUtils {

	public static String getParameter(ServletRequest request, String name) {
		return request.getParameter(name);
	}

	public static long getLongParameter(ServletRequest request, String name) {
		String value = getParameter(request, name);
		if (value == null) {
			return 0;
		}
		return Long.parseLong(getParameter(request, name));
	}
	
	public static String getRequiredParameter(ServletRequest request, String name) {
		String value = getParameter(request, name);
		if (value == null) {
			throw new NullPointerException();
		}
		return value;
	}
}
