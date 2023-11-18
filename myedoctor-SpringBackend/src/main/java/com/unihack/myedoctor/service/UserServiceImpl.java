package com.unihack.myedoctor.service;

import com.unihack.myedoctor.exception.UserNotFoundException;
import com.unihack.myedoctor.model.User;
import com.unihack.myedoctor.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(String userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setEmail(user.getEmail());
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setLocation(user.getLocation());
            user.setDateOfBirth(user.getDateOfBirth());
            user.setOccupationField(user.getOccupationField());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + "not found"));
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
