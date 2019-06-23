package com.megatravel.service;

import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.models.admin.User;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.models.messages.ChatRoom;
import com.megatravel.models.messages.Message;
import com.megatravel.models.reservations.Reservation;
import com.megatravel.repository.ChatRoomRepository;
import com.megatravel.repository.MessageRepository;
import com.megatravel.repository.ReservationRepository;
import com.megatravel.repository.UserRepository;
import com.megatravel.utils.PageRequestProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl {

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

    public List<ChatRoomDTO> getChatRooms(Long userId) throws ResponseStatusException {
        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }

        List<ChatRoom> results = chatRoomRepository.allByUserId(userId);
        List<ChatRoomDTO> retVal = new ArrayList<>();
        for (ChatRoom chat: results ) {
            retVal.add(new ChatRoomDTO(chat));
        }
        return retVal;
    }

    public List<Message> getListMessagesForChatRoom(Long userId, Long chatRoomId) throws ResponseStatusException {
        List<Message> results = messageRepository.allMessagesToChat(chatRoomId, userId);
        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        return results;
    }

    public Message sendMessage(Long chatRoomIdOrReservationId, MessageDTO messageDTO) throws ResponseStatusException {

        Message msg = new Message(messageDTO);

        if (msg.getSender() == null || msg.getReceiver() == null || msg.getChatRoom() == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        User sender = userRepository.getOne(msg.getSender().getId());
        User receiver = userRepository.getOne(msg.getReceiver().getId());

        // sender and receiver are not null by .getId (otherwise throws exception)
        msg.setTimeStamp(new Date());
        ChatRoom chatRoom = chatRoomRepository.getOne(msg.getChatRoom().getId());

        msg.setChatRoom(chatRoom);
        msg.setReceiver(receiver);
        msg.setSender(sender);

        messageRepository.save(msg);

        return msg;
    }

    public ChatRoom createChatRoom(ChatRoomDTO chatRoomDTO) throws ResponseStatusException{

        ChatRoom chatRoom = new ChatRoom(chatRoomDTO);

        if (chatRoom.getReservation() == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }

        Reservation reservation = reservationRepository.getOne(chatRoom.getReservation().getId()); // returns not null

        Message starterMessage = new Message();
        starterMessage.setSender(reservation.getUser());
        starterMessage.setTimeStamp(new Date());
        starterMessage.setMsg("Your chat has started");
        AccommodationUnit accommodationUnit = reservation.getAccommodationUnit().get(0);
        if (accommodationUnit == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        starterMessage.setReceiver(accommodationUnit.getAccommodation().getUser());

        chatRoom.setReservation(reservation);
        chatRoomRepository.save(chatRoom);

        starterMessage.setChatRoom(chatRoom);
        messageRepository.save(starterMessage);

        return chatRoom;
    }
}
