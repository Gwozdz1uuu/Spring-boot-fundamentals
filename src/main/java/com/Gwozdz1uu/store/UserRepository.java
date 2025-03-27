package com.Gwozdz1uu.store;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
