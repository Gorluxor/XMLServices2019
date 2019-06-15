package com.megatravel.repository;

import com.megatravel.models.messages.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository extends JpaRepository<Message, Long>{


    @Query("select m from Message m where m.chatRoom = ?1 and m.receiver.id = ?2 or m.sender.id = ?2")
    Page<Message> allMessagesToChat(Long charRoomId, Long userId, Pageable pageable);
}
