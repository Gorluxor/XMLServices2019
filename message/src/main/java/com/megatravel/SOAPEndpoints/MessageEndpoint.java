package com.megatravel.SOAPEndpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.interfaces.*;
import com.megatravel.models.admin.User;
import com.megatravel.models.messages.ChatRoom;
import com.megatravel.models.messages.Message;
import com.megatravel.repository.ChatRoomRepository;
import com.megatravel.repository.UserRepository;
import com.megatravel.service.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Endpoint
public class MessageEndpoint {
    final String NAMESPACE = "http://interfaces.megatravel.com/";

    @Autowired
    MessageServiceImpl messageService;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserRepository userRepository;

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

            messageDTO.setReceiver(userDT);
            messageDTO.setSender(userDTO);
            messageDTO.setTimeStamp(c.getTimeStamp());

            System.out.println("TIMESTAMP " + messageDTO.getTimeStamp());
            System.out.println("TIMESTAMP " + messageDTO.getReceiver().getId());
            System.out.println("SENDER " + messageDTO.getSender().getId());



            ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

            chatRoomDTO.setId(c.getChatRoom().getId());
            chatRoomDTO.setName(c.getChatRoom().getName());

            messageDTO.setChatRoomDTO(chatRoomDTO);

            messageDTOS.add(messageDTO);

        }


        response.setMessageDTO(messageDTOS);
        return response;

    }

   /* @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "SendMessage")
    public SendMessageResponse sendMessage(@RequestPayload  SendMessage input) {
        SendMessageResponse response = new SendMessageResponse();

        System.out.println("DOSAO");

        System.out.println("ID" + input.getMessageDTO().getReceiver().getId());


        com.megatravel.dtos.messages.MessageDTO messageDT = new com.megatravel.dtos.messages.MessageDTO();
     //   messageDT.setId(input.getMessageDTO().getId());
        messageDT.setMsg(input.getMessageDTO().getMsg());

        com.megatravel.dtos.messages.ChatRoomDTO chatRoomDTO= new com.megatravel.dtos.messages.ChatRoomDTO();

        chatRoomDTO.setId(input.getArg0());
       // chatRoomDTO.setName(input.getMessageDTO().getChatRoomDTO().getName());

        messageDT.setChatRoomDTO(chatRoomDTO);
     //   messageDT.setTimeStamp(input.getMessageDTO().getTimeStamp());

        com.megatravel.dtos.admin.UserDTO userDTO = new com.megatravel.dtos.admin.UserDTO();

    //    userDTO.setActivatedUser(input.getMessageDTO().getReceiver().isActivatedUser());
    //    userDTO.setBirthday(input.getMessageDTO().getReceiver().getBirthday());
    //    userDTO.setCountry(input.getMessageDTO().getReceiver().getCountry());
        userDTO.setEmail(input.getMessageDTO().getReceiver().getEmail());
       userDTO.setId(input.getMessageDTO().getReceiver().getId());
    //    userDTO.setLastName(input.getMessageDTO().getReceiver().getLastName());
    //    userDTO.setName(input.getMessageDTO().getReceiver().getName());
    //    com.megatravel.dtos.admin.RoleDTO roleDTO = new com.megatravel.dtos.admin.RoleDTO();
    //    roleDTO.setId(input.getMessageDTO().getReceiver().getRoleDTO().getId());
   //     roleDTO.setName(input.getMessageDTO().getReceiver().getRoleDTO().getName());

   //     userDTO.setRoleDTO(roleDTO);

        messageDT.setReceiver(userDTO);


        com.megatravel.dtos.admin.UserDTO userDT = new com.megatravel.dtos.admin.UserDTO();
      //  userDT.setActivatedUser(input.getMessageDTO().getSender().isActivatedUser());
     //   userDT.setBirthday(input.getMessageDTO().getSender().getBirthday());
      //  userDT.setCountry(input.getMessageDTO().getSender().getCountry());
        userDT.setEmail(input.getMessageDTO().getSender().getEmail());
       userDT.setId(input.getMessageDTO().getSender().getId());
        //userDT.setLastName(input.getMessageDTO().getSender().getLastName());
       // userDT.setName(input.getMessageDTO().getSender().getName());
      //  com.megatravel.dtos.admin.RoleDTO roleDT = new com.megatravel.dtos.admin.RoleDTO();
     //   roleDT.setId(input.getMessageDTO().getSender().getRoleDTO().getId());
    //    roleDT.setName(input.getMessageDTO().getSender().getRoleDTO().getName());

   //     userDT.setRoleDTO(roleDT);

        messageDT.setSender(userDT);

        messageDT.setLastChangedDate(new Date());

        System.out.println("DPSAP DPVDE");

        Message c = messageService.sendMessageWithChatroom(input.getArg0(),messageDT);

            MessageDTO messageDTO = new MessageDTO();

            messageDTO.setId(c.getId());
            messageDTO.setMsg(c.getMsg());

            UserDTO s = new UserDTO();
            s.setActivatedUser(c.getReceiver().isActivatedUser());
            s.setBirthday(c.getReceiver().getBirthday());
            s.setCountry(c.getReceiver().getCountry());
            s.setEmail(c.getReceiver().getEmail());
            s.setId(c.getReceiver().getId());
            s.setLastName(c.getReceiver().getLastName());
            s.setName(c.getReceiver().getName());
            RoleDTO r = new RoleDTO();
            r.setId(c.getReceiver().getRole().getId());
            r.setName(c.getReceiver().getRole().getRoleName());

            s.setRoleDTO(r);


            UserDTO g = new UserDTO();
            g.setActivatedUser(c.getSender().isActivatedUser());
            g.setBirthday(c.getSender().getBirthday());
            g.setCountry(c.getSender().getCountry());
            g.setEmail(c.getSender().getEmail());
            g.setId(c.getSender().getId());
            g.setLastName(c.getSender().getLastName());
            g.setName(c.getSender().getName());
            RoleDTO rr = new RoleDTO();
            rr.setId(c.getSender().getRole().getId());
            rr.setName(c.getSender().getRole().getRoleName());

            g.setRoleDTO(rr);

            messageDTO.setReceiver(s);
            messageDTO.setSender(g);
            messageDTO.setTimeStamp(c.getTimeStamp());

            ChatRoomDTO cc = new ChatRoomDTO();

            cc.setId(c.getChatRoom().getId());
            cc.setName(c.getChatRoom().getName());

            messageDTO.setChatRoomDTO(cc);
        System.out.println("DPSAP DPVDE");


        response.setMessageDTO(messageDTO);
        return response;

    }
*/


    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "SendMessage")
    public SendMessageResponse sendMessage(@RequestPayload SendMessage input) {
        SendMessageResponse response = new SendMessageResponse();

        System.out.println("DOSAO");

        User user = userRepository.findByEmail(input.getMessageDTO().getSender().getEmail());
        // PRIMALAC USTVARI

        Optional<User> sender = userRepository.findById(input.getMessageDTO().getId());

        com.megatravel.dtos.messages.MessageDTO messageDT = new com.megatravel.dtos.messages.MessageDTO();
        //   messageDT.setId(input.getMessageDTO().getId());
        messageDT.setMsg(input.getMessageDTO().getMsg());

        com.megatravel.dtos.messages.ChatRoomDTO chatRoomDTO= new com.megatravel.dtos.messages.ChatRoomDTO();

        chatRoomDTO.setId(input.getArg0());
        // chatRoomDTO.setName(input.getMessageDTO().getChatRoomDTO().getName());

        messageDT.setChatRoomDTO(chatRoomDTO);
        //   messageDT.setTimeStamp(input.getMessageDTO().getTimeStamp());

        com.megatravel.dtos.admin.UserDTO userDTO = new com.megatravel.dtos.admin.UserDTO();

        //    userDTO.setActivatedUser(input.getMessageDTO().getReceiver().isActivatedUser());
        //    userDTO.setBirthday(input.getMessageDTO().getReceiver().getBirthday());
        //    userDTO.setCountry(input.getMessageDTO().getReceiver().getCountry());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        //    userDTO.setLastName(input.getMessageDTO().getReceiver().getLastName());
        //    userDTO.setName(input.getMessageDTO().getReceiver().getName());
        //    com.megatravel.dtos.admin.RoleDTO roleDTO = new com.megatravel.dtos.admin.RoleDTO();
        //    roleDTO.setId(input.getMessageDTO().getReceiver().getRoleDTO().getId());
        //     roleDTO.setName(input.getMessageDTO().getReceiver().getRoleDTO().getName());

        //     userDTO.setRoleDTO(roleDTO);

        messageDT.setReceiver(userDTO);


        com.megatravel.dtos.admin.UserDTO userDT = new com.megatravel.dtos.admin.UserDTO();
        //  userDT.setActivatedUser(input.getMessageDTO().getSender().isActivatedUser());
        //   userDT.setBirthday(input.getMessageDTO().getSender().getBirthday());
        //  userDT.setCountry(input.getMessageDTO().getSender().getCountry());
        userDT.setEmail(sender.get().getEmail());
        userDT.setId(sender.get().getId());
        //userDT.setLastName(input.getMessageDTO().getSender().getLastName());
        // userDT.setName(input.getMessageDTO().getSender().getName());
        //  com.megatravel.dtos.admin.RoleDTO roleDT = new com.megatravel.dtos.admin.RoleDTO();
        //   roleDT.setId(input.getMessageDTO().getSender().getRoleDTO().getId());
        //    roleDT.setName(input.getMessageDTO().getSender().getRoleDTO().getName());

        //     userDT.setRoleDTO(roleDT);

        messageDT.setSender(userDT);

        messageDT.setLastChangedDate(new Date());

        System.out.println("DPSAP DPVDE");

        Message c = messageService.sendMessageWithChatroom(input.getArg0(),messageDT);

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setId(c.getId());
        messageDTO.setMsg(c.getMsg());

        UserDTO s = new UserDTO();
       /* s.setActivatedUser(userDTO.isActivatedUser());
        s.setBirthday(userDTO.getBirthday());
        s.setCountry(userDTO.getCountry());*/
        s.setEmail(userDTO.getEmail());
        s.setId(userDTO.getId());
       /* s.setLastName(userDTO.getLastName());
        s.setName(userDTO.getName());
        RoleDTO r = new RoleDTO();
        r.setId(userDTO.getRoleDTO().getId());
        r.setName(userDTO.getRoleDTO().getName());

        s.setRoleDTO(r);*/


        UserDTO g = new UserDTO();
      /*  g.setActivatedUser(userDT.isActivatedUser());
        g.setBirthday(userDT.getBirthday());
        g.setCountry(userDT.getCountry());*/
        g.setEmail(userDT.getEmail());
        g.setId(userDT.getId());
    /*    g.setLastName(userDT.getLastName());
        g.setName(userDT.getName());
        RoleDTO rr = new RoleDTO();
        rr.setId(userDT.getRoleDTO().getId());
        rr.setName(userDT.getRoleDTO().getName());

        g.setRoleDTO(rr);*/

        messageDTO.setReceiver(s);
        messageDTO.setSender(g);
        messageDTO.setTimeStamp(c.getTimeStamp());

        ChatRoomDTO cc = new ChatRoomDTO();

        cc.setId(c.getChatRoom().getId());
       // cc.setName(c.getChatRoom().getName());

        messageDTO.setChatRoomDTO(cc);
        System.out.println("DPSAP DPVDE");


        response.setMessageDTO(messageDTO);
        return response;


    }

}