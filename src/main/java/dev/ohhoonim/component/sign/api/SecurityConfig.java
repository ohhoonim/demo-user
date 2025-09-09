package dev.ohhoonim.component.sign.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import dev.ohhoonim.component.sign.activity.BearerTokenActivity;
import dev.ohhoonim.component.sign.activity.port.AuthorityPort;
import dev.ohhoonim.component.sign.api.filter.BearerAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final BearerTokenActivity bearerTokenService;
	private final AuthorityPort authorityPort;

	SecurityConfig(BearerTokenActivity bearerTokenService,
			AuthorityPort authorityPort) {
		this.bearerTokenService = bearerTokenService;
		this.authorityPort = authorityPort;
	}

	@Bean
	SecurityFilterChain securityFitlerChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> {
					auth
							.requestMatchers("/sign/in",
									"/sign/out",
									"/sign/refresh",
									"/sign/callback/**")
							.permitAll()
							.anyRequest().authenticated();
				})
				.logout(logout -> logout
						.logoutUrl("/sign/logout"))
				.sessionManagement(s -> s
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterAfter(new BearerAuthenticationFilter(
						bearerTokenService, authorityPort), LogoutFilter.class);

		return http.build();
	}
}
