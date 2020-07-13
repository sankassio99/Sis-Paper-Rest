/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;


import dao.ItemDao;
import dao.PaperDao;

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

/**
 *
 * @author kassi
 */

@Path("itens")
public class Rest {
    
    
    ItemDao itemDao =new ItemDao();
    PaperDao pDao = new PaperDao();
    Item item = new Item();
    Paper paper = new Paper();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item getItem(@PathParam("id")int id){      
        item = itemDao.findById(id);
        return item ;
    }
    
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> listItens(){
        return itemDao.list();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int insert(Item item) {
        return itemDao.save(item);
    }
    
    
    @GET
    @Path("/paper/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Paper getPaper(@PathParam("id")int id){       
        paper = pDao.findById(id);        
        return paper ;
    }
    
    @POST
    @Path("/paper")
    @Consumes(MediaType.APPLICATION_JSON)
    public int insertPaper(Paper paper){
        return pDao.save(paper);
    }
    
    @GET
    @Path("/paper/listar")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Paper> listPaper(){
        return pDao.list();
    }
}
