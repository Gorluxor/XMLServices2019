package com.megatravel.repository;

import com.megatravel.models.messages.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("select ch from ChatRoom ch WHERE ch.id in (select m.chatRoom.id from Message m where m.chatRoom.id = ch.id and (m.receiver.id = ?1 or m.sender.id = ?1))")
    Page<ChatRoom> allByUserId(Long id, Pageable pageable);

    @Query("select ch from ChatRoom ch WHERE ch.id in (select m.chatRoom.id from Message m where m.chatRoom.id = ch.id and (m.receiver.id = ?1 or m.sender.id = ?1))")
    List<ChatRoom> allByUserId(Long id);
}
