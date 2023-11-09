package za.ac.mycput.lyleesauexpensetrackerapp.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import za.ac.mycput.lyleesauexpensetrackerapp.R
import za.ac.mycput.lyleesauexpensetrackerapp.TrackerAppTopAppBar
import za.ac.mycput.lyleesauexpensetrackerapp.data.entity.Expense
import za.ac.mycput.lyleesauexpensetrackerapp.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(

    navigateToExpenseEntry: () -> Unit,
    navigateToExpenseUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier.background(brownBackhround)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection

        )  .background(brownBackhround),
        topBar = {
            TrackerAppTopAppBar(
                modifier = Modifier.background(brownBackhround),
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToExpenseEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Title"
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            expenseList = listOf(),
            onExpenseClick = navigateToExpenseUpdate,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        )
    }
}

val brownBackhround = Color(0xFF6d6056)
val creamBackground = Color(0xFFdbc1ac)
@Composable
private fun HomeBody(
    expenseList: List<Expense>, onExpenseClick: (Int) -> Unit, modifier: Modifier = Modifier.background(
        brownBackhround)
) {
    Column(
        modifier.background(creamBackground),
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        if (expenseList.isEmpty()) {
            Text(
                text = "No Expenses Yet !\nTry Adding One Now",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
            )
        } else {
            ExpenseList(
                expenseList = expenseList,
                onExpenseClick = { onExpenseClick(it.id) },
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}


@Composable
private fun ExpenseList(
    expenseList: List<Expense>, onExpenseClick: (Expense) -> Unit, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.background(creamBackground)) {
        items(items = expenseList, key = { it.id }) { expense ->
            ExpenseInventory(expense = expense,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onExpenseClick(expense) })
        }
    }
}

@Composable
private fun ExpenseInventory(
    expense: Expense, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = expense.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(

                    text = "R" + expense.amount.toString()

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    HomeBody(listOf(
        Expense(1, "Game", 20.0, 123.0), Expense(2, "Pen", 20.0, 20.0), Expense(3, "TV",20.0, 60.0)
    ), onExpenseClick = {})
}


@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    HomeBody(listOf(), onExpenseClick = {})
}

@Preview(showBackground = true)
@Composable
fun ExpensePreview() {

    ExpenseInventory(
        Expense(1, "Game", 20.0, 90.0),
    )

}
