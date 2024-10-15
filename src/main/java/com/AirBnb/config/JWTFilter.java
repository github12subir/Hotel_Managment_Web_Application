package com.AirBnb.config;

import com.AirBnb.entity.AppUser;
import com.AirBnb.repository.AppUserRepository;
import com.AirBnb.service.Impl.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token!=null && token.startsWith("Bearer ")){
            String tokenVal = token.substring(7);

            System.err.println(tokenVal);
            String username = jwtService.getUserName(tokenVal);
            System.err.println(username);
            Optional<AppUser> opUser = appUserRepository.findByUsername(username);
            if(opUser.isPresent()){
                AppUser appUser = opUser.get();
                //set Principle->User(user&pass) & Role
                UsernamePasswordAuthenticationToken
                        auth=new UsernamePasswordAuthenticationToken(appUser,null,Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
                System.err.println(auth);
                auth.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            System.out.println(username);

        }

        filterChain.doFilter(request,response);
    }
}
