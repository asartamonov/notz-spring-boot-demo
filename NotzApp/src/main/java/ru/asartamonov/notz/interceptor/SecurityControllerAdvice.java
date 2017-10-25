package ru.asartamonov.notz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityControllerAdvice extends HandlerInterceptorAdapter {

	public static boolean isUserLogged() {
		try {
			return !SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser");
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView model)
			throws Exception {
		if (model != null && !isRedirectView(model) && isUserLogged()) {
			addToModelUserDetails(model);
		}
	}

	public static boolean isRedirectView(ModelAndView mv) {
		String viewName = mv.getViewName();
		
		if (viewName.startsWith("redirect:/")) {
			return true;
		}
		View view = mv.getView();
		return (view != null && view instanceof SmartView && ((SmartView) view).isRedirectView());
	}

	private void addToModelUserDetails(ModelAndView model) {
		String loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addObject("nickname", loggedUsername);
	}
}
