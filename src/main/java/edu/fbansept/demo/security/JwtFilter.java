package edu.fbansept.demo.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYmFuc2VwdCJ9.UdkbKzN6rHDQFWaZZ699bhLleATNvU2kt8hhAtwudNY

        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);

            System.out.println(jwt);

        }
    }
}
