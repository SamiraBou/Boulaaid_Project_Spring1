package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.w3c.dom.ls.LSOutput;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {// Pour spécifier ou sont lus users et la strategie: db ou xml ou ...
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        BCryptPasswordEncoder bcpe=getBCPE();
        /*auth.inMemoryAuthentication().withUser("admin").password(bcpe.encode("1234")).roles("ADMIN", "USER");
        auth.inMemoryAuthentication().withUser("user").password(bcpe.encode("1234")).roles( "USER");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());*/
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal, password as credentials, active from user where username=?")
                .authoritiesByUsernameQuery("select username as principal, role from users_roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(getBCPE());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
}
