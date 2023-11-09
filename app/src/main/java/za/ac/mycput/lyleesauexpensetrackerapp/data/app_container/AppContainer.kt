package za.ac.mycput.lyleesauexpensetrackerapp.data.app_container

import android.content.Context
import za.ac.mycput.lyleesauexpensetrackerapp.data.database.ExpenseDatabase
import za.ac.mycput.lyleesauexpensetrackerapp.data.repository.ExpensesRepository
import za.ac.mycput.lyleesauexpensetrackerapp.data.repository.OfflineExpenseRepository

interface AppContainer {
    val expensesRepository: ExpensesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val expensesRepository: ExpensesRepository by lazy {
        OfflineExpenseRepository(ExpenseDatabase.getInstance(context).expenseDao())
    }
}