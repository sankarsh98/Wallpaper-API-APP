package com.wallpaper.api.favs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}

