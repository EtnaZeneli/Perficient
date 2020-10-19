package net.javaproject.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaproject.springboot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
