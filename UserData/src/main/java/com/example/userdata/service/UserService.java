package com.example.userdata.service;

import com.example.userdata.dto.UserDTO;
import com.example.userdata.entity.UserEntity;
import com.example.userdata.exception.UserException;
import com.example.userdata.repo.UserRepo;
import com.example.userdata.util.EmailSenderService;
import com.example.userdata.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepo repository;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService sender;
    @Override
    public UserEntity saveData(UserDTO user) {
        UserEntity userEntity = new UserEntity(user);
        repository.save(userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> getAllData() {
        List<UserEntity> result = repository.findAll();
        return result;
    }

    @Override
    public Optional<UserEntity> getById(int id) {
        Optional<UserEntity> userEntity = repository.findById(id);
        return userEntity;
    }

    @Override
    public UserEntity getByEmail(String email) {
        UserEntity result = repository.findByEmail(email);
        return result;
    }

    @Override
    public UserEntity forgotpassword(UserDTO user, int id) {
        UserEntity userEntity = repository.findById(id).get();
        if(repository.findById(id).isPresent()){
            userEntity.setPassword(user.getPassword());
            repository.save(userEntity);
            return userEntity;
        }
        else {
            throw new UserException("Id not found.....!");
        }
    }

    @Override
    public UserEntity editData(UserDTO user, String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if(userEntity != null){
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setEmail(user.getEmail());
            userEntity.setAddress(user.getAddress());
            userEntity.setPassword(user.getPassword());
            userEntity.setDob(user.getDob());
            repository.save(userEntity);
            return userEntity;
        }
        else {
            throw new UserException("Email not found");
        }
    }

    @Override
    public String addRecord(UserDTO user) throws Exception{
        UserEntity newUser = new UserEntity(user);
        repository.save(newUser);
        String token = tokenUtil.createToken(newUser.getUserId());
        sender.sendEmail(newUser.getEmail(),"TestMail...!","Hello..."+newUser.getFirstName()+" http://localhost:8080/findByToken/"+token);
        return token;
    }

    @Override
    public UserEntity findByToken(String token) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserEntity> userEntity = repository.findById(id);
        if(userEntity.isPresent()){
            return userEntity.get();
        }else {
            throw new UserException("Id not found");
        }
    }

    @Override
    public String loginUser(String email, String password) {
        UserEntity userEntity = repository.findByEmail(email);
        if(email.equals(userEntity.getEmail()) && password.equals(userEntity.getPassword())){
            return "user has logged in successfully";
        }else{
            throw new UserException("Credentials are invalid");
        }
    }

    @Override
    public UserEntity getDetailsById(int id) {
        UserEntity user = repository.findById(id).get();
        if(repository.findById(id).isPresent()){
            return user;
        }else {
            throw new UserException("User not found.....!");
        }
    }
}
