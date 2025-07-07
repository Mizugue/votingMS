package com.hallak.PollManagerService.entities;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.Objects;
import java.util.Set;



public class PollDTO {

    private Long id;
    private String name;
    private Set<String> options;



    public PollDTO(String name, Set<String> options) {
        this.name = name;
        this.options = options;
    }

    public PollDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PollDTO pollDTO = (PollDTO) o;
        return Objects.equals(id, pollDTO.id) && Objects.equals(name, pollDTO.name) && Objects.equals(options, pollDTO.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, options);
    }

    @Override
    public String toString() {
        return "PollDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }
}
