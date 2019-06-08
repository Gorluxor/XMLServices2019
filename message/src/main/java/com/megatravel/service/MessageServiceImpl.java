package com.megatravel.service;

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
    public List<MessageDTO> getMessages(Long userId) {
        return null;
    }



}
