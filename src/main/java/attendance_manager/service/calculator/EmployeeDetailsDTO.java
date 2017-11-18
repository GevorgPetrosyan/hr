package attendance_manager.service.calculator;


import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.domain.TimeOffType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeeDetailsDTO implements Serializable {

    private Long employeeId;
    private String employeeName;
    private LocalDate joinDate;
    private LocalDate leaveDate;
    private List<IndividualTimeOff> overallApprovedTimeOffs;
    private Map<TimeOffType, Long> overallTimeOff;
    private Map<TimeOffType, Long> overallDisposedTimeOff;
    private Map<TimeOffType, Long> timeOffSummary;
    private Double vacationLeftFromOutdated;
    private Double vacationLeftFromIndate;
    private Double overallVacationFromOutdated;
    private Double overallVacationFromIndate;
    private Double overallVacationGranted;
    private Double overallDisposableVacationTaken;
    private Double vacationInAdvance;

    public EmployeeDetailsDTO() {
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

    public List<IndividualTimeOff> getOverallApprovedTimeOffs() {
        return overallApprovedTimeOffs;
    }

    public void setOverallApprovedTimeOffs(List<IndividualTimeOff> overallApprovedTimeOffs) {
        this.overallApprovedTimeOffs = overallApprovedTimeOffs;
    }

    public Map<TimeOffType, Long> getTimeOffSummary() {
        return timeOffSummary;
    }

    public void setTimeOffSummary(Map<TimeOffType, Long> timeOffSummary) {
        this.timeOffSummary = timeOffSummary;
    }
}
