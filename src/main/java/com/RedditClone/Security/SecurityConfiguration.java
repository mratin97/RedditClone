package com.RedditClone.Security;

import com.RedditClone.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {



    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {

        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService).passwordEncoder(
                        passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();//BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean()
            throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter
                .setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOriginPatterns("*").allowedHeaders("*").allowedMethods("GET" , "POST" , "PUT" , "DELETE" , "OPTIONS" , "HEAD");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/api/login", "/styles.css", "/profile",
                        "/runtime.js", "/polyfills.js", "/vendor.js", "/main.js", "/images/**", "/js/**",
                        "/runtime-es2015.js", "/polyfills-es2015.js", "/vendor-es2015.js", "/main-es2015.js").permitAll()
                .antMatchers(HttpMethod.POST,"/api/comment","/api/communityEdit","/api/community","/api/reactUpComment","/api/reactDownComment","/api/reactDownPost","/api/reactUpPost","/api/comment").hasRole("MODERATOR")
                .antMatchers(HttpMethod.POST, "/api/user","/api/login","/api/community","/api/editUser").permitAll()
                .antMatchers(HttpMethod.POST,"/api/reactUpComment","/api/reactDownComment","/api/reactDownPost","/api/reactUpPost","/api/community","/api/post","/api/comment" ).authenticated()
                .antMatchers(HttpMethod.DELETE,"/**").hasRole("MODERATOR")
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .anyRequest().authenticated();
        httpSecurity.cors();

        // Custom JWT based authentication
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }

}
