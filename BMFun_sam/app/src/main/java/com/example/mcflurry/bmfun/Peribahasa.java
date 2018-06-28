package com.example.mcflurry.bmfun;

public class Peribahasa {
    int id;
    String peribahasa;
    String maksud;
    String img;
    String question;
    String answer;

    public Peribahasa() {
        super();
    }



    public Peribahasa(int id, String peribahasa, String maksud, String answer, String question, String img){
        this.id = id;
        this.peribahasa = peribahasa;
        this.maksud = maksud;
        this.img = img;
        this.answer = answer;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeribahasa() {
        return peribahasa;
    }

    public void setPeribahasa(String peribahasa) {
        this.peribahasa = peribahasa;
    }

    public String getMaksud() {
        return maksud;
    }

    public void setMaksud(String maksud) {
        this.maksud = maksud;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
