package com.hallak.PollRepositoryService.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ElementCollection
    @CollectionTable(name = "poll_options", joinColumns = @JoinColumn(name = "poll_id"))
    @Column(name = "option")
    private Set<String> options = new HashSet<>();


    public Poll(Long id, String name, Set<String> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    public Poll() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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