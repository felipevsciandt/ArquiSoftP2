package com.felipe.arqsoftware.demo.repository;

import com.felipe.arqsoftware.demo.model.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> {
}
