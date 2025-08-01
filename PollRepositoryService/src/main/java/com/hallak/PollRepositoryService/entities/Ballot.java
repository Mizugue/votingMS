package com.hallak.PollRepositoryService.entities;

import jakarta.persistence.Entity;

@Entity
public class Ballot {

    private Long pollId;
    private Long voterId;

    public Ballot(Long pollId, Long voterId) {
        this.pollId = pollId;
        this.voterId = voterId;
    }

    public Ballot() {
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
}
