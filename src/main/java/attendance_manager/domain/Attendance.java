package attendance_manager.domain;

import attendance_manager.converter.LocalDateTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attendance extends AbstractDomain implements Serializable {

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "in")
    private LocalDate in;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "out")
    private LocalDate out;

    @OneToOne
    private Employee employee;

    public Attendance() {
    }

    public Attendance(String ssn, LocalDate in, LocalDate out, Employee employee) {
        super(ssn);
        this.in = in;
        this.out = out;
        this.employee = employee;
    }

    public LocalDate getIn() {
        return in;
    }

    public void setIn(LocalDate in) {
        this.in = in;
    }

    public LocalDate getOut() {
        return out;
    }

    public void setOut(LocalDate out) {
        this.out = out;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
