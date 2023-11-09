package za.ac.mycput.lyleesauexpensetrackerapp.ui.home

import androidx.lifecycle.ViewModel
import za.ac.mycput.lyleesauexpensetrackerapp.data.entity.Expense

class HomeViewModel() : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class HomeUiState(val expenseList: List<Expense> = listOf())