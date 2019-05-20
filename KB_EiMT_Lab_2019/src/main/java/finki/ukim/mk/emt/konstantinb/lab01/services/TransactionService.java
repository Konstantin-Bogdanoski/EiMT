package finki.ukim.mk.emt.konstantinb.lab01.services;

import finki.ukim.mk.emt.konstantinb.lab01.exceptions.TransactionAlreadyExistsException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.TransactionNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.exceptions.UserNotFoundException;
import finki.ukim.mk.emt.konstantinb.lab01.models.Transaction;

import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface TransactionService {
    Transaction addNewTransaction(Transaction transaction) throws TransactionAlreadyExistsException;

    List<Transaction> getAllTransactions();

    Transaction getById(long ID) throws TransactionNotFoundException;

    List<Transaction> getAllByUsername(String username) throws UserNotFoundException;
}
