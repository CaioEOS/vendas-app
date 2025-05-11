package com.Caio.vendas_app.Security;

import com.Caio.vendas_app.Service.PessoaUserDetailsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final PessoaUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String token;
        final String username;

        System.out.println("Authorization Header: " + authHeader); // Log do cabeçalho

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Cabeçalho Authorization ausente ou inválido");
            chain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);
        System.out.println("Token: " + token); // Log do token

        try {
            username = jwtService.getUsername(token);
            System.out.println("Username extraído: " + username); // Log do username

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) { // Adicione validação
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("Autenticação bem-sucedida para: " + username);
                } else {
                    System.out.println("Token inválido para: " + username);
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token inválido");
                    return;
                }
            } else {
                System.out.println("Username nulo ou autenticação já existe");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar token: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Erro ao processar token: " + e.getMessage());
            return;
        }

        chain.doFilter(request, response);
    }
}