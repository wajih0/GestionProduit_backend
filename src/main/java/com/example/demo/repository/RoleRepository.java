package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Role;
import com.example.demo.entity.ERole;


public interface RoleRepository extends JpaRepository<Role,Long>{
Optional<Role>  findByName(ERole name);
}
