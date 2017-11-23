package attendance_manager.domain;

import attendance_manager.converter.LocalTimeAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author Marta Ginosyan
 * Date: 11/14/17
 */

@Entity
@Table(name = "working_hours_scheme")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkingHoursScheme extends AbstractDomain implements Serializable {

    @Column(name = "title")
    private String title;

    @Column(name = "number_of_hours_for_period")
    private Short numberOfHoursForPeriod;

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "working_day_start")
    private LocalTime workingDayStart;

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "working_day_end")
    private LocalTime workingDayEnd;

    @Column(name = "is_valid", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isValid;

    public WorkingHoursScheme() {
    }

    public WorkingHoursScheme(String ssn, String title, Short numberOfHoursForPeriod, LocalTime workingDayStart, LocalTime workingDayEnd, Boolean isValid) {
        super(ssn);
        this.title = title;
        this.numberOfHoursForPeriod = numberOfHoursForPeriod;
        this.workingDayStart = workingDayStart;
        this.workingDayEnd = workingDayEnd;
        this.isValid = isValid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getNumberOfHoursForPeriod() {
        return numberOfHoursForPeriod;
    }

    public void setNumberOfHoursForPeriod(Short numberOfHoursForPeriod) {
        this.numberOfHoursForPeriod = numberOfHoursForPeriod;
    }

    public LocalTime getWorkingDayStart() {
        return workingDayStart;
    }

    public void setWorkingDayStart(LocalTime workingDayStart) {
        this.workingDayStart = workingDayStart;
    }

    public LocalTime getWorkingDayEnd() {
        return workingDayEnd;
    }

    public void setWorkingDayEnd(LocalTime workingDayEnd) {
        this.workingDayEnd = workingDayEnd;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
