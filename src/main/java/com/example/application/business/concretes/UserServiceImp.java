package com.example.application.business.concretes;

import com.example.application.business.abstracts.UserService;
import com.example.application.model.user.User;
import com.example.application.repository.UserRepository;
import com.example.application.util.modelmapper.ModelMapperService;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        modelMapperService.forRequest().map(user,existingUser);
        userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ListDataProvider<User> getAllUsers() {
        // Fetch all users from the repository
        List<User> users = userRepository.findAll();

        // Return the ListDataProvider with the list of users
        return new ListDataProvider<>(users);
    }



    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }
}
