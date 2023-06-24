package com.wallpaper.api.favs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wallpaper.api.models.User;
import com.wallpaper.api.url.URL;


@Entity
@Table(name = "favs")
public class Favs {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private URL url;

    
    
    public Favs() {
    }
    
    public Favs(User user, URL url) {
        this.user = user;
        this.url = url;
    }
    public Favs (int userId, int urlId){
        this.user = new User(userId);
        this.url=new URL(urlId);
    }
    public Favs(int id, User user, URL url) {
        this.id = id;
        this.user = user;
        this.url = url;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }


}
