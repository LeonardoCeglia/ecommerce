package it.objectmethod.tutorial.ecommerce.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import it.objectmethod.tutorial.ecommerce.service.JWTService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(2)
@Component
public class MyFirstFilter implements Filter {
	@Autowired
	private JWTService jwtService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpReq = (HttpServletRequest) request;
		final HttpServletResponse httpResp = (HttpServletResponse) response;

		Boolean permitted = false;
		String getUrl = httpReq.getRequestURI();
		String loginUrl = "/api/cust/login";
		String productListUrl = "/api/pro";
		String updateProductUrl = "/api/pro/updatePro";
		String addProductUrl = "/api/pro/addPro";
		if (loginUrl.equals(getUrl) || productListUrl.equals(getUrl)||addProductUrl.equals(getUrl)||updateProductUrl.equals(getUrl)) {

			log.info("chiamata libera");
			chain.doFilter(request, response);

		} else {
			log.info("Chiamata con token necesario");
			// TODO add some logic
			String token = httpReq.getHeader("auth-token");
			permitted = this.jwtService.checkJWTToken(token);
			if (permitted) {

				log.info("token valido!!");
				chain.doFilter(request, response);

			} else {

				httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);

			}
			httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

	}

}
