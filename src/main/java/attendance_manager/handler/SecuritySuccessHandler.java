package attendance_manager.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */
@PropertySource("classpath:security.properties")
public class SecuritySuccessHandler  extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${path_base_login}")
    String path_base_login;
    @Value("${path_base_logout}")
    String path_base_logout;
    @Value("${key_remember_me}")
    String key_remember_me;
    @Value("${key_username}")
    String key_username;
    @Value("${key_password}")
    String key_password;
    @Value("${path_base}")
    String path_base;
    @Value("${path_base_extended}")
    String path_base_extended;
    @Value("${role_admin}")
    String role_admin;
    @Value("${role_employee}")
    String role_employee;
    @Value("${role_hr}")
    String role_hr;
    @Value("${admin}")
    String admin;
    @Value("${employee}")
    String employee;
    @Value("${hr}")
    String hr;
    @Value("${base_path_admin}")
    String base_path_admin;
    @Value("${base_path_hr}")
    String base_path_hr;
    @Value("${base_path_employee}")
    String base_path_employee;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        try {
            redirectStrategy.sendRedirect(request, response, resolveTargetURL(authentication, path_base));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String resolveTargetURL(Authentication authentication, String noAuthenticatedPath) {
        String targetUrl = noAuthenticatedPath;
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = grantedAuthorities.iterator();
        while (iterator.hasNext()) {
            GrantedAuthority grantedAuthority = iterator.next();
            if (grantedAuthority.getAuthority().equalsIgnoreCase(role_admin))
                targetUrl = base_path_admin;
            else if (grantedAuthority.getAuthority().equalsIgnoreCase(role_employee))
                targetUrl = base_path_employee;
            else if (grantedAuthority.getAuthority().equalsIgnoreCase(role_hr))
                targetUrl = base_path_hr;
        }
        return targetUrl;
    }
}
