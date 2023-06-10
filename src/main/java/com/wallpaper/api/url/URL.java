package com.wallpaper.api.url;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wallpaper.api.term.Term;

@Entity
@Table(name = "URL")
public class URL {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "url_id")
    private String urlId;
    
    private String url;

    @Column(name = "ixid")
    private String ixid;
    
    public String getIxid() {
        return ixid;
    }

    public void setIxid(String ixid) {
        this.ixid = ixid;
    }

    @ManyToOne
    private Term term;

    
    public URL(){
        
    }

    public URL(int id, String urlId, String ixid, Term term) {
        this.id = id;
        this.urlId = urlId;
        this.ixid = ixid;
        this.term = new Term(term.getId(),"");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
