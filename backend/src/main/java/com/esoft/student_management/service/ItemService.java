package com.esoft.student_management.service;

import com.esoft.student_management.dto.ItemDTO;

import java.util.ArrayList;

public interface ItemService {

    public ArrayList<ItemDTO> getAllItems();

    public ItemDTO getItem(Integer itemId);

    public boolean deleteItem(Integer itemId);

    public boolean saveItem(ItemDTO itemDTO);

    public long getTotalItems();


}
