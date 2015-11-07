package bd;

import logica.*;

import java.util.Date;

/**
 * Created by teruyi on 6/11/15.
 */
public class main {

    public static void main(String [ ] args){

        Database d = new Database();

        System.out.println(d.connect());
        Date fecha = new Date(System.currentTimeMillis());
        Cliente c = new Cliente (1,"perro", "pellicer", fecha, 50740);
        d.insertarCliente(c);
        Article a = new Article("121n2j1n2","perro", "alimentacion", 21.4, 21, "descripcion", 22);
        d.insertarArticulo(a);





    }
}
