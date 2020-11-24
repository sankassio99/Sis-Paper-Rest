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
import model.Paper;

/**
 *
 * @author kassi
 */
public class PaperDao {
    
    
    public int save(Paper paper){
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            em.getTransaction().begin();
            em.persist(paper);
            em.getTransaction().commit();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        return paper.getId() ;
    }
    
    public Paper findById(int id){
        Paper paper = null ;
        EntityManager em = new ConnectionFactory().getConnection();
        
        try{
            
            paper = em.find(Paper.class, id);
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            em.close();
        }
        return paper ;
    }
    
    public List<Paper> list(){
        EntityManager em = new ConnectionFactory().getConnection();
        List<Paper> paper = null ;
        try {
            paper = em.createQuery("from Paper").getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally {
            em.close();    
        }
        return paper ;
    }
    public List<Paper> listTipo(boolean tipo){
        EntityManager em = new ConnectionFactory().getConnection();
        List<Paper> paper = null ;
        try {
            paper = em.createQuery("from Paper i where venda="+tipo).getResultList();
        }catch(Exception e){
            System.err.println(e);
        }finally {
            em.close();    
        }
        return paper ;
    }
}
