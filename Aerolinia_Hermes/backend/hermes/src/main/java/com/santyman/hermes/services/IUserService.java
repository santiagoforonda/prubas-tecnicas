package com.santyman.hermes.services;

import java.util.List;
import com.santyman.hermes.dtos.userDTO.UserDto;


public interface IUserService {
    
    public UserDto updateUser(Long id, UserDto userUpdate);

    public List<UserDto> showUser();

    public Boolean deleteUser(Long id);

    public UserDto getById(Long id);

}
