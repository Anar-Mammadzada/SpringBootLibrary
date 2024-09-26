package com.company.controller;

import com.company.entity.Message;
import com.company.service.MessagesService;
import com.company.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessagesService service;


    public MessagesController(MessagesService service) {
        this.service = service;
    }

    @PostMapping("/secure/add/message")
    public void postMessage(@RequestHeader(value = "Authorization") String token, @RequestBody Message messageRequest){
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        service.postMessage(messageRequest, userEmail);
    }
}
