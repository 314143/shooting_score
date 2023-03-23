package pl.kamil.wyniki_strzeleckie.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//
@Configuration
public class WebSecurityConfig {
//    @Autowired
//    private MyAuthenticationProvider authenticationProvider;
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
////        UserDetails user = User
////                .withUsername("kamil")
////                .password(passwordEncoder.encode("pass"))
////                .roles("USER")
////                .build();
////        userDetailsService.createUser(user);
////        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
////    }
//
//    @Bean
//    public AuthenticationManager authManager (HttpSecurity http, PasswordEncoder passwordEncoder,
//                                              UserDetailsServiceImpl userDetailsService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(authenticationProvider)
////                .userDetailsService(userDetailsService)
//                .build();
//    }
//
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests(configurer -> configurer
                .antMatchers(HttpMethod.GET, "/competitions/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/competitions").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/competitions/**/addstart/**").hasRole("MANAGER")
                .antMatchers(HttpMethod.GET, "/competitors").hasRole("MANAGER")
                .antMatchers(HttpMethod.POST, "/competitors").hasRole("MANAGER")
                .antMatchers(HttpMethod.DELETE, "/competitors").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/competitors/register").permitAll()
                .antMatchers(HttpMethod.GET, "/competitors/**/starts").hasRole("MANAGER")
                .antMatchers(HttpMethod.PUT, "/starts/**").hasRole("MANAGER")
                .anyRequest().permitAll());
        http.csrf().disable();
        return http.build();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) throws Exception {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails user = User.builder()
//                        .username("user")
//                        .password("{noop}pass")
//                        .roles("USER")
//                        .build();
//        UserDetails manager = User.builder()
//                .username("manager")
//                .password("{noop}pass")
//                .roles("MANAGER", "USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}pass")
//                .roles("ADMIN", "MANAGER", "USER")
//                .build();
//        manager.createUser(admin);
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email, password, active FROM competitors WHERE email=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT competitors.email, role.name " +
                "FROM competitors " +
                "INNER JOIN user_role ON competitors.id=user_role.user_id " +
                "INNER JOIN role ON user_role.role_id=role.id " +
                "WHERE competitors.email=?");
        return jdbcUserDetailsManager;
    }
//
}
