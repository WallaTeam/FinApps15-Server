package bd;

import logica.Cliente;

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





    }
}
