package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.interfaces.MessageService;
import com.megatravel.models.messages.ChatRoom;
import com.megatravel.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@WebService(portName = "MessagePort",
            serviceName = "MessageServiceInterface",
            targetNamespace = "http://interfaces.megatravel.com",
            endpointInterface = "com.megatravel.interfaces.MessageService")
@Service
public class WebMessageServiceImpl implements MessageService {

    @Autowired
    private MessageServiceImpl messageService;

    public static final String ENDPOINT = "/services/msg";

    public WebMessageServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    /* userId in this case is ReservationID :D
    * */
    @Override
    public List<ChatRoomDTO> getChatRooms(Long userId) throws ResponseStatusException {
        return messageService.convertToChatroomDTO(messageService.getChatRooms(userId));
    }

    @Override
    public List<MessageDTO> getListMessagesForChatRoom(Long userId, Long chatRoomId) throws ResponseStatusException {
        return messageService.convertToMessageDTO(messageService.getListMessagesForChatRoom(userId,chatRoomId));
    }

    @Override
    public MessageDTO sendMessage(Long chatRoomIdOrReservationId, MessageDTO messageDTO) throws ResponseStatusException {
        return new MessageDTO(messageService.sendMessage(chatRoomIdOrReservationId,messageDTO));
    }

    @Override
    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) throws ResponseStatusException {
        return new ChatRoomDTO(messageService.createChatRoom(chatRoomDTO));
    }
}
