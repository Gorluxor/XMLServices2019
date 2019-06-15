package com.megatravel.repository;

import com.megatravel.models.messages.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {


    Page<ChatRoom> findAllByReservation_User_Id(Long id, Pageable pageable);
}
