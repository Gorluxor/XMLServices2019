package com.megatravel.controller;


import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<MessageDTO>> getInbox(@PathVariable("id") Long id){
        return new ResponseEntity<List<MessageDTO>>(this.messageService.getInbox(id), HttpStatus.OK);
    }





}
