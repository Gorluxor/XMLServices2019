package com.megatravel.SOAPEndpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.interfaces.*;
import com.megatravel.models.admin.User;
import com.megatravel.models.messages.ChatRoom;
import com.megatravel.models.messages.Message;
import com.megatravel.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class MessageEndpoint {
    final String NAMESPACE = "http://interfaces.megatravel.com/";

    @Autowired
    MessageServiceImpl messageService;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "GetChatRooms")
    public GetChatRoomsResponse getAll(@RequestPayload GetChatRooms input) {
        GetChatRoomsResponse response = new GetChatRoomsResponse();

        System.out.println("DOSAO");

        List<ChatRoom> chatRooms = messageService.getChatRooms(input.getUserId());
        List<ChatRoomDTO> chatRoomDTOS = new ArrayList<>();
        for(ChatRoom c : chatRooms)
        {
            ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

            chatRoomDTO.setId(c.getId());
            chatRoomDTO.setName(c.getName());

            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setArrivalDate(c.getReservation().getArrivalDate());
            reservationDTO.setDepartureDate(c.getReservation().getDepartureDate());
            reservationDTO.setId(c.getReservation().getId());
            reservationDTO.setReservationPrice(c.getReservation().getReservationPrice());
            reservationDTO.setStayRealized(c.getReservation().isStayRealized());
            UserDTO userDTO = new UserDTO();
            userDTO.setActivatedUser(c.getReservation().getUser().isActivatedUser());
            userDTO.setBirthday(c.getReservation().getUser().getBirthday());
            userDTO.setCountry(c.getReservation().getUser().getCountry());
            userDTO.setEmail(c.getReservation().getUser().getEmail());
            userDTO.setId(c.getReservation().getUser().getId());
            userDTO.setLastName(c.getReservation().getUser().getLastName());
            userDTO.setName(c.getReservation().getUser().getName());
            LocationDTO locationDTO = new LocationDTO();
            locationDTO.setCity(c.getReservation().getUser().getLocation().getCity());
            locationDTO.setCountry(c.getReservation().getUser().getLocation().getCountry());
            locationDTO.setId(c.getReservation().getUser().getLocation().getId());
            locationDTO.setLatitude(c.getReservation().getUser().getLocation().getLatitude());
            locationDTO.setLongitude(c.getReservation().getUser().getLocation().getLongitude());
            locationDTO.setNumber(c.getReservation().getUser().getLocation().getNumber());
            locationDTO.setPostalCode(c.getReservation().getUser().getLocation().getPostalCode());
            locationDTO.setStreet(c.getReservation().getUser().getLocation().getStreet());

            userDTO.setLocationDTO(locationDTO);
            userDTO.setPassword(c.getReservation().getUser().getPassword());
            userDTO.setPib(c.getReservation().getUser().getPib());
            userDTO.setPhoneNumber(c.getReservation().getUser().getPhoneNumber());

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(c.getReservation().getUser().getRole().getId());
            roleDTO.setName(c.getReservation().getUser().getRole().getRoleName());

            userDTO.setRoleDTO(roleDTO);
            reservationDTO.setUserDTO(userDTO);

            chatRoomDTO.setReservationDTO(reservationDTO);
            chatRoomDTOS.add(chatRoomDTO);
        }


        response.setChatRoomDTO(chatRoomDTOS);
        return response;

    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "GetListMessagesForChatRoom")
    public GetListMessagesForChatRoomResponse getListMessagesForChatRoom(@RequestPayload  GetListMessagesForChatRoom input) {
        GetListMessagesForChatRoomResponse response = new GetListMessagesForChatRoomResponse();

        System.out.println("DOSAO");

        List<Message> chatRooms = messageService.getListMessagesForChatRoomChatRoom(input.getUserId(),input.getChatroomId());

        List<MessageDTO> messageDTOS = new ArrayList<>();
        for(Message c : chatRooms)
        {
            MessageDTO messageDTO = new MessageDTO();

            messageDTO.setId(c.getId());
            messageDTO.setMsg(c.getMsg());

            UserDTO userDTO = new UserDTO();
            userDTO.setActivatedUser(c.getReceiver().isActivatedUser());
            userDTO.setBirthday(c.getReceiver().getBirthday());
            userDTO.setCountry(c.getReceiver().getCountry());
            userDTO.setEmail(c.getReceiver().getEmail());
            userDTO.setId(c.getReceiver().getId());
            userDTO.setLastName(c.getReceiver().getLastName());
            userDTO.setName(c.getReceiver().getName());
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(c.getReceiver().getRole().getId());
            roleDTO.setName(c.getReceiver().getRole().getRoleName());

            userDTO.setRoleDTO(roleDTO);


            UserDTO userDT = new UserDTO();
            userDT.setActivatedUser(c.getSender().isActivatedUser());
            userDT.setBirthday(c.getSender().getBirthday());
            userDT.setCountry(c.getSender().getCountry());
            userDT.setEmail(c.getSender().getEmail());
            userDT.setId(c.getSender().getId());
            userDT.setLastName(c.getSender().getLastName());
            userDT.setName(c.getSender().getName());
            RoleDTO roleDT = new RoleDTO();
            roleDT.setId(c.getSender().getRole().getId());
            roleDT.setName(c.getSender().getRole().getRoleName());

            userDT.setRoleDTO(roleDT);

            messageDTO.setReceiver(userDTO);
            messageDTO.setSender(userDT);
            messageDTO.setTimeStamp(c.getTimeStamp());

            ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

            chatRoomDTO.setId(c.getChatRoom().getId());
            chatRoomDTO.setName(c.getChatRoom().getName());

            messageDTO.setChatRoomDTO(chatRoomDTO);

            messageDTOS.add(messageDTO);

        }


        response.setMessageDTO(messageDTOS);
        return response;

    }

}