package com.example.finalloanproject.repositories;

import com.example.finalloanproject.entities.Loantable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoantableRepository extends JpaRepository<Loantable,Long> {

    List<Loantable> findLoantableByClientno(Long clientno);
}
