package com.wallpaper.api.url;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallpaper.api.term.Term;
import com.wallpaper.api.term.TermService;

@Service
public class UrlService {
    
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private TermService termService;

    public List<URL> getUrls(String term) {
        if(termService.existsByTerm(term)){
            int id = termService.getIdByTerm(term);
            return urlRepository.getAllByTermId(id);
        }
        return null;
    }

    public void addUrl(URL url, String term) {

        if(termService.existsByTerm(term)){
            int id = termService.getIdByTerm(term);
            url.setTerm(new Term(id,""));
            urlRepository.save(url);
        }else{
            termService.addTerm(new Term(1,term));
            int id = termService.getIdByTerm(term);
            url.setTerm(new Term(id,""));
            urlRepository.save(url); 
        }
        
    }

    public void deleteUrl(String term) {
        urlRepository.deleteAllByTermTerm(term);
    }

    public URL getRandomUrl(String term) {
        if(termService.existsByTerm(term)){
            int id = termService.getIdByTerm(term);
            List<URL> urls = urlRepository.getAllByTermId(id); 

            Random rand = new Random();
            return urls.get(rand.nextInt(urls.size()));

        }else{
            int id = termService.getIdByTerm("default"); 

            Random rand = new Random();
            List<URL> urls = urlRepository.getAllByTermId(id);
            
            return urls.get(rand.nextInt(urls.size()));
        }
    }

}
