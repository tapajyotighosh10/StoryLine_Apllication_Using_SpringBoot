package com.blogging.app.repositories;

import com.blogging.app.entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {

}
