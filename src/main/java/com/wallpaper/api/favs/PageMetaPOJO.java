package com.wallpaper.api.favs;

public class PageMetaPOJO {
    private int page_size;
    private int total_pages;
    private long total_elements;
    
    public PageMetaPOJO(int page_size, int total_pages, long total_elements) {
        this.page_size = page_size;
        this.total_pages = total_pages;
        this.total_elements = total_elements;
    }

    public long getTotal_elements() {
        return total_elements;
    }
    public void setTotal_elements(int total_elements) {
        this.total_elements = total_elements;
    }
    public int getPage_size() {
        return page_size;
    }
    
    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
    public int getTotal_pages() {
        return total_pages;
    }
    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }
    
}
