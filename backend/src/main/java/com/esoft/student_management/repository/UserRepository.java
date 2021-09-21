package com.esoft.student_management.repository;


import com.esoft.student_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsernameAndPassword(String name, String password);

    User findByUsername(String userName);

    User findByNic(String nic);

    User findByName(String name);
}
