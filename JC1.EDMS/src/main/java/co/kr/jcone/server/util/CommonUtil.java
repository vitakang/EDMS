package co.kr.jcone.server.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	public static Map<String, Object> getMap(HttpServletRequest request) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Enumeration enums = request.getParameterNames();

		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);

			// Parameter가 배열일 경우
			if (parameters.length > 1) {
				List<Object> parmList = new ArrayList<Object>();

				for (int i = 0; i < parameters.length; i++) {
					parmList.add(parmList.size(), parameters[i]);
				}

				parameterMap.put(paramName, parmList);
				// Parameter가 배열이 아닌 경우
			} else {
				parameterMap.put(paramName, parameters[0]);
			}
		}

		return parameterMap;
	}
}
