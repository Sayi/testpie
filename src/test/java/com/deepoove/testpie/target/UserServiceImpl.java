package com.deepoove.testpie.target;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class UserServiceImpl implements UserService {

    // real data
    private static List<User> users = LongStream.of(1590000, 1581111, 1360000)
            .mapToObj(phone -> new User(phone)).map(user -> {
                user.setName("Sayi");
                return user;
            }).collect(Collectors.toList());

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User find(long phone) {
        return users.stream().filter(user -> user.getPhone() == phone).findFirst().get();
    }

    @Override
    public void add(User user) {
        if (null == user) throw new RuntimeException("null user");
        User findUser = find(user.getPhone());
        if (null != findUser) throw new RuntimeException("user is exist");
        users.add(user);

    }

    @Override
    public void update(User user) {
        if (null == user) throw new RuntimeException("null user");
        User findUser = find(user.getPhone());
        if (null == findUser) throw new RuntimeException("user is not exist");
        users.remove(findUser);
        users.add(user);
    }

    @Override
    public boolean delete(long phone) {
        User findUser = find(phone);
        if (null == findUser) throw new RuntimeException("user is not exist");
        users.remove(findUser);
        return true;
    }

}
