package com.hallak.sharedDtos.dtos;

public class BallotToCorrectionDTO {


    private String option;


    public BallotToCorrectionDTO(String option) {
        this.option = option;
    }

    public BallotToCorrectionDTO() {
    }



    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }



}

