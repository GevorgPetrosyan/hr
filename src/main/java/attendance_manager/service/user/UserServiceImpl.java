package attendance_manager.service.user;

import attendance_manager.domain.Authority;
import attendance_manager.domain.PasswordResetToken;
import attendance_manager.domain.User;
import attendance_manager.domain.VerificationToken;
import attendance_manager.repository.AuthorityRepository;
import attendance_manager.repository.PasswordResetTokenRepository;
import attendance_manager.repository.UserRepository;
import attendance_manager.repository.VerificationTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 11/14/17
 */
@Service("userService")
@PropertySource("classpath:security.properties")
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${admin}")
    String admin;
    @Value("${employee}")
    String employee;
    @Value("${hr}")
    String hr;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return user == null ? null : buildUserForAuthentication(user);
    }

    @Override
    public Authority findByRole(String role) {
        return authorityRepository.findByRole(role);
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities());
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User approveUser(Long id) {
        User user = userRepository.findOne(id);
        user.setApproved(true);
        Authority authority = authorityRepository.findByRole(user.getDtype()
                .toUpperCase());
        user.setRole(authority);
        return userRepository.save(user);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findVerificationTokenByToken(token);
    }

    @Override
    public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRepository.findPasswordResetTokenByToken(token);
    }

    @Override
    public void savePasswordResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public List<User> findAllEmployees() {
        return userRepository.findByDtypeNotOrderByCreatedAtDesc(employee);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }
}
