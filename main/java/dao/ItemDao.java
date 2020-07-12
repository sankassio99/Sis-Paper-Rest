/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.ConnectionFactory;
import java.util.List;
import javax.persistence.EntityManager;
import model.Item;

/**
 *
 * @author kassi
 */
public class ItemDao {
    
    public int save(Item item){
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        return item.getId() ;
    }
    
    public List<Item> list(){
        EntityManager em = new ConnectionFactory().getConnection();
        List<Item> itens = null ;
        try {
            itens = em.createQuery("from Item i").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally {
            em.close();    
        }
        return itens ;
    }
    
    public Item findById(int id){
        Item item = null ;
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            
            item = em.find(Item.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        return item ;
    }
    
    
}
