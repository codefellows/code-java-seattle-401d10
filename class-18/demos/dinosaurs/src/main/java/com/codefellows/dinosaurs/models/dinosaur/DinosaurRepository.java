package com.codefellows.dinosaurs.models.dinosaur;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DinosaurRepository extends JpaRepository<Dinosaur, Long> {
}
