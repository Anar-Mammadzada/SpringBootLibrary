package com.company.controller;

import com.company.entity.Message;
import com.company.requestModels.AdminQuestionRequest;
import com.company.service.MessagesService;
import com.company.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("https://localhost:3000")
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

    @PutMapping("/secure/admin/message")
    public void putMessage(@RequestHeader(value = "Authorization") String token, @RequestBody AdminQuestionRequest adminQuestionRequest)throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        String admin = ExtractJWT.payloadJWTExtraction(token, "\"userType\"");
        if (admin == null || !admin.equals("admin")){
            throw new Exception("Administration page only.");
        }
        service.putMessage(adminQuestionRequest, userEmail);
    }
}
