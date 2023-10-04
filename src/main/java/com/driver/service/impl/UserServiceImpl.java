package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(userDto.getUserId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());

        UserEntity savedUser = userRepository.save(userEntity);

        UserDto response = new UserDto();

        response.setId(savedUser.getId());
        response.setUserId(savedUser.getUserId());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            return new UserDto();
        }

        UserDto response = new UserDto();

        response.setUserId(userEntity.getUserId());
        response.setId(userEntity.getId());
        response.setEmail(userEntity.getEmail());
        response.setFirstName(userEntity.getFirstName());
        response.setLastName(userEntity.getLastName());

        return response;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null){
            return new UserDto();
        }

        UserDto response = new UserDto();

        response.setUserId(userEntity.getUserId());
        response.setId(userEntity.getId());
        response.setEmail(userEntity.getEmail());
        response.setFirstName(userEntity.getFirstName());
        response.setLastName(userEntity.getLastName());

        return response;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {

        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity == null){
            return new UserDto();
        }

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());

        UserEntity updatedUser = userRepository.save(userEntity);

        UserDto response = new UserDto();

        response.setUserId(updatedUser.getUserId());
        response.setId(updatedUser.getId());
        response.setEmail(updatedUser.getEmail());
        response.setFirstName(updatedUser.getFirstName());
        response.setLastName(updatedUser.getLastName());

        return response;



    }

    @Override
    public void deleteUser(String userId) throws Exception {

        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);

    }

    @Override
    public List<UserDto> getUsers() {

        Iterable<UserEntity> users = userRepository.findAll();

        List<UserDto> ans = new ArrayList<>();

        for(UserEntity userEntity : users){

            UserDto response = new UserDto();

            response.setUserId(userEntity.getUserId());
            response.setFirstName(userEntity.getFirstName());
            response.setId(userEntity.getId());
            response.setLastName(userEntity.getLastName());
            response.setEmail(userEntity.getEmail());

            ans.add(response);
        }

        return ans;
    }
}