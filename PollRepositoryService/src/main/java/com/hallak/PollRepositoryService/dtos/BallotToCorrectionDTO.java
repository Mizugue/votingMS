package com.hallak.PollRepositoryService.dtos;

public class BallotToCorrectionDTO {

    private Long id;
    private Long pollId;
    private Long voterId;
    private String option;


    public BallotToCorrectionDTO(Long id, Long pollId, Long voterId, String option) {
        this.id = id;
        this.pollId = pollId;
        this.voterId = voterId;
        this.option = option;
    }

    public BallotToCorrectionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}


