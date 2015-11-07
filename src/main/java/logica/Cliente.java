package logica;

import java.util.Date;

/**
 * Created by teruyi on 6/11/15.
 */
public class Cliente {

    private int code;
    private String name;
    private String surname;
    private String birthDate;
    private int postalCode;

    public Cliente(int code, String name, String surname, String birthDate, int postalCode) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
