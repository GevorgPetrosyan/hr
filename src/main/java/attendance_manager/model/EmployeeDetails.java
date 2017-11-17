package attendance_manager.model;


import attendance_manager.domain.TimeOffType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class EmployeeDetails implements Serializable {

    private Long employeeId;
    private String employeeName;
    private LocalDate joinDate;
    private LocalDate leaveDate;
    private Map<TimeOffType, Long> overallTimeOff;
    private Map<TimeOffType, Long> overallDisposedTimeOff;
    private Double vacationLeftFromOutdated;
    private Double vacationLeftFromIndate;
    private Double overallVacationFromOutdated;
    private Double overallVacationFromIndate;
    private Double overallVacationGranted;
    private Double overallDisposableVacationTaken;
    private Double vacationInAdvance;

    public EmployeeDetails() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Map<TimeOffType, Long> getOverallTimeOff() {
        return overallTimeOff;
    }

    public void setOverallTimeOff(Map<TimeOffType, Long> overallTimeOff) {
        this.overallTimeOff = overallTimeOff;
    }

    public Map<TimeOffType, Long> getOverallDisposedTimeOff() {
        return overallDisposedTimeOff;
    }

    public void setOverallDisposedTimeOff(Map<TimeOffType, Long> overallDisposedTimeOff) {
        this.overallDisposedTimeOff = overallDisposedTimeOff;
    }

    public Double getVacationLeftFromOutdated() {
        return vacationLeftFromOutdated;
    }

    public void setVacationLeftFromOutdated(Double vacationLeftFromOutdated) {
        this.vacationLeftFromOutdated = vacationLeftFromOutdated;
    }

    public Double getVacationLeftFromIndate() {
        return vacationLeftFromIndate;
    }

    public void setVacationLeftFromIndate(Double vacationLeftFromIndate) {
        this.vacationLeftFromIndate = vacationLeftFromIndate;
    }

    public Double getOverallVacationFromOutdated() {
        return overallVacationFromOutdated;
    }

    public void setOverallVacationFromOutdated(Double overallVacationFromOutdated) {
        this.overallVacationFromOutdated = overallVacationFromOutdated;
    }

    public Double getOverallVacationFromIndate() {
        return overallVacationFromIndate;
    }

    public void setOverallVacationFromIndate(Double overallVacationFromIndate) {
        this.overallVacationFromIndate = overallVacationFromIndate;
    }

    public Double getOverallVacationGranted() {
        return overallVacationGranted;
    }

    public void setOverallVacationGranted(Double overallVacationGranted) {
        this.overallVacationGranted = overallVacationGranted;
    }

    public Double getOverallDisposableVacationTaken() {
        return overallDisposableVacationTaken;
    }

    public void setOverallDisposableVacationTaken(Double overallDisposableVacationTaken) {
        this.overallDisposableVacationTaken = overallDisposableVacationTaken;
    }

    public Double getVacationInAdvance() {
        return vacationInAdvance;
    }

    public void setVacationInAdvance(Double vacationInAdvance) {
        this.vacationInAdvance = vacationInAdvance;
    }
}
