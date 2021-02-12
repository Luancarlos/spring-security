package br.com.luancarlos.examplejwt.config;

import br.com.luancarlos.examplejwt.security.jwt.JwtConfigurer;
import br.com.luancarlos.examplejwt.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;

    public WebSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swagger-ui/index.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/product").permitAll()
                .antMatchers(HttpMethod.POST,"/api/product").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/product/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/api/user").permitAll()
                .antMatchers("/api/user").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/user/**").hasAnyAuthority("ROLE_ADMIN")
//                .antMatchers("/api/authenticate").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .and()
                .apply(securityConfigureAdapter());

    }

    private JwtConfigurer securityConfigureAdapter() {
        return new JwtConfigurer(tokenProvider);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetails).passwordEncoder(passwordEncoder());
////        auth.inMemoryAuthentication()
////                .withUser("user").password("{noop}user").roles("USER")
////                .and()
////                .withUser("admin").password("{noop}admin").roles("ADMIN");
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
