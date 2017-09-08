package com.spring_boot2017127.demo.SecurityRepository;


import com.spring_boot2017127.demo.DaLRepository.UzerRepository;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private SSUserDetailService userDetailService;

    @Autowired
    private UzerRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean()throws Exception
    {
        return new SSUserDetailService(userRepository);
    }




    @Override
    protected void configure(HttpSecurity http)throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/","/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();


        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();

//               .authorizeRequests()
//                .antMatchers("/")
//                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .httpBasic();

//        .authorizeRequests()
//         .antMatchers("/").permitAll()
//        .anyRequest().authenticated()
//         .and()
//         .formLogin().loginPage("/login").permitAll()
//         .and()
//         .logout()
//         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//         .logoutSuccessUrl("/logout").permitAll().permitAll()
//         .and()
//         .httpBasic();
//
//        http
//                .csrf().disable();
//
//        http
//                .headers().frameOptions().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
//                .and()
//                .withUser("Dave").password("begreat").roles("ADMIN");

        auth
                .userDetailsService(userDetailsServiceBean());
    }

}
