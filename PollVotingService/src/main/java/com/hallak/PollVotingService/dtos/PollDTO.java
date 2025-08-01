package com.hallak.PollVotingService.dtos;


import java.util.Set;

public class PollDTO {

    private Long id;
    private String name;
    private Set<String> options;



    public PollDTO(String name, Set<String> options, Long id) {
        this.name = name;
        this.options = options;
        this.id = id;
    }

    public PollDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getOptions() {
        return options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
