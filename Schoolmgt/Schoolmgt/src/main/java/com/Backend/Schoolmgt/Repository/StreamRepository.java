package com.Backend.Schoolmgt.Repository;

import com.Backend.Schoolmgt.Entity.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamRepository extends JpaRepository<Stream, Long> {
}
