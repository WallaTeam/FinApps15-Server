package logica;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by teruyi on 6/11/15.
 */
public class Sale {

    private int code;
    private int client;
    private String date;
    private ArrayList<Article> articleList;
    private double finalPrice;
    private int worker;



    public Sale (int code , int client, String date, ArrayList articleList,
                 double finalPrice, int worker){
        this.code = code;
        this.client = client;
        this.date = date;
        this.articleList = articleList;
        this.finalPrice = finalPrice;
        this.worker = worker;

    }

    public int getCode (){
        return code;
    }

    public int getClient (){
        return client;
    }

    public String getDate (){
        return date;
    }

    public ArrayList<Article> getArticlelist (){
        return articleList;
    }

    public double getFinalPrice (){
        return finalPrice;
    }

    public void setCode (int code){
        this.code = code;
    }

    public void setClient (int client){
        this.client = client;
    }

    public void setdate (String date){
        this.date = date;
    }

    public void setArticleList(ArrayList<Article> articleList){
        this.articleList = articleList;
    }

    public void setFinalPrice (Double finalPrice){
        this.finalPrice = finalPrice;
    }
    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

}
