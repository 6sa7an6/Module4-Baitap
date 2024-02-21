package ra.md4project.security;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.GET, "/css/**", "/js/**","/fonts/**","/favicon.ico", "/about","/assets/logo/**").permitAll()
                        // Ko cần check authencation
                        .requestMatchers("/save/**", "/home","/","/product").permitAll()
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN","MODERATOR")
                        // tất cả các request còn lại đều phải xác thực
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            for(GrantedAuthority authority : authentication.getAuthorities()){
                                if(authority.getAuthority().equals("ADMIN") || authority.getAuthority().equals("MODERATOR")){
                                    HttpSession session = request.getSession();
                                    session.setAttribute("userType","ADMIN");
                                    response.sendRedirect("/admin/user");
                                }else if (authority.getAuthority().equals("USER")){
                                    HttpSession session = request.getSession();
                                    session.setAttribute("userType","USER");
                                    response.sendRedirect("/home");
                                }
                            }
                        })
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                )
        ;
        return http.build();
    }
}
