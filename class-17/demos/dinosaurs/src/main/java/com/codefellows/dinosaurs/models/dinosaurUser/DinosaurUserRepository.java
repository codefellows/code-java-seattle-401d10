package com.codefellows.dinosaurs.models.dinosaurUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DinosaurUserRepository extends JpaRepository<DinosaurUser, Long> {
//    as long as the field `username` exists on our DinosaurUser
    public DinosaurUser findByUsername(String username);

}
