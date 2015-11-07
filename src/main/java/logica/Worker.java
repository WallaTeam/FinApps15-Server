package logica;

/**
 * Created by piraces on 7/11/15.
 */
public class Worker {

    private int dni;
    private String name;

    public Worker(int dni, String nam) {
        this.dni = dni;
        name = nam;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "dni=" + dni +
                ", name='" + name + '\'' +
                '}';
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
