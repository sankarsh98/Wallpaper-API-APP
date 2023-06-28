package com.wallpaper.api.favs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.wallpaper.api.models.User;
import com.wallpaper.api.url.URL;
import com.wallpaper.api.url.UrlRepository;

@Service
public class FavsService {

    @Autowired
    private FavsRepository favsRepository;

    @Autowired
    private UrlRepository urlRepository;

    private final int page_size = 6;

    public void addFavs(Favs favs){
        User user = new User(favs.getUser().getId());
        URL url = new URL(favs.getUrl().getId());
        Favs newFavs = new Favs(user,url);
        favsRepository.save(newFavs);
    }    

    public void deleteFavs(Favs favs){
        favsRepository.deleteByUserAndUrl(favs.getUser().getId(), favs.getUrl().getId());
    }

    public List<URL> getFavsByUserId(int user_id){

        List<Integer> urlIdList = favsRepository.getFavsByUserId(user_id);
        
        return urlRepository.getUrlsByUrlids(urlIdList);
    }

    public Page<URL> getFavsByUserIdPaged(int user_id, int page_no){

        

        Pageable pageableObject = PageRequest.of(page_no, page_size,Sort.by("id").ascending());

        
        Page<Integer> page= favsRepository.getFavsByUserIdPaged(user_id,pageableObject);
        
        List<Integer> urlIdList = page.getContent();
        
        List<URL> urlList =  urlRepository.getUrlsByUrlids(urlIdList);

        Page<URL> newPage = new PageImpl<>(urlList, pageableObject, page_size);
        return newPage;
    }

    public PageMetaPOJO getPageInfo(int userId) {

        Pageable pageableObject = PageRequest.of(0, page_size);

        Page<Integer> page= favsRepository.getFavsByUserIdPaged(userId,pageableObject);

        PageMetaPOJO pageMetaPOJO = new PageMetaPOJO(page.getSize(),page.getTotalPages(),page.getTotalElements());

        return pageMetaPOJO;
    }
}

