package com.wallpaper.api.url;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public URL addUrl(String url, String term) {

        URL urlObj = new URL();

        //new code for breaking url string to url_id and ixid
        String url_string = url.substring(8, url.length()-2);
        Pattern pattern = Pattern.compile("(photo-.*)\\?.*ixid=(.*)\\&ixlib");
        Matcher matcher = pattern.matcher(url_string);
        if (matcher.find()) {
            String photoName = matcher.group(1);
            String ixid = matcher.group(2);
            urlObj.setUrlId(photoName);
            urlObj.setIxid(ixid);
            urlObj.setUrl(url_string);
        }
        //-------------------------------

        if(termService.existsByTerm(term)){
            int id = termService.getIdByTerm(term);
            urlObj.setTerm(new Term(id,""));
            return urlRepository.save(urlObj);
        }else{
            termService.addTerm(new Term(1,term));
            int id = termService.getIdByTerm(term);
            urlObj.setTerm(new Term(id,""));
            return urlRepository.save(urlObj); 
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
