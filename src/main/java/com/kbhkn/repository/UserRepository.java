package com.kbhkn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kbhkn.domain.User;

/**
 * Created by kbhkn on 20.09.2016 16:39.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
