package com.megatravel.interfaces;

import com.megatravel.dtos.admin.UserDTO;
import com.megatravel.models.admin.User;
import org.springframework.data.domain.Pageable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UserSoapInterface {

    @WebMethod
    UserDTO findOne(Long id);
    @WebMethod
    User save(User user);
    @WebMethod
    String signin(String email, String password);
    @WebMethod
    User signup(User user);
    @WebMethod
    User findByEmail(String email);
    @WebMethod
    List<UserDTO> findAll();
    @WebMethod
    void remove(Long id);
}
