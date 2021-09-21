package com.esoft.student_management.service.impl;


import com.esoft.student_management.dto.ItemDTO;
import com.esoft.student_management.entity.Item;
import com.esoft.student_management.repository.ItemRepository;
import com.esoft.student_management.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ArrayList<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        ArrayList<ItemDTO> alItems = new ArrayList<>();
        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO(item.getItemCode(), item.getItemName(), item.getQty(), item.getUnitPrice());
            alItems.add(itemDTO);
        }

        return alItems;
    }

    @Override
    public ItemDTO getItem(Integer itemId) {
        Item item = itemRepository.findById(itemId).get();
        return new ItemDTO(item.getItemCode(), item.getItemName(), item.getQty(), item.getUnitPrice());
    }

    @Override
    public boolean deleteItem(Integer itemId) {
        itemRepository.deleteById(itemId);
        return true;
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        Item item = new Item(itemDTO.getItemCode(), itemDTO.getDescription(), itemDTO.getQty(), itemDTO.getUnitPrice());
        itemRepository.save(item);
        return true;
    }

    @Override
    public long getTotalItems() {
        return itemRepository.getTotalItems();
    }


}
