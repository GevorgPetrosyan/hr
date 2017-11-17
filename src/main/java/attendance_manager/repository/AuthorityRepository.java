package attendance_manager.repository;

import attendance_manager.domain.Authority;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author Marta Ginosyan
 * Date: 11/14/17
 */
@Primary
@Repository
public interface AuthorityRepository extends BaseRepository<Authority> {

    Authority findByRole(String role);
}
