package com.esoft.student_management.repository;


import com.esoft.student_management.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {


    UserRole findByName(String name);
}
