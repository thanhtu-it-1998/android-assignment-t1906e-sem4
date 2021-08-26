package com.fptaptech.AsmApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fptaptech.AsmApi.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
