package logica;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by teruyi on 6/11/15.
 */
public class Sale {

    private int code;
    private int client;
    private Date date;
    private ArrayList<Article> articleList;
    private double finalPrice;

    public Sale (int code , int client, Date date, ArrayList articleList,
                 double finalPrice){
        this.code = code;
        this.client = client;
        this.date = date;
        this.articleList = articleList;
        this.finalPrice = finalPrice;
    }

    public int getCode (){
        return code;
    }

    public int getClient (){
        return client;
    }

    public Date getDate (){
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

    public void setdate (int date){
        this.date = date;
    }

    public void setArticleList(ArrayList<Article> articleList){
        this.articleList = articleList;
    }

    public void setFinalPrice (Double finalPrice){
        this.finalPrice = finalPrice;
    }
}
