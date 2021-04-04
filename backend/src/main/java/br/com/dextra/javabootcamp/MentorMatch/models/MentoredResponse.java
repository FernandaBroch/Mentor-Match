package br.com.dextra.javabootcamp.MentorMatch.models;
import lombok.Data;

import java.util.List;

@Data
public class MentoredResponse {

    private String name;

    private String bio;

    private String knowledgeArea;

    private Mentor mentor;

    private List<Mentor> likedList;

    public MentoredResponse(Mentored mentored) {
        this.name = mentored.getName();

        this.bio = mentored.getBio();

        this.knowledgeArea = mentored.getKnowledgeArea();

        this.mentor = mentored.getMentor();

        this.likedList = mentored.getLikedList();
    }
}
