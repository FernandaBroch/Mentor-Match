package br.com.dextra.javabootcamp.MentorMatch.models;
import lombok.Data;

import java.util.List;

@Data
public class MentorResponse {

    private String name;

    private String knowledgeArea;

    private String bio;

    private List<Mentored> mentored;

    private List<Mentored> likedList;

    public MentorResponse(Mentor mentor) {
        this.name = mentor.getName();

        this.knowledgeArea = mentor.getKnowledgeArea();

        this.bio = mentor.getBio();

        this.mentored = mentor.getMentored();

        this.likedList = mentor.getLikedList();
    }




}
