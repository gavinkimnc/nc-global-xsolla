package com.project.otholla.controller;

import com.project.otholla.dto.merchants.Token;
import com.project.otholla.service.Items;
import com.project.otholla.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class MainController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("oshop")
    public String oshop() {
        return "oshop";
    }

    @GetMapping("jshop")
    public String jshop(Model model) {
        Items items = paymentService.getItemList();

        model.addAttribute("items", items.getItems());

        return "jshop";
    }

    @GetMapping("jshop/purchase/{sku}")
    public String jshopPurchse(@PathVariable String sku, Model model) {
        UserInfo userInfo = new UserInfo();
        String token = paymentService.getCommerceToken(userInfo.getUserId());

        Token token1 = paymentService.purchaseItem(sku, token);

        model.addAttribute("token", token1.getToken());

        return "purchase";
    }

    @GetMapping("jshop/inventory/{userid}")
    public String jshopInventory(@PathVariable String userid, Model model) {
        UserInfo userInfo = new UserInfo();
        String token = paymentService.getCommerceToken(userid);

        Inventory inventory = paymentService.inventory(token);

        model.addAttribute("inventory", inventory);
        model.addAttribute("userid", userid);

        return "inventory";
    }

    @GetMapping("jshop/inventory/{userid}/item/{sku}/consume")
    public String jshopItemConsume(@PathVariable String userid, @PathVariable String sku, Model model) {

        String token = paymentService.getCommerceToken(userid);
        paymentService.itemConsume(sku, token);

        return "consume";
    }
}