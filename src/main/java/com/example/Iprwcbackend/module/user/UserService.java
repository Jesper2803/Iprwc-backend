package com.example.Iprwcbackend.module.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId){
        return userRepository.findById(userId);
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String firstName, String lastName, String password, String email) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        if (firstName != null && firstName.length() > 0){
            user.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0){
            user.setLastName(lastName);
        }

        if (email != null && email.length() > 0){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
//            if (userOptional.isPresent()){
//                throw new IllegalStateException("email taken");
//            }
            user.setEmail(email);
        }

        if (password != null && password.length() > 0){
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
        }
    }
}
