package br.com.dextra.javabootcamp.MentorMatch.repositories;

import br.com.dextra.javabootcamp.MentorMatch.models.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {}
