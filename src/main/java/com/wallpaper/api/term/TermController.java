package com.wallpaper.api.term;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TermController {
    
    //Get all terms
    //Add terms

    @Autowired
    private TermService termService;
    
    @RequestMapping("/")
    public String homeMapping(){
        return "Hello";
    }

    @RequestMapping("/terms")
    public List<Term> getAllTerms(){
        return termService.getAllTerms();
    }

    @RequestMapping(method = RequestMethod.POST, value="/terms")
    public void addTerm(@RequestBody Term term){
        System.out.println(term.toString());
        termService.addTerm(term);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/terms/{term}")
    public void deleteTerm(@PathVariable String term){
        termService.deleteTerm(term);
    }

    //temp method

    @RequestMapping(method = RequestMethod.GET, value = "/terms/{term}")
    public boolean isTermExists(@PathVariable String term){
        return termService.existsByTerm(term);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/terms/id/{term}")
    public int getIdByTerm(@PathVariable String term){
        return termService.getIdByTerm(term);
    }

}
