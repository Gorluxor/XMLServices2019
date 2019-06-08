package com.megatravel.interfaces;


import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface MessageService {

    @WebMethod
    List<ChatRoomDTO> getChatRooms(@WebParam(name = "UserId") Long userId);

    @WebMethod
    List<MessageDTO> getListMessagesForChatRoom(@WebParam(name = "UserId") Long userId, @WebParam(name = "ChatroomId") Long chatRoomId);

    @WebMethod
    MessageDTO sendMessage(@WebParam(name = "ReceiverId") Long receiverId, MessageDTO messageDTO);

    @WebMethod
    ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO);



}
