package ro.personal.home.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import ro.personal.home.realestate.persistance.model.Ad;
import ro.personal.home.realestate.service.AdService;

@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping(value = "/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Ad getAd(@PathVariable String id) {
        return adService.findById(id);
    }
}
