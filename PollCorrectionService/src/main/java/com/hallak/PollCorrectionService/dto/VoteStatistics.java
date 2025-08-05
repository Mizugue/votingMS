package com.hallak.PollCorrectionService.dto;

public class VoteStatistics {

    private String nameOfPoll;
    private String option;
    private Long voteCount;
    private Double percentage;

    public VoteStatistics(String nameOfPoll, String option, Long voteCount, Double percentage) {
        this.nameOfPoll = nameOfPoll;
        this.option = option;
        this.voteCount = voteCount;
        this.percentage = percentage;
    }

    public VoteStatistics() {
    }

    public String getNameOfPoll() {
        return nameOfPoll;
    }

    public void setNameOfPoll(String nameOfPoll) {
        this.nameOfPoll = nameOfPoll;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
