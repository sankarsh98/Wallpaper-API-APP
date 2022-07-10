package com.wallpaper.api.term;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TermRepository extends JpaRepository<Term, Integer> {

    @Transactional
    public void deleteByTerm(String term);

    public boolean existsByTerm(String term);

    @Query("select t.id from Term t where t.term = ?1")
    public List<Integer> findByTermTerm(String term);

    // public List<Term> findAllByTermTerm(String term);    
    
}
