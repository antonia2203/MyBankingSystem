package repo;

import entity.Account;
import entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId "
            + "AND (:type IS NULL OR t.type = :type)"
            + "AND (:startDate IS NULL OR t.date >= :startDate) "
            + "AND(:endDate IS NULL OR t.date <= :endDate)")
    List<Transaction> findByAccountIdAndOptionalFilters(@Param("accountID") Long accountId,
                                                        @Param("type") String type , @Param("startDate")LocalDateTime startDate,
                                                        @Param("endDate") LocalDateTime endDate);

}
