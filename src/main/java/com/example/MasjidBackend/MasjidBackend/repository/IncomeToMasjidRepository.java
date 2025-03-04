package com.example.MasjidBackend.MasjidBackend.repository;

import com.example.MasjidBackend.MasjidBackend.model.IncomeToMasjid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeToMasjidRepository extends JpaRepository<IncomeToMasjid,Long> {


}
