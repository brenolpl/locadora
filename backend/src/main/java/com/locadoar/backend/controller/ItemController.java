package com.locadoar.backend.controller;

import com.locadoar.backend.domain.Item;
import com.locadoar.backend.persistence.repository.IItemRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/item")
public class ItemController extends BaseController<Item, IItemRepository> {

    public ItemController(IItemRepository repository) {
        super(Item.class, repository);
    }
}
