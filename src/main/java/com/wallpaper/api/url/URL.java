package com.wallpaper.api.url;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.wallpaper.api.term.Term;

@Entity
public class URL {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    
    @ManyToOne
    private Term term;

    
    public URL(){
        
    }

    public URL(int id, String url, Term term) {
        this.id = id;
        this.url = url;
        this.term = new Term(term.getId(),"");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    
}
