package za.ac.mycput.lyleesauexpensetrackerapp.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import za.ac.mycput.lyleesauexpensetrackerapp.data.dao.ExpenseDao
import za.ac.mycput.lyleesauexpensetrackerapp.data.entity.Expense

class OfflineExpenseRepository(private val expenseDao: ExpenseDao) : ExpensesRepository {
    override fun getAllExpensesStream(): Flow<List<Expense>> = expenseDao.getAllExpenses()

    override fun getExpenseStream(id: Int): Flow<Expense?> = expenseDao.getExpense(id)

    override suspend fun insertExpense(expense: Expense) = expenseDao.insert(expense)

    override suspend fun deleteExpense(expense: Expense) = expenseDao.delete(expense)

    override suspend fun updateExpense(expense: Expense) = expenseDao.update(expense)

    override fun getLastSavedTotalAmount(): LiveData<Double> {
        return expenseDao.getLastSavedTotalAmount()
    }
}