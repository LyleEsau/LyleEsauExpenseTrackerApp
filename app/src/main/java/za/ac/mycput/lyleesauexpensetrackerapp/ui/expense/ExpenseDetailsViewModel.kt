package za.ac.mycput.lyleesauexpensetrackerapp.ui.expense

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ExpenseDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val expenseId: Int = checkNotNull(savedStateHandle[ExpenseDetailsDestination.expenseIdArg])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ExpenseDetailsUiState(
    val outOfStock: Boolean = true,
    val expenseDetails: ExpenseDetails = ExpenseDetails()
)
