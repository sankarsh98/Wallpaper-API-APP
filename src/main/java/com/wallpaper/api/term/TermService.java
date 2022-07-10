package com.wallpaper.api.term;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;

    public List<Term> getAllTerms() {
        return termRepository.findAll();
    }

    public void addTerm(Term term) {
        termRepository.save(term);
    }

    public void deleteTerm(String term) {
        termRepository.deleteByTerm(term);
    }

    public boolean existsByTerm(String term){
        return termRepository.existsByTerm(term);
    }
    
    public int getIdByTerm(String term){
        List<Integer> ids = termRepository.findByTermTerm(term);
        if (ids.size()!=0){
            return ids.get(0);
        }else{
            return -1;
        }
    }
}
