package com.wallpaper.api.url;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wallpaper.api.term.Term;

public interface UrlRepository extends JpaRepository<URL,Integer>{

    @Query(nativeQuery = true,value = "select * from URL where term_id = ?1")
    List<URL> getAllByTermId(int termId);
    
    @Query(nativeQuery = true,value = "select * from URL where id IN (:url_ids)")
    List<URL> getUrlsByUrlids(@Param("url_ids") List<Integer> url_ids);
    
    @Transactional
    void deleteAllByTermId(int termId);


    @Transactional
    void deleteAllByTermTerm(String term);


    // Integer getIdByUrlid(String url_id);

   
}
