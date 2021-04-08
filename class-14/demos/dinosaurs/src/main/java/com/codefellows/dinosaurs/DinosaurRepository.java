package com.codefellows.dinosaurs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DinosaurRepository  extends JpaRepository<Dinosaur, Long> {
    public Dinosaur findByUsername(String username);
}
