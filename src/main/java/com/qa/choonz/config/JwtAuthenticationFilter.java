package com.qa.choonz.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qa.choonz.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

// adapted from javainuse.com/webseries/spring-security-jwt
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtTokenUtil;

	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
		throws ServletException, IOException {

		String jwtToken = extractJwtFromReq(req);

		if(StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)){
			UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken), "", jwtTokenUtil.getRolesFromToken(jwtToken));

			UsernamePasswordAuthenticationToken uAuthToken = new UsernamePasswordAuthenticationToken
				(userDetails, null, userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(uAuthToken);
		} else {
			System.out.println("Cannot set Security Context");
		}
		chain.doFilter(req, res);
	}

	private String extractJwtFromReq(HttpServletRequest req){
		String bearerToken = req.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
