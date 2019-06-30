package com.megatravel.interfaces;


import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.server.ResponseStatusException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.rmi.NoSuchObjectException;
import java.util.List;

@WebService
public interface MessageService {

    @WebMethod
    List<ChatRoomDTO> getChatRooms(@WebParam(name = "UserId") Long userId);

    @WebMethod
    List<MessageDTO> getListMessagesForChatRoom(@WebParam(name = "UserId") Long userId, @WebParam(name = "ChatroomId") Long chatRoomId);

    @WebMethod
    MessageDTO sendMessage(Long chatRoomIdOrReservationId, MessageDTO messageDTO);

    @WebMethod
    ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO);



}
