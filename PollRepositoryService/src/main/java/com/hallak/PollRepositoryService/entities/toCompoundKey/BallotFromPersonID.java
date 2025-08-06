package com.hallak.PollRepositoryService.entities.toCompoundKey;


import java.io.Serializable;
import java.util.Objects;

public class BallotFromPersonID implements Serializable {
    private Long pollId;
    private Long personId;

    public BallotFromPersonID() {
    }

    public BallotFromPersonID(Long pollId, Long personId) {
        this.pollId = pollId;
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BallotFromPersonID that = (BallotFromPersonID) o;
        return Objects.equals(pollId, that.pollId) && Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pollId, personId);
    }
}