package br.com.dextra.javabootcamp.MentorMatch.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Mentored {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String knowledgeArea;

    private String bio;

    @ManyToMany
    @JsonIgnore
    private List<Mentor> likedList;

    @ManyToOne
    @JsonIgnore
    private Mentor mentor;
}
