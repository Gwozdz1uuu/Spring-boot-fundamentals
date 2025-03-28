package com.Gwozdz1uu.store.OldStaff;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
