package com.hallak.sharedDtos.dtos;

public class BallotDTO {

    private Long pollId;
    private String option;
    private String token;

    public BallotDTO(Long pollId, String option, String token) {
        this.pollId = pollId;
        this.option = option;
        this.token = token;
    }

    public BallotDTO() {
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}