package za.ac.mycput.lyleesauexpensetrackerapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import za.ac.mycput.lyleesauexpensetrackerapp.data.entity.Expense

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * from expense WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense>

    @Query("SELECT amount FROM expense ORDER BY id DESC LIMIT 1")
    fun getLastSavedTotalAmount(): LiveData<Double>

    @Query("SELECT * from expense ORDER BY name ASC")
    fun getAllExpenses(): Flow<List<Expense>>
}
