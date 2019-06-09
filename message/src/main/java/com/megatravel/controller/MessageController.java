package com.megatravel.controller;


import com.megatravel.dtos.messages.ChatRoomDTO;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public  ResponseEntity<List<ChatRoomDTO>> getInbox(@PathVariable("id") Long id){
        return new ResponseEntity<List<ChatRoomDTO>>(this.messageService.getChatRooms(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/chatRoom/{chatRoomId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public  ResponseEntity<List<MessageDTO>> getChatRoomMessages(@PathVariable("id") Long id, @PathVariable("chatRoomId") Long chatRoomId){
        return new ResponseEntity<List<MessageDTO>>(this.messageService.getListMessagesForChatRoom(id,chatRoomId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{receiverId}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable("receiverId") Long id, @RequestBody MessageDTO messageDTO){
        return new ResponseEntity<>(this.messageService.sendMessage(id, messageDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/chatRoom", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ChatRoomDTO> createRoom(@RequestBody ChatRoomDTO chatRoomDTO){
        return new ResponseEntity<>(this.messageService.createChatRoom(chatRoomDTO), HttpStatus.OK);
    }



}
