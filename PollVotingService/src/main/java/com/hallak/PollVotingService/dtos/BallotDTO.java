package com.hallak.PollVotingService.dtos;

public class BallotDTO {

    private Long pollId;
    private String option;
    private String cpf;

    public BallotDTO(Long pollId, String option, String cpf) {
        this.pollId = pollId;
        this.option = option;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
