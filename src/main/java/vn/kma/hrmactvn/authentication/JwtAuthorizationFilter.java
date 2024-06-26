package vn.kma.hrmactvn.authentication;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.kma.hrmactvn.constant.ApplicationConstant;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        log.info(request.getRequestURI());

        String mock = request.getHeader("mock");
        if (!StringUtils.hasText(mock) && (request.getMethod().equals("POST") || request.getMethod()
                .equals("PUT") || request.getMethod().equals("DELETE"))) {
            handleUnauthorized(response);
            return;
        }

        if ((request.getMethod().equals("POST") || request.getMethod()
                .equals("PUT") || request.getMethod().equals("DELETE")) && !Objects.equals(mock, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MTgxODk3MTgsImV4cCI6MTc0OTcyNTcxOCwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoibWFuaGR6YXBwbGVJZCIsImVtYWlsIjoiZHV5bWFuaGttYUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6InRydWUifQ.bYDeuYgdRgQW_Dci_5b89cYffOqr8Wiq4MBwKVi_9_Q")) {
            handleUnauthorized(response);
            return;
        }

        boolean isDisableAuthorize = true;
        for (String check : ApplicationConstant.SECURITY_CONFIG.DISABLE_AUTHORIZE) {
            isDisableAuthorize = new AntPathMatcher().match(check,
                    request.getRequestURI().substring(request.getContextPath().length()));
            if (isDisableAuthorize) {
                break;
            }
        }
        try {
//      if (isDisableAuthorize) {
//        filterChain.doFilter(request, response);
//        return;
//      } else {
//          String accessToken = jwtUtil.resolveToken(request);
//          if (accessToken == null) {
//              handleUnauthorized(response);
//              return;
//          }
//        Claims claims = jwtUtil.resolveClaims(request);
//        if (claims != null & jwtUtil.validateClaims(claims)) {
//          UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
//          if (userDetails == null) {
//            handleUnauthorized(response);
//            return;
//          }
//          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//              userDetails, userDetails, userDetails.getAuthorities());
//          authenticationToken.setDetails(
//              new WebAuthenticationDetailsSource().buildDetails(request));
//          SecurityContext context = SecurityContextHolder.createEmptyContext();
//          context.setAuthentication(authenticationToken);
//          SecurityContextHolder.setContext(context);
//        } else {
//          handleUnauthorized(response);
//          return;
//        }
//      }
            filterChain.doFilter(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            handleUnauthorized(response);
            return;
        }
//    filterChain.doFilter(request, response);
    }


    private HttpServletResponse handleUnauthorized(HttpServletResponse response) throws IOException {
        SecurityContextHolder.clearContext();
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter()
                .write("Full authentication is required to access this resource unauthorized");
        return response;
    }
}
