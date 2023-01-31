package com.iuran_bulanan_warga.Models.Repositories;

import com.iuran_bulanan_warga.Models.Entities.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

    @Query(value = "SELECT CASE WHEN count(t) > 0 THEN true ELSE false END " +
            "FROM Transactions t " +
            "WHERE t.house.id = :houseId " +
            "ORDER BY t.date DESC")
    boolean findLastTransactionsByHouseId(@Param("houseId") Integer houseId);

    @Query(value = "SELECT " +
            "PERIOD_DIFF(DATE_FORMAT(CURRENT_DATE, '%Y%m'),DATE_FORMAT(t.date, '%Y%m')) as DiffMonth " +
            "FROM Transactions t " +
            "WHERE PERIOD_DIFF(DATE_FORMAT(CURRENT_DATE, '%Y%m'),DATE_FORMAT(t.date, '%Y%m')) >= 1 " +
            "AND t.house.id = :houseId " +
            "ORDER BY t.date DESC")
    Integer findDiffMonth(@Param("houseId") Integer houseId);
}
