package br.com.dextra.javabootcamp.MentorMatch.services;

import br.com.dextra.javabootcamp.MentorMatch.models.Mentor;
import br.com.dextra.javabootcamp.MentorMatch.models.Mentored;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MatchService {

    MentorService mentorService;
    MentoredService mentoredService;

    public boolean checkMatch(Mentor mentor, Mentored mentored){
        boolean mentorLikedMentored = mentored.getLikedList().contains(mentor);
        boolean mentoredLikedMentor = mentor.getLikedList().contains(mentored);

        return  mentoredLikedMentor && mentorLikedMentored;

    }

    public void createMatch(Mentor mentor, Mentored mentored){

            mentor.getMentored().add(mentored);
            mentored.setMentor(mentor);

            mentorService.update(mentor);
            mentoredService.update(mentored);

    };

    public void match(Mentor mentor, Mentored mentored){
        if(checkMatch(mentor, mentored));
            createMatch(mentor, mentored);
    }

}
