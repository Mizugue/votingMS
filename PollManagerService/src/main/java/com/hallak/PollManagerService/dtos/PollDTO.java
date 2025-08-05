package com.hallak.PollManagerService.dtos;


import java.util.Set;



public class PollDTO {

    private String name;
    private Set<String> options;



    public PollDTO(String name, Set<String> options) {
        this.name = name;
        this.options = options;
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



}
