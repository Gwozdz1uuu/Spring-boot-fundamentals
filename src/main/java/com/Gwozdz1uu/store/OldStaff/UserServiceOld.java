package com.Gwozdz1uu.store.OldStaff;

import org.springframework.stereotype.Service;

@Service
public class UserServiceOld {
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public UserServiceOld(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        userRepository.save(user);
        notificationService.send("You registered succesfully", user.getEmail());
    }
}
