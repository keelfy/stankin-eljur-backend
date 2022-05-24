package org.keelfy.eljur.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.configuration.property.SecurityProperties;
import org.keelfy.eljur.api.listener.SecurityEventPublisher;
import org.keelfy.eljur.api.rest.AccessDeniedHandlerImpl;
import org.keelfy.eljur.api.rest.AuthenticationEntryPointImpl;
import org.keelfy.eljur.api.rest.controller.CredentialsController;
import org.keelfy.eljur.api.rest.controller.InvitationController;
import org.keelfy.eljur.api.rest.controller.TokenController;
import org.keelfy.eljur.api.security.filter.AuthenticationFilter;
import org.keelfy.eljur.api.security.filter.AuthorizationFilter;
import org.keelfy.eljur.api.security.handler.AuthenticationFailureHandlerImpl;
import org.keelfy.eljur.api.security.handler.AuthenticationSuccessHandlerImpl;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.api.service.SecurityService;
import org.keelfy.eljur.api.util.RestConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] PERMITTED_GET_REQUESTS = {
            TokenController.REFRESH_TOKEN_MAPPING,
    };

    private static final String[] PERMITTED_POST_REQUESTS = {
            TokenController.REFRESH_TOKEN_MAPPING,
            RestConstants.AUTH_LOGIN_URL,
            RestConstants.AUTH_LOGOUT_URL,
            InvitationController.ACTIVATE_MAPPING,
            CredentialsController.FORGOT_PASSWORD_MAPPING,
            CredentialsController.CHANGE_FORGOTTEN_PASSWORD_MAPPING,
    };

    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    private final AccessDeniedHandlerImpl accessDeniedHandler;

    private final CredentialsService credentialsService;

    private final SecurityService securityService;

    private final SecurityProperties securityProperties;

    private final ObjectMapper objectMapper;

    private final SecurityEventPublisher securityEventPublisher;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .formLogin()
                .loginProcessingUrl(RestConstants.AUTH_LOGIN_URL)
                .usernameParameter(RestConstants.USERNAME_PARAMETER)
                .passwordParameter(RestConstants.PASSWORD_PARAMETER)
                .and()
                .logout().logoutUrl(RestConstants.AUTH_LOGOUT_URL)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, PERMITTED_POST_REQUESTS).permitAll()
                .antMatchers(HttpMethod.GET, PERMITTED_GET_REQUESTS).permitAll()
                .and()
                .addFilter(authenticationFilter())
                .addFilter(authorizationFilter())
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    private AuthenticationFilter authenticationFilter() throws Exception {
        return new AuthenticationFilter(
                authenticationManager(),
                credentialsService,
                authenticationSuccessHandler(),
                authenticationFailureHandler(),
                objectMapper
        );
    }

    private AuthorizationFilter authorizationFilter() throws Exception {
        return new AuthorizationFilter(authenticationManager(), authenticationEntryPoint)
                .setSecurityService(securityService)
                .setSecurityProperties(securityProperties)
                .setCredentialsService(credentialsService)
                .setObjectMapper(objectMapper);
    }

    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl(
                objectMapper,
                credentialsService,
                securityService,
                securityProperties,
                securityEventPublisher
        );
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl(authenticationEntryPoint);
    }

}
