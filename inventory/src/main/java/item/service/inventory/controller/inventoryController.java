package item.service.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class inventoryController {
    
    @GetMapping("/itemList")
    public String viewItemList(){

        return "List";
    }

}
