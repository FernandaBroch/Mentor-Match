package br.com.dextra.javabootcamp.MentorMatch.service;

import br.com.dextra.javabootcamp.MentorMatch.models.Mentor;
import br.com.dextra.javabootcamp.MentorMatch.models.MentorResponse;
import br.com.dextra.javabootcamp.MentorMatch.models.exceptions.UnexistentEntityException;
import br.com.dextra.javabootcamp.MentorMatch.repositories.MentorRepository;
import br.com.dextra.javabootcamp.MentorMatch.services.MentorService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MentorServiceTest {
    @Autowired
    MentorService mentorService;

    @MockBean
    MentorRepository mentorRepository;

    @Test
    public void creatingSuccess() {

        // Scenery
        Mentor mentor = new Mentor();
        mentor.setName("Xuxa Verde");
        mentor.setKnowledgeArea("Frontend");
        mentor.setBio("Bom dia amiguinhos já estou aqui");

        when(mentorRepository.save(Mockito.any(Mentor.class))).thenAnswer(i -> {
            Mentor mentorToReturn = i.getArgument(0);
            mentorToReturn.setId(1L);
            return mentorToReturn;
        });

        // Action
        Mentor returnedMentor = mentorService.create(mentor);

        // Check
        assertThat(returnedMentor).isEqualTo(mentor);
    }

    @Test
    public void deleteSuccess() {

        // Scenery
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("Xuxa Verde");
        mentor.setKnowledgeArea("Frontend");
        mentor.setBio("Bom dia amiguinhos já estou aqui");

        Mockito.when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));

        // Action
        mentorService.delete(1L);

        // Check
        verify(mentorRepository, times(1)).deleteById(1L);

    }

    @Test
    public void findOneSuccess(){
        // Scenery
        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("Xuxa Verde");
        mentor.setKnowledgeArea("Frontend");
        mentor.setBio("Bom dia amiguinhos já estou aqui");

        Mockito.when(mentorRepository.findById(1L)).thenReturn(Optional.of(mentor));

        // Action
        MentorResponse mentorResponse = mentorService.findOne(1L);

        // Check
        assertThat(mentorResponse).usingRecursiveComparison().isEqualTo(mentor);

    }

    @Test
    public void listSuccess(){
        // Scenery
        List<Mentor> mentorList = new ArrayList<Mentor>();

        Mentor mentor = new Mentor();
        mentor.setId(1L);
        mentor.setName("Xuxa Verde");
        mentor.setKnowledgeArea("Frontend");
        mentor.setBio("Bom dia amiguinhos já estou aqui");
        mentorList.add(mentor);

        Mentor mentor2 = new Mentor();
        mentor2.setId(2L);
        mentor2.setName("Claudia Senta Lá");
        mentor2.setKnowledgeArea("Backend");
        mentor2.setBio("Tenho tantas coisas pra me divertir");
        mentorList.add(mentor2);

        Mentor mentor3 = new Mentor();
        mentor3.setId(3L);
        mentor3.setName("Claudia Senta Lá");
        mentor3.setKnowledgeArea("Backend");
        mentor3.setBio("Tenho tantas coisas pra me divertir");
        mentorList.add(mentor3);

        Mockito.when(mentorRepository.findAll())
                .thenReturn(mentorList);

        // Action
        List<MentorResponse> returnedList = mentorService.list();

        // Check
        assertThat(returnedList).isNotEmpty();

    }

    @Test
    public void findOneException() {
        // Scenery
        Long id = 2L;
        Mockito.when(mentorRepository.findById(id)).thenReturn(Optional.empty());

        // Check
        assertThrows(UnexistentEntityException.class, () -> {
            // Action
            mentorService.findOne(id);
        });
    }

}
