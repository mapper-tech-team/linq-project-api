package com.example.linqprojectapi.repository;


import com.example.linqprojectapi.model.Empresa;
import com.example.linqprojectapi.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
