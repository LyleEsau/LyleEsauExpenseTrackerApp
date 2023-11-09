package za.ac.mycput.lyleesauexpensetrackerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseDetailsDestination
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseDetailsScreen
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseEditDestination
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseEditScreen
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseEntryDestination
import za.ac.mycput.lyleesauexpensetrackerapp.ui.expense.ExpenseEntryScreen
import za.ac.mycput.lyleesauexpensetrackerapp.ui.home.HomeDestination
import za.ac.mycput.lyleesauexpensetrackerapp.ui.home.HomeScreen

@Composable
fun ExpenseNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen( navigateToExpenseEntry = { navController.navigate(ExpenseEntryDestination.route) },
                navigateToExpenseUpdate = {
                    navController.navigate("${ExpenseDetailsDestination.route}/${it}")
                })
        }
        composable(route = ExpenseEntryDestination.route) {
            ExpenseEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = ExpenseDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ExpenseDetailsDestination.expenseIdArg) {
                type = NavType.IntType
            })
        ) {
            ExpenseDetailsScreen(
                navigateToEditItem =
                {
                    navController.navigate("${ExpenseEditDestination.route}/$it")
                },
                navigateBack = { navController.navigateUp() })
        }
        composable(
            route = ExpenseEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ExpenseEditDestination.expenseIdArg) {
                type = NavType.IntType
            })
        ) {
            ExpenseEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}



