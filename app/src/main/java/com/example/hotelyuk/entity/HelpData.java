package com.example.hotelyuk.entity;

public class HelpData {
    private String title;
    private String desc;
    private boolean expandable;

    public HelpData(String title, String desc)
    {
        this.title = title;
        this.desc = desc;
        this.expandable = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}
