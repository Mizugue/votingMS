package com.hallak.PollCorrectionService.services;

import com.hallak.PollCorrectionService.OPFConfig.PollRepositoryClient;
import com.hallak.PollCorrectionService.dto.VoteStatistics;
import com.hallak.shared_library.dtos.BallotDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PollCorrectionServiceImpl implements PollCorrectionService {


    private final PollRepositoryClient pollRepositoryClient;

    @Autowired
    public PollCorrectionServiceImpl(PollRepositoryClient pollRepositoryClient) {
        this.pollRepositoryClient = pollRepositoryClient;
    }


    @Override
    public VoteStatistics getWinnerByPollId(Long pollId) {
        List<BallotDTO> ballots = pollRepositoryClient.findAllBallotsByPollId(pollId);

        String optionWinner = ballots.stream()
    .map(BallotDTO::getOption)
    .collect(Collectors.groupingBy(option -> option, Collectors.counting()))
    .entrySet().stream()
    .max(Map.Entry.comparingByValue())
    .map(Map.Entry::getKey)
    .orElse(null);

        Map.Entry<String, Long> mostFrequent = ballots.stream()
                .map(BallotDTO::getOption)
        .collect(Collectors.groupingBy(option -> option, Collectors.counting()))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .orElse(null);

        Long frequency;

        if (mostFrequent == null){
            frequency = 0L;
        } else {
            frequency = mostFrequent.getValue();
        }



        return new VoteStatistics(
                pollRepositoryClient.findPollNameByPollId(pollId),
                optionWinner,
                frequency,
                String.format("%.2f", (frequency * 100.0) / ballots.size()));


    }



}
