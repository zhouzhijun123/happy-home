/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.util.Date;

/**
 *
 * @author 周志军
 */
public class book {
    private int id;
    private String title;
    private int price;
    private String author;
    private Date submission_date;
  

    public  book(int id, String title, int price, String author, Date submission_date){
        this.id=id;
        this.title=title;
        this.author=author;
        this.submission_date=submission_date;
        this.price=price;
    }

    public book() {
//        this.id=1;
//        this.title="1";
//        this.author="1";
//        this.submission_date=new java.sql.Date(new java.util.Date().getTime());
//        this.price=1;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Date submission_date) {
        this.submission_date = submission_date;
    }
   
    
}
