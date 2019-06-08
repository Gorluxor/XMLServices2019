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
    List<MessageDTO> getMessages(@WebParam(name = "UserId") Long userId);

    @WebMethod
    List<ChatRoomDTO> getChatrooms(@WebParam(name = "UserId") Long userId);

    @WebMethod
    List<MessageDTO> getListMessagesForChatroom(@WebParam(name = "UserId") Long userId, @WebParam(name = "ChatroomId") Long chatRoomId);






}
