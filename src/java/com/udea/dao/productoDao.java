/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.dao;

import com.udea.modelo.Producto;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author sergio.marriaga
 */
public class productoDao {
    
public void ingresarProducto(Producto p){
    SessionFactory sf=null;
    Session session=null;
    Transaction tx=null;
    
    try{
        sf=HibernateUtil.getSessionFactory(); //obtiene session
        session=sf.openSession(); // abre session
        tx=session.beginTransaction(); //comienza la transaccion
        session.save(p);//guarda la session que comenzo
        tx.commit();//carga los datos
        session.close();//cierra la session
    }catch(Exception ex){
        tx.rollback();// Realiza la accion contraria al commit
        throw new RuntimeException("No se pudo guardar el producto");
    }
    
}

public String consultarProducto(int codigo){
    
    SessionFactory sf=HibernateUtil.getSessionFactory();
    Session session=sf.openSession();
    Producto p=(Producto)session.get(Producto.class,codigo);
    session.close();
    
    if(p!=null){
        return "el producto con codigo "+ p.getCodigo() + "cuyo nombre es " +
                p.getNombre() + "cuesta " + p.getPrecio() + "tiene en stock " + p.getStock()
                + "y su descripcion es " + p.getDescripcion();
    }else{
        return "producto con codigo " + codigo + "no existe";
    }
    
}
 
public List<Producto> verProductos(){
    SessionFactory sf=HibernateUtil.getSessionFactory();
    Session session=sf.openSession();
    
    Query query = session.createQuery("from Producto");
    List<Producto> lista=query.list();
    session.close();
    return lista;
}
}
