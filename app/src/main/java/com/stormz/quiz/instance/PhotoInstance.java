package com.stormz.quiz.instance;

public class PhotoInstance {

    private String nameStr;
    private int photoInt;

    public PhotoInstance(String nameStr, int photoInt) {
        this.nameStr = nameStr;
        this.photoInt = photoInt;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public int getPhotoInt() {
        return photoInt;
    }

    public void setPhotoInt(int photoInt) {
        this.photoInt = photoInt;
    }
}
