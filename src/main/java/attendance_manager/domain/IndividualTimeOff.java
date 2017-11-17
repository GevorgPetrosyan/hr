package attendance_manager.domain;

import attendance_manager.converter.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@Entity
@Table(name = "individual_time_off")
public class IndividualTimeOff extends AbstractDomain implements Serializable {

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "start")
    private LocalDateTime start;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "end")
    private LocalDateTime end;

    @OneToOne
    private TimeOffType reason;

    @OneToOne
    private User user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "disposed", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean disposed;

    @Column(name = "approved", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean approved;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> approvers;

    public IndividualTimeOff() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public TimeOffType getReason() {
        return reason;
    }

    public void setReason(TimeOffType reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDisposed() {
        return disposed;
    }

    public void setDisposed(Boolean disposed) {
        this.disposed = disposed;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public List<User> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<User> approvers) {
        this.approvers = approvers;
    }
}
