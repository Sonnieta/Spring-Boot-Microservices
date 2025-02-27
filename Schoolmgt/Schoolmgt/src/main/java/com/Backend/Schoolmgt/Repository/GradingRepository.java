package com.Backend.Schoolmgt.Repository;

import com.Backend.Schoolmgt.Entity.Grading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradingRepository extends JpaRepository<Grading, Long> {

    @Query("SELECT g FROM Grading g WHERE :score BETWEEN g.minScore AND g.maxScore")
    Optional<Grading> findByScore(@Param("score") int score);
}
