package br.com.rsbdev.starter.tenant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.rsbdev.starter.security.JwtService;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private JwtService jwtService;

	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Value("${jwt.prefix}")
	private String tokenPrefix;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String authToken =  request.getHeader(tokenHeader);
		
		if (authToken == null || !authToken.startsWith(tokenPrefix)) {
			return true;
		}
		
		if(tokenHeader!=null) {
			String jwt = extractToken(authToken);
			String tenantId = jwtService.getTokenTenant(jwt);
			
			TenantContext.setCurrentTenant(tenantId);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		TenantContext.clear();
	}
	
	private String extractToken(String token) {
		return token.substring(tokenPrefix.length());
	}

}
