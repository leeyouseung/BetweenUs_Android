package org.techtown.betweenus_android.model;

import java.util.List;

public class Image {

    private List<String> images;

    public Image(List<String> images) {
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
