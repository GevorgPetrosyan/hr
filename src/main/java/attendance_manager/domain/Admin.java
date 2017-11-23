package attendance_manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */
@Entity
@Table(name = "admin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin  extends User implements Serializable {
}
