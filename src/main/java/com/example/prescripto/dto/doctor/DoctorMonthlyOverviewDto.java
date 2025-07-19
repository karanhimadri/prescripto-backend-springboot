package com.example.prescripto.dto.doctor;

public class DoctorMonthlyOverviewDto {

    private int earnings;
    private double appointmentRate;

    public DoctorMonthlyOverviewDto() {}
    public DoctorMonthlyOverviewDto(int earnings, double appointmentRate) {
        this.earnings = earnings;
        this.appointmentRate = appointmentRate;
    }

    public int getEarnings() {
        return earnings;
    }

    public double getAppointmentRate() {
        return appointmentRate;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public void setAppointmentRate(double appointmentRate) {
        this.appointmentRate = appointmentRate;
    }
}
