package com.wallpaper.api.url;

import java.util.List;

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
        }
        
    }

    public void deleteUrl(String term) {
        urlRepository.deleteAllByTermTerm(term);
    }

}
