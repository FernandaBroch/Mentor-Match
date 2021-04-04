package br.com.dextra.javabootcamp.MentorMatch.repositories;

import br.com.dextra.javabootcamp.MentorMatch.models.Mentored;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoredRepository extends JpaRepository<Mentored, Long> {}