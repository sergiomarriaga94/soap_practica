/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.services;

import com.udea.dao.productoDao;
import com.udea.modelo.Producto;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sergio.marriaga
 */
@WebService(serviceName = "ProductoWS")//Notacion que define el comportamiento de la clase como un servicio web SOAP
public class ProductoWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ingresarProducto")
    public String ingresarProducto(@WebParam(name = "nombre") String nombre, @WebParam(name = "precio") double precio, @WebParam(name = "stock") int stock, @WebParam(name = "descripcion") String descripcion) {
        //TODO write your implementation code here:
        Producto producto=new Producto(nombre,precio,stock,descripcion);
        productoDao productoDao=new productoDao();
        productoDao.ingresarProducto(producto);
        return "producto ingresado";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarProducto")
    public String buscarProducto(@WebParam(name = "codigo") int codigo) {
        //TODO write your implementation code here:
        productoDao dao=new productoDao();
        return dao.consultarProducto(codigo);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "consultarProductos")
    public List<Producto> consultarProductos() {
        //TODO write your implementation code here:
        productoDao dao=new productoDao();
        List<Producto> list=dao.verProductos();
        return list;
    }

    /**
     * This is a sample web service operation
     */

}
