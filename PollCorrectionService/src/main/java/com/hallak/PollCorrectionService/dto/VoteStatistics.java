package com.hallak.PollCorrectionService.dto;

public class VoteStatistics {

    private String nameOfPoll;
    private String option;
    private Long voteCount;
    private String percentage;

    public VoteStatistics(String nameOfPoll, String option, Long voteCount, String percentage) {
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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
