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

//	private final String loginUrl = "/api/cust/login";
//	private final String productListUrl = "/api/pro";
//	private final String updateProductUrl = "/api/pro/updatePro";
//	private final String addProductUrl = "/api/pro/addPro";
//	private final String addSupplierUrl = "/api/supplier/add-new-supplier";
//	private final String getSupplierUrl = "/api/supplier/get-supp";
//	private final List<String> allowedUrls = Arrays.asList(loginUrl, productListUrl, updateProductUrl, addProductUrl, addSupplierUrl,getSupplierUrl);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest httpReq = (HttpServletRequest) request;
		final HttpServletResponse httpResp = (HttpServletResponse) response;

		Boolean permitted = false;
		String url = httpReq.getRequestURI();
		String method = httpReq.getMethod();

		if (url.contains("/api")) {
//			log.info("chiamata libera");
//			if (url.endsWith("cust/login") 
//					|| url.endsWith("pro/updatePro") 
//					|| url.endsWith("pro/addPro")
//					|| url.endsWith("supplier/add-new-supplier") && "GET".equals(method)) {
//
//				chain.doFilter(request, response);
//
//			}
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
		}

	}

}
