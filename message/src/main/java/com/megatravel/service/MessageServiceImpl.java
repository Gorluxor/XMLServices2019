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
import java.util.Optional;

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

    public List<ChatRoom> getChatRooms(Long userId) throws ResponseStatusException {
        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }

        List<ChatRoom> results = chatRoomRepository.allByUserId(userId);
        return results;
    }

    public List<Message> getListMessagesForChatRoom(Long userId, Long reservationId) throws ResponseStatusException {
        ChatRoom cr = chatRoomRepository.findFirstByReservation_Id(reservationId);

        List<Message> results = messageRepository.allMessagesToChat(cr.getId(), userId);
        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        return results;
    }


    public List<Message> getListMessagesForChatRoomChatRoom(Long userId, Long reservationId) throws ResponseStatusException {

        List<Message> results = messageRepository.allMessagesToChat(reservationId, userId);
        if (!userRepository.existsById(userId)){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        return results;
    }

    public Message sendMessage(Long reservationId, MessageDTO messageDTO) throws ResponseStatusException {


        Message msg = new Message(messageDTO);

        if (msg.getSender() == null || msg.getReceiver() == null ){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        User sender = userRepository.getOne(msg.getSender().getId());
        User receiver = userRepository.getOne(msg.getReceiver().getId());

        Date date = new Date();
        // sender and receiver are not null by .getId (otherwise throws exception)
        msg.setTimeStamp(date);
        ChatRoom chatRoom = chatRoomRepository.findFirstByReservation_Id(reservationId);

        msg.setChatRoom(chatRoom);
        msg.setReceiver(receiver);
        msg.setSender(sender);
        msg.setLastChangedDate(date);
        messageRepository.save(msg);

        return msg;
    }

    public Message sendMessageWithChatroom(Long crID, MessageDTO messageDTO) throws ResponseStatusException {


        Message msg = new Message(messageDTO);

        if (msg.getSender() == null || msg.getReceiver() == null ){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No such object in database");
        }
        User sender = userRepository.getOne(msg.getSender().getId());
        User receiver = userRepository.getOne(msg.getReceiver().getId());

        Date date = new Date();
        // sender and receiver are not null by .getId (otherwise throws exception)
        msg.setTimeStamp(date);
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(crID);

        msg.setChatRoom(chatRoom.get());
        msg.setReceiver(receiver);
        msg.setSender(sender);
        msg.setLastChangedDate(date);
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
        chatRoom.setLastChangedDate(new Date());
        chatRoom.setReservation(reservation);
        chatRoomRepository.save(chatRoom);

        starterMessage.setChatRoom(chatRoom);
        messageRepository.save(starterMessage);

        return chatRoom;
    }


    public ChatRoom getChatRoomForReservationId(Long id){
        return chatRoomRepository.findFirstByReservation_Id(id);
    }


    public List<ChatRoomDTO> convertToChatroomDTO(List<ChatRoom> list){
        List<ChatRoomDTO> results = new ArrayList<>();
        if (list != null){

            for (ChatRoom chat: list ) {
                results.add(new ChatRoomDTO(chat));
            }
        }
        return results;
    }

    public List<MessageDTO> convertToMessageDTO(List<Message> list){
        List<MessageDTO> results = new ArrayList<>();
        if (list != null){
            for (Message message : list){
                results.add(new MessageDTO(message));
            }
        }
        return results;
    }

}
