package com.example.tasbih;

public class NoteModel {
    String title,content;

    public NoteModel() {
    }

    public NoteModel(String title) {
        this.title = title;
        this.content= content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
