package com.wallpaper.api.url;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wallpaper.api.term.Term;

@CrossOrigin
@RestController
public class UrlController {
    
    @Autowired
    private UrlService urlService;

    @RequestMapping("/terms/{term}/urls/")
    public List<URL> getUrls(@PathVariable String term){
        return urlService.getUrls(term);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/terms/{term}/urls/")
    public void addUrl(@RequestBody URL url,@PathVariable String term){
        urlService.addUrl(url,term);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/terms/{term}/urls/")
    public void deleteUrl(@PathVariable String term){
        urlService.deleteUrl(term);
    }
    
    
}
