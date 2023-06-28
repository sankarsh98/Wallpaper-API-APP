package com.wallpaper.api.favs;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FavsRepository extends JpaRepository<Favs,Integer>{
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "delete from favs where user_id = ?1 and url_id = ?2")
    void deleteByUserAndUrl(int user_id,int url_id);

    @Query(nativeQuery = true,value = "select url_id from favs where user_id = ?1")
    List<Integer> getFavsByUserId(int user_id);

    @Query(nativeQuery = true,value = "select url_id from favs where user_id = ?1 order by url_id asc")
    Page<Integer> getFavsByUserIdPaged(int user_id,Pageable pageable);
}
