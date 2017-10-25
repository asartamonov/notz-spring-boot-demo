package ru.asartamonov.notz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/login")
public class LoginController {

	@GetMapping("/login")
	public ModelAndView getLoginPage(HttpServletRequest request, HttpServletResponse response) {
		CsrfToken _csrf = (CsrfToken) request.getAttribute("_csrf");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("_csrf", _csrf);
		return new ModelAndView("login", model);
	}

	@PostMapping("/login")
	public void authenticate(@RequestParam Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Nothing is required here as Spring Security works as Filter chain and intercepts
		// requests before they hit this Controller as configured in WebSecurityConfig extends WebSecurityConfigurerAdapter
	}

	@GetMapping("/login/out")
	public String postLogout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

}
