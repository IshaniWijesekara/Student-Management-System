package com.esoft.student_management.controller;

import com.esoft.student_management.dto.ItemDTO;
import com.esoft.student_management.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("${app.endpoint.api}/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO getItem(@PathVariable("id") Integer itemId){
        return itemService.getItem(itemId);
    }

    @DeleteMapping(value = "{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteItem(Integer itemId){
        return itemService.deleteItem(itemId);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveItem(@RequestBody ItemDTO itemDTO){
        return itemService.saveItem(itemDTO);
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getItemsCount(){
        return itemService.getTotalItems();
    }


}
