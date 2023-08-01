package com.cmpt276.groupproject.models;

public class News {
    
    private String category;
    private String datetime;
    private String headline;
    private String id;
    private String image;
    private String related;
    private String source;
    private String summary;
    private String url;


    public News() {
    }


    public News(String category, String datetime, String headline, String id, String image, String related,
            String source, String summary, String url) {
        this.category = category;
        this.datetime = datetime;
        this.headline = headline;
        this.id = id;
        this.image = image;
        this.related = related;
        this.source = source;
        this.summary = summary;
        this.url = url;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getDatetime() {
        return datetime;
    }


    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    public String getHeadline() {
        return headline;
    }


    public void setHeadline(String headline) {
        this.headline = headline;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public String getRelated() {
        return related;
    }


    public void setRelated(String related) {
        this.related = related;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public String getSummary() {
        return summary;
    }


    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    


    
    
}
