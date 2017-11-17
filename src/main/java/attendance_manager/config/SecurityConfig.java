package attendance_manager.config;

import attendance_manager.handler.SecuritySuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@Configuration
@EnableWebMvc
@Order
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("classpath:security.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${path_base_login}")
    String PATH_BASE_LOGIN;

    @Value("${path_base_logout}")
    String PATH_BASE_LOGOUT;

    @Value("${key_remember_me}")
    String KEY_REMEMBER_ME;

    @Value("${key_username}")
    String KEY_USERNAME;

    @Value("${key_password}")
    String KEY_PASSWORD;

    @Autowired
    @Qualifier("userService")
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .invalidSessionUrl(PATH_BASE_LOGIN)
                .and()
                .rememberMe()
                .rememberMeParameter(KEY_REMEMBER_ME)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(20)
                .and()
                .formLogin()
                .loginPage(PATH_BASE_LOGIN)
                .successHandler(new SecuritySuccessHandler())
                .usernameParameter(KEY_USERNAME)
                .passwordParameter(KEY_PASSWORD)
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("remember-me")
                .and()
                .csrf()
                .disable();
        System.out.println();
    }


    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers(
                        "/", "/registration",
                        "/registration/**",
                        "/registration/",
                        "registration");
    }

    @Bean
    public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
        PersistentTokenBasedRememberMeServices tokenBasedService = new PersistentTokenBasedRememberMeServices(
                KEY_REMEMBER_ME, userDetailsService, persistentTokenRepository());
        tokenBasedService.setUseSecureCookie(true);
        tokenBasedService.setCookieName(KEY_REMEMBER_ME);
        tokenBasedService.setTokenLength(3);
        return tokenBasedService;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
