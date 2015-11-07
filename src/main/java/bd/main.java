package bd;

import logica.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by teruyi on 6/11/15.
 */
public class main {

    public static void main(String [ ] args){

        Database d = new Database();

        System.out.println(d.connect());
        String fecha = new String("25/05/2014");
        String fecha1= new String ("24/05/2015");
        Article articulo = new Article(
                "1","leche","lacteos",2.39,23,
                "Leche condensada uperisada con " +
                        "fermentos lacteos",57);
        Article articulo2 = new Article(
                "2","magdalenas","bollos",4.5,17,
                "Magdalenas caseras",39);
        Article articulo3 = new Article(
                "3","Macarrones","pasta",2.39,23,
                "Leche condensada uperisada con " +
                        "fermentos lacteos",12);
        Article articulo4 = new Article(
                "4","arroz","pasta",5.01,31,
                "Arroz bomba",89);

        Cliente cliente = new Cliente (1,"Luis","sfwefwe","21/03/2004",44311);
        Cliente cliente1 = new Cliente (2,"Raul","dsgwevxbrt","91/03/2004",55412);
        Cliente cliente3 = new Cliente (3,"Sergio","dsvgsw","32/03/2004",77421);
        Cliente cliente2 = new Cliente (4,"sdfsf","sfwefwe","16/03/2004",35128);
        ArrayList<Article> articleList = new ArrayList<Article>();
        articleList.add(articulo);
        articleList.add(articulo2);
        ArrayList<Article> articleList2 = new ArrayList<Article>();
        articleList2.add(articulo3);
        articleList2.add(articulo4);
        Worker worker1 = new Worker(1,"Francisco");
        Worker worker2 = new Worker(2,"Jose");
        Sale venta1 = new Sale(1,1,"24/5/2003",articleList,1);
        Sale venta2 = new Sale(2,2,"24/5/2003",articleList2,2);
        /*d.insertarArticulo(articulo);
        d.insertarArticulo(articulo2);
        d.insertarArticulo(articulo3);
        d.insertarArticulo(articulo4);*/
        System.out.println(d.obtenerArticulo(articulo.getCode()));
        System.out.println(d.obtenerArticulo(articulo2.getCode()));
        System.out.println(d.obtenerArticulo(articulo3.getCode()));
        System.out.println(d.obtenerArticulo(articulo4.getCode()));
       /* d.insertarCliente(cliente);
        d.insertarCliente(cliente1);
        d.insertarCliente(cliente3);
        d.insertarCliente(cliente2);
        System.out.println(d.obtenerCliente(cliente.getCode()));
        System.out.println(d.obtenerCliente(cliente.getCode()));
        System.out.println(d.obtenerCliente(cliente.getCode()));
        System.out.println(d.obtenerCliente(cliente.getCode()));
        */

        /*d.insertarTrabajador(worker1);
        d.insertarTrabajador(worker2);
        System.out.println(d.obtenerTrabajador(worker1.getDni()));
        System.out.println(d.obtenerTrabajador(worker2.getDni()));

        d.insertarVenta(venta1);*/
        d.insertarVenta(venta2);
        //System.out.println(d.obtenerVenta(venta1.getCode()));
        System.out.println(d.obtenerListadoVentas());





    }
}
