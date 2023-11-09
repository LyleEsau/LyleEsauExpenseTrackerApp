package za.ac.mycput.lyleesauexpensetrackerapp.data.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import za.ac.mycput.lyleesauexpensetrackerapp.data.entity.Expense

interface ExpensesRepository {

    fun getAllExpensesStream(): Flow<List<Expense>>

    fun getExpenseStream(id: Int): Flow<Expense?>

    fun getLastSavedTotalAmount(): LiveData<Double>

    suspend fun insertExpense(expense: Expense)

    suspend fun deleteExpense(expense: Expense)

    suspend fun updateExpense(expense: Expense)
}