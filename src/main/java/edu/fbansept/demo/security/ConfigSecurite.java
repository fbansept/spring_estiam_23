package edu.fbansept.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@EnableWebSecurity
public class ConfigSecurite extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource source;

    @Autowired
    private AppUserDetailsService userDetailService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailService);

//        auth.jdbcAuthentication()
//                .dataSource(source)
//                .usersByUsernameQuery("SELECT login, password, 1 FROM utilisateur WHERE login = ?")
//                .authoritiesByUsernameQuery("SELECT login, IF(admin,'ROLE_ADMIN','ROLE_USER') FROM utilisateur WHERE login = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors()
                .and().authorizeRequests()
                    .antMatchers("/connexion","/inscription").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                ;
    }

    @Bean
    public PasswordEncoder dependancePasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager dependanceAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

}
