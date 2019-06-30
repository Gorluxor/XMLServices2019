package com.megatravel.controller;


import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.models.messages.Message;
import com.megatravel.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public  ResponseEntity<List<ChatRoomDTO>> getInbox(@PathVariable("id") Long id){
        return new ResponseEntity<>(messageService.convertToChatroomDTO(messageService.getChatRooms(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}/chatroom/{chatRoomId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public  ResponseEntity<List<MessageDTO>> getChatRoomMessages(@PathVariable("id") Long id, @PathVariable("chatRoomId") Long chatRoomId){
        List<Message> messages = messageService.getListMessagesForChatRoom(id,chatRoomId);
        List<MessageDTO> ret = new ArrayList<>();
        for (Message m : messages){
            ret.add(new MessageDTO(m));
        }
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @RequestMapping(value = "/res/{id}/chatroom", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ChatRoomDTO> getChatRoomForReservationId(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(new ChatRoomDTO(messageService.getChatRoomForReservationId(id)), HttpStatus.OK);
    }


    @RequestMapping(value = "/{chatRoomId}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable("chatRoomId") Long chatRoomId, @RequestBody MessageDTO messageDTO){
        return new ResponseEntity<>(new MessageDTO(this.messageService.sendMessage(chatRoomId, messageDTO)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/chatRoom/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ChatRoomDTO> createRoom(@RequestBody ChatRoomDTO chatRoomDTO){
        return new ResponseEntity<>(new ChatRoomDTO(messageService.createChatRoom(chatRoomDTO)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Result", HttpStatus.OK);
    }

}
