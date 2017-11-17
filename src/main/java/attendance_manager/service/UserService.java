package attendance_manager.service;

import attendance_manager.domain.Authority;
import attendance_manager.domain.PasswordResetToken;
import attendance_manager.domain.User;
import attendance_manager.domain.VerificationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 11/14/17
 */
public interface UserService extends UserDetailsService {
    @Override
    default UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    User findUserByUsername(String username);

    Authority findByRole(String role);

    User save(User user);

    User approveUser(Long id);

    VerificationToken getVerificationToken(String token);

    VerificationToken saveVerificationToken(VerificationToken verificationToken);

    PasswordResetToken getPasswordResetToken(String token);

    void savePasswordResetToken(PasswordResetToken passwordResetToken);

    List<User> findAllEmployees();

    User findById(Long id);
}
