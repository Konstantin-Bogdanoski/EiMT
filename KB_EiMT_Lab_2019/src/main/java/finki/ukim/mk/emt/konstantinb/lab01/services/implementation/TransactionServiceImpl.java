package finki.ukim.mk.emt.konstantinb.lab01.services.implementation;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.TransactionAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.TransactionNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.UserNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Transaction;
import finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence.PersistentProductRepository;
import finki.ukim.mk.emt.konstantinb.lab01.repositories.persistence.PersistentTransactionRepository;
import finki.ukim.mk.emt.konstantinb.lab01.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private PersistentTransactionRepository transactionRepository;

    public TransactionServiceImpl(PersistentTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction addNewTransaction(Transaction transaction) throws TransactionAlreadyExistsException {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(long ID) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionRepository.findById(ID);
        if (transaction.isPresent())
            return transaction.get();
        return null;
    }

    @Override
    public List<Transaction> getAllByUsername(String username) throws UserNotFoundException {
        return transactionRepository.findAllByUsername(username);
    }
}
