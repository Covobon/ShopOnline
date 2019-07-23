package com.smartshop.api;

import com.smartshop.service.InfoSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class InfoController {

    @Autowired
    private InfoSiteService infoSiteService;

    @GetMapping("/category")
    public List<String> getCatalogy(){
        return infoSiteService.getCategory();
    }
}
