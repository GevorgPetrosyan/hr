package attendance_manager.service.calculator;


import attendance_manager.domain.IndividualTimeOff;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class EmployeeDetailsDTO implements Serializable {

    private Long employeeId;
    private String employeeName;
    private String joinDate;
    private String leaveDate;
    private List<IndividualTimeOff> overallApprovedTimeOffs;
    private Map<String, Long> overallTimeOff;
    private Map<String, Long> overallDisposedTimeOff;
    private Map<String, Long> timeOffSummary;
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

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public List<IndividualTimeOff> getOverallApprovedTimeOffs() {
        return overallApprovedTimeOffs;
    }

    public void setOverallApprovedTimeOffs(List<IndividualTimeOff> overallApprovedTimeOffs) {
        this.overallApprovedTimeOffs = overallApprovedTimeOffs;
    }

    public Map<String, Long> getOverallTimeOff() {
        return overallTimeOff;
    }

    public void setOverallTimeOff(Map<String, Long> overallTimeOff) {
        this.overallTimeOff = overallTimeOff;
    }

    public Map<String, Long> getOverallDisposedTimeOff() {
        return overallDisposedTimeOff;
    }

    public void setOverallDisposedTimeOff(Map<String, Long> overallDisposedTimeOff) {
        this.overallDisposedTimeOff = overallDisposedTimeOff;
    }

    public Map<String, Long> getTimeOffSummary() {
        return timeOffSummary;
    }

    public void setTimeOffSummary(Map<String, Long> timeOffSummary) {
        this.timeOffSummary = timeOffSummary;
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
