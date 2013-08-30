package com.app.example.model;

public class Content {
    private Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String letter;
    private String name;

    public Content(Long id,String letter, String name) {
        super();
        this.id = id;
        this.letter = letter;
        this.name = name;
    }

    public Content(String letter, String name) {
        super();
        this.letter = letter;
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
