package com.megatravel.service;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.interfaces.MessageService;
import com.megatravel.models.admin.User;
import com.megatravel.models.messages.ChatRoom;
import com.megatravel.models.messages.Message;
import com.megatravel.models.reservations.Reservation;
import com.megatravel.repository.ChatRoomRepository;
import com.megatravel.repository.MessageRepository;
import com.megatravel.repository.ReservationRepository;
import com.megatravel.repository.UserRepository;
import com.megatravel.utils.PageRequestProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebService(endpointInterface = "com.megatravel.interfaces.MessageService")
@Service
public class MessageServiceImpl implements MessageService {

    public static final String ENDPOINT = "/msg";

    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    PageRequestProvider pageRequestProvider;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReservationRepository reservationRepository;

    public MessageServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }


    @Override // Page<ChatRoomDTO>
    public List<ChatRoomDTO> getChatRooms(Long userId, String page) throws ResponseStatusException {
        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }

        Page<ChatRoom> results = chatRoomRepository.findAllByReservation_User_Id(userId, pageRequestProvider.provideRequest(page));
        List<ChatRoomDTO> retVal = new ArrayList<>();
        for (ChatRoom chat: results.getContent() ) {
            retVal.add(new ChatRoomDTO(chat));
        }
        return retVal;
    }

    @Override
    public List<MessageDTO> getListMessagesForChatRoom(Long userId, Long chatRoomId, String page) throws ResponseStatusException {
        Page<Message> results = messageRepository.allMessagesToChat(chatRoomId, userId, pageRequestProvider.provideRequest(page));

        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }

        List<MessageDTO> retVal = new ArrayList<>();
        for (Message m : results.getContent()){
            retVal.add(new MessageDTO(m));
        }
        return retVal;
    }

    @Override
    public MessageDTO sendMessage(Long chatRoomId, MessageDTO messageDTO) throws ResponseStatusException {

        Message msg = new Message(messageDTO);

        if (msg.getSender() == null || msg.getReceiver() == null || msg.getChatRoom() == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        User sender = userRepository.getOne(msg.getSender().getId());
        User receiver = userRepository.getOne(msg.getReceiver().getId());
        if (sender == null || receiver == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        msg.setTimeStamp(new Date());
        ChatRoom chatRoom = chatRoomRepository.getOne(msg.getChatRoom().getId());

        if (chatRoom == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        msg.setChatRoom(chatRoom);
        msg.setReceiver(receiver);
        msg.setSender(sender);
        messageRepository.save(msg);

        return new MessageDTO(msg);
    }

    @Override
    public ChatRoomDTO createChatRoom(ChatRoomDTO chatRoomDTO) throws ResponseStatusException{

        ChatRoom chatRoom = new ChatRoom(chatRoomDTO);

        if (chatRoom.getReservation() == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }

        Reservation reservation = reservationRepository.getOne(chatRoom.getReservation().getId());
        if (reservation == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }

        chatRoom.setReservation(reservation);
        chatRoomRepository.save(chatRoom);

        return new ChatRoomDTO(chatRoom);
    }
}
