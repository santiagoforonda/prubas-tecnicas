package com.santyman.hermes.services.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santyman.hermes.dtos.userDTO.MapperUserDto;
import com.santyman.hermes.dtos.userDTO.UserDto;
import com.santyman.hermes.entities.Booking;
import com.santyman.hermes.entities.User;
import com.santyman.hermes.exceptions.customExceptions.UserNotFoundException;
import com.santyman.hermes.exceptions.validators.personalInfoValidator;
import com.santyman.hermes.repositories.BookingRepository;
import com.santyman.hermes.repositories.UserRepository;
import com.santyman.hermes.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private MapperUserDto mapeador;


  
   @Override
    public UserDto updateUser(Long id, UserDto userUpdate) {
       Optional<User> userInDb = userRepo.findById(id);
       Set<Booking> bookings = bookingRepo.findByUserId(id);
        Optional<User> userToUpdate = userInDb.map(oldUserDb ->{
         personalInfoValidator.validateEmail(userUpdate.getEmail());
         personalInfoValidator.validatePersonalName(userUpdate.getName());
         personalInfoValidator.validatePersonalLastname(userUpdate.getLastname());
         personalInfoValidator.validatePassword(userUpdate.getPassword());
            User userAux = oldUserDb.updateWith(mapeador.dtoToEntity(userUpdate));
            userAux.setBookings(bookings);
            return userRepo.save(userAux);
       });
       return mapeador.optionalToDto(userToUpdate);
    }

    @Override
    public List<UserDto> showUser() {
        List<User> users = userRepo.findAll();
        List<UserDto> usersDto = users.stream().map(user->mapeador.entityToDto(user)).collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public Boolean deleteUser(Long id) {
       User userIsDelete = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
       if(userIsDelete != null){
        userRepo.deleteById(id);
        return true;
       }
       return false;
    }

    @Override
    public UserDto getById(Long id) {
       User user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException(id));
       return mapeador.entityToDto(user);
    }
    
}
