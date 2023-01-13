/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FINAL.exam.MyController;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Kartu;
import java.util.ArrayList;
import java.util.List;
import jpa.KartuJpaController;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Abdul Aziz Fathoni - 20200140059 
 */

@RestController
@CrossOrigin
@RequestMapping("/exam")
public class MyController {
    Kartu data = new Kartu();
    KartuJpaController ctrl = new KartuJpaController();
    
    @GetMapping
    public List<Kartu> getAll(){
        List<Kartu> dami = new ArrayList<>();
        try {
            
            dami = ctrl.findKartuEntities();
            
        } catch (Exception e) {
            
            dami = List.of();
        }
        
        return dami;
    }
    @GetMapping("/{id}")
    public List<Kartu> getNameById(@PathVariable("id") int id) {
        List<Kartu> dami = new ArrayList<>(); //Declare new LIST
        try {
            data = ctrl.findKartu(id); // get data from db
            dami.add(data);
        }catch (Exception e){
            dami = List.of();
        }
        return dami;
        } 
    @PostMapping
    public String createData(HttpEntity<String> card) {
        String message = "";
        
        try {
            String json_receive = card.getBody();
            
            ObjectMapper map = new ObjectMapper();
            
            data = map.readValue(json_receive, Kartu.class);
            
            ctrl.create(data);
            message = data.getNama() + " Data Saved";
        } catch (Exception e){
            message = "Failed";
        }
        return message;
    }
         @PutMapping
    public String editData(HttpEntity<String> card) {
        String message = "";
        
        try {
            String json_receive = card.getBody();
            
            ObjectMapper map = new ObjectMapper();
            
            data = map.readValue(json_receive, Kartu.class);
            
            ctrl.edit(data);
            message = data.getNama() + " Data Update";
        } catch (Exception e){
            message = "Failed";
        }
        return message;
    }
    @DeleteMapping
    public String deleteData(HttpEntity<String> card) {
        String message = "";
        
        try {
            String json_receive = card.getBody();
            
            ObjectMapper map = new ObjectMapper();
            
            data = map.readValue(json_receive, Kartu.class);
            
            ctrl.destroy(data.getId());
            message ="ID = "+ data.getId().toString()+ " Data Deleted"; 
        } catch (Exception e){
            message = "Failed";
        }
        return message;
    }
}
