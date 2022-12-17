package com.example.data.repository;

import com.example.data.entity.Address;
import com.example.data.entity.Gender;
import com.example.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "inner join Address a on a.id = u.address.id " +
            "where (a.city like %:address% or a.country like %:address%) and u.gender = :gend and u.name like %:name% " +
            "order by u.id desc")
    List<User> search(String address, @Param("gend") Gender gender, String name);

    @Query("select u from User u where u.address = :address")
    List<User> searchByAddress(Address address);

    @Query(value = "select id, address_id, gender, name from users where gender = :gender and name like %:name%", nativeQuery = true)
    List<User> nativeSearch(String gender, String name);

}
