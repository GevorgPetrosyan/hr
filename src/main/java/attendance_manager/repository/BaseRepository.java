package attendance_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {

}
