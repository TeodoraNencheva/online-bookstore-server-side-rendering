package bg.softuni.onlinebookstore.config;

import bg.softuni.onlinebookstore.model.enums.UserRoleEnum;
import bg.softuni.onlinebookstore.repositories.UserRepository;
import bg.softuni.onlinebookstore.service.BookstoreUserDetailsService;
import bg.softuni.onlinebookstore.service.OAuthSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           OAuthSuccessHandler oAuthSuccessHandler) throws Exception {

        http.
                // define which requests are allowed and which not
                        authorizeRequests().
                // everyone can download static resources (css, js, images)
                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                // everyone can login and register
                        antMatchers("/orders/mine", "/cart/**").hasRole(UserRoleEnum.USER.name()).
                antMatchers("/authors/add", "/authors/update/**",
                        "/books/add", "/books/update/**",
                        "/orders/processed", "/orders/unprocessed", "/orders/statistics",
                        "/users/all").hasRole(UserRoleEnum.ADMIN.name()).
                antMatchers("/", "/books/**", "/authors/**",
                        "/api/books/**", "/maintenance", "/swagger-ui/**").permitAll().
                antMatchers("/users/login", "/users/register/**", "/password/**").anonymous().
                // all other pages are available for logger in users
                        anyRequest().
                authenticated().
                and().
                // configuration of form login
                        formLogin().
                // the custom login form
                        loginPage("/users/login").
                // the name of the username form field
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                // the name of the password form field
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                // where to go in case that the login is successful
                        defaultSuccessUrl("/").
                // where to go in case that the login failed
                        failureForwardUrl("/users/login-error").
                and().
                // configure logut
                        logout().
                // which is the logout url, must be POST request
                        logoutUrl("/users/logout").
                // on logout go to the home page
                        logoutSuccessUrl("/").
                // invalidate the session and delete the cookies
                        invalidateHttpSession(true).
                deleteCookies("JSESSIONID").
                and().
                //allow oauth login
                        oauth2Login().
                loginPage("/users/login").
                successHandler(oAuthSuccessHandler);


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new BookstoreUserDetailsService(userRepository);
    }
}
