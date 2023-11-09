package za.ac.mycput.lyleesauexpensetrackerapp.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

import za.ac.mycput.lyleesauexpensetrackerapp.TrackerApplication
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseDetailsViewModel
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseEditViewModel
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseEntryViewModel
import za.ac.mycput.lyleesauexpensetrackerapp.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ExpenseEditViewModel(
                this.createSavedStateHandle()
            )
        }

        initializer {
            ExpenseEntryViewModel(trackerApplication().container.expensesRepository)
        }

        initializer {
            ExpenseDetailsViewModel(
                this.createSavedStateHandle()
            )
        }

        initializer {
            HomeViewModel()
        }
    }
}

fun CreationExtras.trackerApplication(): TrackerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TrackerApplication)