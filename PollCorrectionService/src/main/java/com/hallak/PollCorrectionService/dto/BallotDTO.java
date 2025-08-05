package com.hallak.PollCorrectionService.dto;

public class BallotDTO {


    private String option;


    public BallotDTO(String option) {
        this.option = option;
    }

    public BallotDTO() {
    }



    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }



}






