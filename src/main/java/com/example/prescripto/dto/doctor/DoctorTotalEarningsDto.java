package com.example.prescripto.dto.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorTotalEarningsDto {
    private Integer totalEarning;

    public DoctorTotalEarningsDto() {}
    public DoctorTotalEarningsDto(Integer totalEarning) {
        this.totalEarning = totalEarning;
    }

    public Integer getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(Integer totalEarning) {
        this.totalEarning = totalEarning;
    }
}
