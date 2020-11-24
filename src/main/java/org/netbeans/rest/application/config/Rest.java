/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;


import com.google.gson.Gson;
import dao.ItemDao;
import dao.PaperDao;
import java.util.ArrayList;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import model.Item;
import model.Paper;
import org.hibernate.Hibernate;

/**
 *
 * @author kassi
 */

@Path("itens")
public class Rest {  
    
    PaperDao pDao = new PaperDao();
//    Item item = new Item();;
//    Paper paper = new Paper();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@PathParam("id")int id){  
        ItemDao itemDao =new ItemDao();
        Item item = new Item();
        item = itemDao.findById(id);
        return item ;
    }
    
    @GET
    @Path("/listar/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> listItens(@PathParam("tipo") String tipo ){
        Gson gson = new Gson();
        Item item = new Item();
        Hibernate.initialize(item.getPapers());
        ItemDao itemDao =new ItemDao();
        return itemDao.list(tipo);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int insert(Item item) {
        ItemDao itemDao =new ItemDao();
        return itemDao.save(item);
    }
    
    
    @GET
    @Path("/paper/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Paper getPaper(@PathParam("id")int id){    
        Paper paper = new Paper();
        paper = pDao.findById(id);        
        return paper ;
    }
    
    @POST
    @Path("/paper")
    @Consumes(MediaType.APPLICATION_JSON)
    public int insertPaper(Paper paper){ 
        System.out.println("List Items: "+paper.getItems());
        Integer ret = pDao.save(paper);
        return ret ;
    }
    
    @GET
    @Path("/paper/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Paper> listPaper(){
        return pDao.list();
    }
    
    @GET
    @Path("/paper/listar/{tipo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Paper> listPaperTipo(@PathParam("tipo")boolean tipo){
        return pDao.listTipo(tipo);
    }
}
