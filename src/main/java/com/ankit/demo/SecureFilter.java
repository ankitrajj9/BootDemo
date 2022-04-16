/*
 * package com.ankit.demo;
 * 
 * import java.io.IOException;
 * 
 * import javax.servlet.Filter; import javax.servlet.FilterChain; import
 * javax.servlet.ServletException; import javax.servlet.ServletRequest; import
 * javax.servlet.ServletResponse; import javax.servlet.http.HttpServletRequest;
 * import javax.servlet.http.HttpSession;
 * 
 * import org.springframework.core.annotation.Order; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.client.HttpServerErrorException;
 * 
 * @Component
 * 
 * @Order(1) public class SecureFilter implements Filter{
 * 
 * @Override public void doFilter(ServletRequest request, ServletResponse
 * response, FilterChain chain) throws IOException, ServletException {
 * HttpServletRequest req = (HttpServletRequest) request;
 * if(req.getRequestURI().equalsIgnoreCase("/home") ||
 * req.getRequestURI().equalsIgnoreCase("/") ||
 * req.getRequestURI().contains("/images") ||
 * req.getRequestURI().contains("/login")) {
 * System.out.println("Without session permit : "+req.getRequestURI());
 * System.out.println("With session permit : "+req.getRequestURL());
 * chain.doFilter(request, response); } else { HttpSession session =
 * req.getSession(); SessionBean sessionBean = (SessionBean)
 * session.getAttribute("sessionBean");
 * System.out.println("With session permit : "+req.getRequestURI());
 * System.out.println("With session permit : "+req.getRequestURL());
 * if(sessionBean != null) { chain.doFilter(request, response); } }
 * 
 * }
 * 
 * }
 */