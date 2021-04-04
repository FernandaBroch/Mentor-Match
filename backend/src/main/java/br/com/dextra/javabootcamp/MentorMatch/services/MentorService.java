package br.com.dextra.javabootcamp.MentorMatch.services;

import br.com.dextra.javabootcamp.MentorMatch.models.MentorResponse;
import br.com.dextra.javabootcamp.MentorMatch.repositories.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorService {

    @Autowired
    MentorRepository mentorRepository;

    public List<MentorResponse> list(){
        return mentorRepository.findAll()
                .stream()
                .map(mentor -> new MentorResponse(mentor))
                .collect(Collectors.toList());
    };

}
