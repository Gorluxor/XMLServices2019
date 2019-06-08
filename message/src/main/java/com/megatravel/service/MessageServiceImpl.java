package com.megatravel.service;

import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.interfaces.MessageService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;


@WebService(endpointInterface = "com.megatravel.interfaces.MessageService")
@Service
public class MessageServiceImpl implements MessageService {

    public static final String ENDPOINT = "/msg";


    @Override
    public List<ChatRoomDTO> getChatRooms(Long userId) {
        return null;
    }

    @Override
    public List<MessageDTO> getListMessagesForChatRoom(Long userId, Long chatRoomId) {
        return null;
    }

    @Override
    public MessageDTO sendMessage(Long receiverId, MessageDTO messageDTO) {
        return null;
    }

    @Override
    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) {
        return null;
    }
}
