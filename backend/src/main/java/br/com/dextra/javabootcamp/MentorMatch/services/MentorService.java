package br.com.dextra.javabootcamp.MentorMatch.services;

import br.com.dextra.javabootcamp.MentorMatch.models.Mentor;
import br.com.dextra.javabootcamp.MentorMatch.models.MentorResponse;
import br.com.dextra.javabootcamp.MentorMatch.models.exceptions.UnexistentEntityException;
import br.com.dextra.javabootcamp.MentorMatch.repositories.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;

    public Mentor createMentor(Mentor mentor){
        mentorRepository.save(mentor);
        return mentor;
    }

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

}
