package com.example.prescripto.dto.doctor;

public class TotalAppointmentsDto {
    private String doctorName;
    private Long totalAppointments;
    private Long todayTotalAppointments;
    private Long scheduled;
    private Long canceled;
    private Long completed;

    public TotalAppointmentsDto(String doctorName, Long totalAppointments, Long todayTotalAppointments, Long scheduled, Long canceled, Long completed) {
        this.doctorName = doctorName;
        this.totalAppointments = totalAppointments;
        this.todayTotalAppointments = todayTotalAppointments;
        this.scheduled = scheduled;
        this.canceled = canceled;
        this.completed = completed;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public Long getScheduled() {
        return scheduled;
    }

    public Long getCanceled() {
        return canceled;
    }

    public Long getCompleted() {
        return completed;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setScheduled(Long scheduled) {
        this.scheduled = scheduled;
    }

    public void setCanceled(Long canceled) {
        this.canceled = canceled;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }

    public Long getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(Long totalAppointments) {
        this.totalAppointments = totalAppointments;
    }

    public Long getTodayTotalAppointments() {
        return todayTotalAppointments;
    }

    public void setTodayTotalAppointments(Long todayTotalAppointments) {
        this.todayTotalAppointments = todayTotalAppointments;
    }
}
