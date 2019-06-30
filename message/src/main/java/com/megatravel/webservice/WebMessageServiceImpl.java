package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.interfaces.MessageService;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService(portName = "MessagePort",
            serviceName = "MessageServiceInterface",
            targetNamespace = "http://interfaces.megatravel.com",
            endpointInterface = "com.megatravel.interfaces.MessageService")
@Service
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class WebMessageServiceImpl implements MessageService {

    public static final String ENDPOINT = "/services/msg";

    public WebMessageServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public List<ChatRoomDTO> getChatRooms(Long userId) throws ResponseStatusException {
        return null;
    }

    @Override
    public List<MessageDTO> getListMessagesForChatRoom(Long userId, Long chatRoomId) throws ResponseStatusException {
        return null;
    }

    @Override
    public MessageDTO sendMessage(Long chatRoomIdOrReservationId, MessageDTO messageDTO) throws ResponseStatusException {
        return null;
    }

    @Override
    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) throws ResponseStatusException {
        return null;
    }
}
