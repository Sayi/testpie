package com.deepoove.testpie.target;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User find(long phone);

    void add(User user);

    void update(User user);

    boolean delete(long phone);
}
