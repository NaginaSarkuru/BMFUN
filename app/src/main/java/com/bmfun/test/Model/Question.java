package com.bmfun.test.Model;

public class Question {

    private int ID;
    private String images;
    private String questions;
    private String answers;
    private String correctPB;

    public Question(int ID, String images, String questions, String answers, String correctPB) {
        this.ID = ID;
        this.images = images;
        this.questions = questions;
        this.answers = answers;
        this.correctPB = correctPB;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getCorrectPB() {
        return correctPB;
    }

    public void setCorrectPB(String correctPB) {
        this.correctPB = correctPB;
    }

}
