package com.info.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAppUserDetailsService myAppUserDetailsService;

    @Autowired
    private AdminUserDetailsService adminUserDetailsService;

    @Autowired
    private StaffDetailsService staffDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/serviceRegistration").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/rest/**").permitAll()
                .antMatchers("/service/**").permitAll()
                .antMatchers("/teamleader/edit").hasAuthority("TEAM_LEADER")
                .antMatchers("/search").hasAuthority("ADMIN")
                .antMatchers("/rest/getall").hasAuthority("ADMIN")
                .antMatchers("/search/all").hasAuthority("ADMIN")
                .antMatchers("/admin/search/all").hasAuthority("ADMIN")
                .antMatchers("/teamleader/getFile").hasAuthority("TEAM_LEADER")
                .antMatchers("/admin/getFile").hasAuthority("ADMIN")
                .antMatchers("/registration").permitAll()
                .antMatchers("/teamleaderRegistration").permitAll()
                .antMatchers("/teamleader/**").hasAuthority("TEAM_LEADER")
                .antMatchers("/addteammember/**").hasAuthority("TEAM_LEADER")
                .antMatchers("/admin/edit").hasAuthority("ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/default")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(adminUserDetailsService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(staffDetailsService).passwordEncoder(passwordEncoder);
    }
}
