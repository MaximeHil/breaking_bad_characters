package com.example.bb_characters.ui.characterdisplay.adapter;

public class CharacterDetailsViewItem {

    private String url;
    private String name;
    private String nickname;
    private String birthday;
    private String portrayed;

    public CharacterDetailsViewItem() {
        this.url = "";
        this.name = "";
        this.nickname = "";
        this.birthday = "";
        this.portrayed = "";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPortrayed() {
        return portrayed;
    }

    public void setPortrayed(String portrayed) {
        this.portrayed = portrayed;
    }

    @Override
    public String toString() {
        return "CharacterDetailsViewItem{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", portrayed='" + portrayed + '\'' +
                '}';
    }
}
