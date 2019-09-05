/**
 * 
 */
package com.project.usermanagement.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.usermanagement.serviceImpl.UserDetailsServiceImpl;

/**
 * @author sivasaiv
 *
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username = null;
		String jwtToken = null;
		final String requestTokenHeader = request.getHeader("Authorization");
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
		jwtToken = getJwtToken(requestTokenHeader);
		username = getUserName(jwtToken);
		}
		

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = getUserDetails(username);
			if (isTokenValid(jwtToken, userDetails)) {
				configureSpringSecurity(userDetails, request);
			}
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtToken(String requestTokenHeader) {
		return requestTokenHeader.substring(7);
	}

	private String getUserName(String jwtToken) {
		return jwtTokenUtil.getUsernameFromToken(jwtToken);
	}

	private boolean isTokenValid(String jwtToken, UserDetails userDetails) {
		return jwtTokenUtil.validateToken(jwtToken, userDetails);
	}

	private UserDetails getUserDetails(String userName) {
		return this.userDetailsService.loadUserByUsername(userName);
	}

	private void configureSpringSecurity(UserDetails userDetails, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	}
}
