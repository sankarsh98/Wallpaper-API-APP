package com.wallpaper.api.favs;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallpaper.api.models.User;
import com.wallpaper.api.url.URL;


@CrossOrigin
@RestController
public class FavsController {
    
    @Autowired
    private FavsService favsService;

    @RequestMapping(method = RequestMethod.POST, value = "/favs/")
    public void addFavs(@RequestBody String favsString) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        // Read a single attribute
        FavsPOJO favsPOJO = mapper.readValue(favsString,FavsPOJO.class);
        
        favsService.addFavs(new Favs(favsPOJO.getUserId(),favsPOJO.getUrlId()));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/favs/")
    public void deleteFavs(@RequestBody String favsString) throws JsonMappingException, JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        // Read a single attribute
        FavsPOJO favsPOJO = mapper.readValue(favsString,FavsPOJO.class);
        
        favsService.deleteFavs(new Favs(favsPOJO.getUserId(),favsPOJO.getUrlId()));
    }

    // @RequestMapping("/favs/{user_id}")
    // public List<URL> getFavsByUserId(@PathVariable int user_id){
    //     return favsService.getFavsByUserId(user_id);
    // }

    @RequestMapping("/favs")
    public Page<URL> getFavsByUserIdPaged(@RequestParam int userId, @RequestParam int pageNumber){
        return favsService.getFavsByUserIdPaged(userId,pageNumber);
    }

    @RequestMapping("/favs/pageInfo")
    public PageMetaPOJO getPageInfo(@RequestParam int userId){
        return favsService.getPageInfo(userId);
    }

}
