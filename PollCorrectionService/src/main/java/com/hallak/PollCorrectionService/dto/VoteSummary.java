package com.hallak.PollCorrectionService.dto;

import java.util.List;

public class VoteSummary {

    private List<VoteStatistics> voteStatisticsList;

    public VoteSummary(List<VoteStatistics> voteStatisticsList) {
        this.voteStatisticsList = voteStatisticsList;
    }

    public VoteSummary() {
    }

    public List<VoteStatistics> getVoteStatisticsList() {
        return voteStatisticsList;
    }

    public void setVoteStatisticsList(List<VoteStatistics> voteStatisticsList) {
        this.voteStatisticsList = voteStatisticsList;
    }
}
