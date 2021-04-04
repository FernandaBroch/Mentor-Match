package br.com.dextra.javabootcamp.MentorMatch.repositories;

import br.com.dextra.javabootcamp.MentorMatch.models.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoredRepository extends JpaRepository<Mentor, Long> {}