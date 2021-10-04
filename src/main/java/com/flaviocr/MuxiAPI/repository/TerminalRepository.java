package com.flaviocr.MuxiAPI.repository;

import com.flaviocr.MuxiAPI.model.TerminalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalRepository extends JpaRepository<TerminalModel, Integer> {

}
