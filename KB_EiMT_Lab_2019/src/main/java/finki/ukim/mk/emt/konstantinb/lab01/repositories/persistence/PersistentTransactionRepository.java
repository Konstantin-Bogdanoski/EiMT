package finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence;

import finki.ukim.mk.emt.konstantinb.lab01.models.Product;
import finki.ukim.mk.emt.konstantinb.lab01.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface PersistentTransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Get all transactions
     */
    List<Transaction> findAll();

    /**
     * Get transaction by Id
     */
    Optional<Transaction> findById(@Param("ID") Long ID);

    /**
     * Get transactions by products
     */
    List<Transaction> findAllByPurchasedProductId(@Param("productID") Long productID);

    /**
     * Get all transactions by user
     */
    List<Transaction> findAllByUsername(@Param("username") String username);

    /**
     * Save transaction
     */
    @Transactional
    @Modifying
    Transaction save(Transaction transaction);

    /**
     * Remove transaction
     */
    @Transactional
    @Modifying
    void delete(Transaction transaction);
}
