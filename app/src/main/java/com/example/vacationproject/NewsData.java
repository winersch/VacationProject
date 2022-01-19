package com.example.vacationproject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsData {
    private String title, link, description, time, img;
    private boolean isHasImg;

    public NewsData(String title, String link, String description, String time) {
        this.title = title;
        this.link = link;
        this.time = time;
        if (description.indexOf("<") != -1) {
            this.img = description.substring(description.indexOf("<"), description.indexOf(">"));
            this.description = description.substring(0, description.indexOf("<")) + description.substring(description.indexOf(">"));
            this.isHasImg = true;
        } else {
            this.description = description;
            this.isHasImg = false;
        }

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getLink() {
        return link;
    }

    public String getImg() {
        if(isHasImg){
            String imgURL;
            Pattern pattern = Pattern.compile("\"(.*?)\"");
            Matcher matcher = pattern.matcher(img);
            if(matcher.find()){
                imgURL = matcher.group(1);
                return imgURL;
            } else {
                return null;
            }

        }else {
            return null;
        }
    }

    public boolean isHasImg() {
        return isHasImg;
    }
}
