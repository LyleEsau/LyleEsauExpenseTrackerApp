package za.ac.mycput.lyleesauexpensetrackerapp.ui.expense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import za.ac.mycput.lyleesauexpensetrackerapp.data.entity.Expense
import za.ac.mycput.lyleesauexpensetrackerapp.data.repository.ExpensesRepository

class ExpenseEntryViewModel(private val expensesRepository: ExpensesRepository) : ViewModel() {

    val totalAmount: LiveData<Double> = expensesRepository.getLastSavedTotalAmount()


    var expenseUiState by mutableStateOf(ExpenseUiState())
        private set


    fun updateUiState(expenseDetails: ExpenseDetails) {
        expenseUiState =
            ExpenseUiState(expenseDetails = expenseDetails, isEntryValid = validateInput(expenseDetails))
    }

    suspend fun saveExpense() {
        if (validateInput()) {
            expensesRepository.insertExpense(expenseUiState.expenseDetails.toItem())
        }
    }

    private fun validateInput(uiState: ExpenseDetails = expenseUiState.expenseDetails): Boolean {
        return with(uiState) {
            name.isNotBlank()
        }
    }
}

data class ExpenseUiState(
    val expenseDetails: ExpenseDetails = ExpenseDetails(),
    val isEntryValid: Boolean = false
)

data class ExpenseDetails(
    val id: Int = 0,
    val name: String = "",
    val added: String = "",
    val amount: String = ""

)

fun ExpenseDetails.toItem(): Expense = Expense(
    id = id,
    name = name,
    added = added.toDoubleOrNull()?: 0.0,
    amount = amount.toDoubleOrNull() ?: 0.0
)


fun Expense.toExpenseUiState(isEntryValid: Boolean = false): ExpenseUiState = ExpenseUiState(
    expenseDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

fun Expense.toItemDetails(): ExpenseDetails = ExpenseDetails(
    id = id,
    name = name,
    added = added.toString(),
    amount = amount.toString()
)
