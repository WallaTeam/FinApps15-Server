package logica;

/**
 * Created by teruyi on 7/11/15.
 */
public class Saled {
    private int code;
    private int article;

    public Saled(int code, int article) {
        this.code = code;
        this.article = article;
    }

    @Override
    public String toString() {
        return "Saled{" +
                "code=" + code +
                ", article=" + article +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }
}
