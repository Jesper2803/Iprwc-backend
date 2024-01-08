package com.example.Iprwcbackend.module.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("user with id " + userId + " does not exist"));
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, UpdateUserRequest updateRequest) throws MethodArgumentNotValidException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        existingUser.setFirstName(updateRequest.getFirstName());
        existingUser.setLastName(updateRequest.getLastName());
        existingUser.setEmail(updateRequest.getEmail());

        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            String password = updateRequest.getPassword();
            BindingResult bindingResult = new BeanPropertyBindingResult(updateRequest, "updateRequest");
            bindingResult.addError(new FieldError("updateRequest", "password", "Wachtwoord moet minstens 8 tekens, een hoofdletter, een kleine letter, een getal en een speciaal teken bevatten"));

            if (password.length() < 8 || !password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]*$")) {
                throw new MethodArgumentNotValidException(null, bindingResult);
            }

            existingUser.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
        }
        userRepository.save(existingUser);
    }
}
