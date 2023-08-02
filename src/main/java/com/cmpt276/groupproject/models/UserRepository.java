package com.cmpt276.groupproject.models;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByNameAndPassword(String name,String password);
    List<User> findByUid(int uid);
}
