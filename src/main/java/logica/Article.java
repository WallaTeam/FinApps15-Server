package logica;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by teruyi on 6/11/15.
 */
public class Article {

    private String code;
    private String name;
    private String category;
    private double prize;
    private int vat;
    private String description;
    private int stock;

    @Override
    public String toString() {
        return "Article{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", prize=" + prize +
                ", vat=" + vat +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                '}';
    }

    public Article (String code, String name, String category, double prize, int vat, String description,
                    int stock){
        this.code = code;
        this.name = name;
        this.category = category;
        this.prize = prize;
        this.vat = vat;
        this.description = description;
        this.stock = stock;
    }

    public String getCode (){
        return code;
    }

    public String getName(){
        return name;
    }

    public String getCategory (){
        return category;
    }

    public double getPrize() {
        return prize;
    }

    public int getVat() {
        return vat;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
