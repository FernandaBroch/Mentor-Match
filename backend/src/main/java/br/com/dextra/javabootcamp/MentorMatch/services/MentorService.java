package br.com.dextra.javabootcamp.MentorMatch.services;

import br.com.dextra.javabootcamp.MentorMatch.models.exceptions.HasMentorException;
import br.com.dextra.javabootcamp.MentorMatch.models.Mentor;
import br.com.dextra.javabootcamp.MentorMatch.models.MentorResponse;
import br.com.dextra.javabootcamp.MentorMatch.models.Mentored;
import br.com.dextra.javabootcamp.MentorMatch.models.exceptions.UnexistentEntityException;
import br.com.dextra.javabootcamp.MentorMatch.repositories.MentorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MentorService {

    MentorRepository mentorRepository;

    MentoredService mentoredService;

    MatchService matchService;

    public Mentor create(Mentor mentor){
        mentorRepository.save(mentor);
        return mentor;
    }

    public Mentor update(Mentor mentor){
        mentorRepository.save(mentor);
        return mentor;
    }

    public void delete(Long id){
        mentorRepository.deleteById(id);
    };

    public MentorResponse findOne(Long id) throws UnexistentEntityException {
        Mentor mentor = this.findOnDataBaseById(id);
        return new MentorResponse(mentor);
    }

    public List<MentorResponse> list(){
        return mentorRepository.findAll()
                .stream()
                .map(mentor -> new MentorResponse(mentor))
                .collect(Collectors.toList());
    };

    public Mentor findOnDataBaseById(Long id) throws UnexistentEntityException {
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if (mentor.isEmpty()) {
            throw new UnexistentEntityException("A mentora não existe no banco de dados");
        }
        return mentor.get();
    }

    public void likeMentored(Long mentoredId, Long mentorId) throws UnexistentEntityException, HasMentorException {

        Mentor mentor = this.findOnDataBaseById(mentorId);

        Mentored mentored = mentoredService.findOnDataBaseById(mentoredId);

        if (mentored.getMentor() != null) {
            throw new HasMentorException("Mentorada já possui mentora");
        }

        mentor.getLikedList().add(mentored);

        matchService.match(mentor, mentored);
    }

}
