package co.kr.jcone.server.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			logger.debug("============ INTERCEPTOR ============");
			RequestDispatcher dispather = request.getRequestDispatcher("/loginView");
			HttpSession session = request.getSession(false);
			logger.info(request.getRequestURI());
			
			if (session == null) {
				logger.info("========== SESSION TIME OUT ==========");
				String ajaxCall = (String) request.getHeader("AJAX");
				
				if ("true".equals(ajaxCall)) {
					logger.info("AJAX CALL");
					response.sendError(901);
					return false;
				}

//				Cookie[] cookies = request.getCookies();
//
//				for (int i = 0; i < cookies.length; i++) {
//					cookies[i].setMaxAge(0);
//					response.addCookie(cookies[i]);
//				}
//				request.setAttribute("COMMAND", "SESSION_TIME_OUT");
//				dispather.forward(request, response);
				response.sendRedirect("/jcone/loginView");
				return false;
			} else {
				if (session.getAttribute("userId") == null) {
					logger.info("ID NULL");
					
					String ajaxCall = (String) request.getHeader("AJAX");
					
					if ("true".equals(ajaxCall)) {
						logger.info("AJAX CALL");
						response.sendError(901);
					} else {
//						request.setAttribute("COMMAND", "NOT_LOGIN");
//						dispather.forward(request, response);
						response.sendRedirect("/jcone/loginView");
					}
					
					return false;
				} else {
					logger.info("SESSION ALIVE");
				}
			}
		} catch (Exception e) {
			logger.error("INTERCEPTOR ERROR ", e);
		}
		return true;
	}

}
