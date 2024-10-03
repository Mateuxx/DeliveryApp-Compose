package br.com.alura.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.alura.aluvery.dao.ProductDao
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.ui.states.HomeScreenUiState


class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()
    var uiState: HomeScreenUiState by mutableStateOf(HomeScreenUiState(
        onSearchChange = {
            uiState =
                uiState.copy(
                    searchText = it,
                    searchedProducts = searchedProducts(it)
                ) // faz uma copia do texto atual e coloca o texto novo
        }
    ))
        private set //apenas o viewModel é capaz de alterar



    private fun constainsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true,
        ) ||
                product.description?.contains(
                    text,
                    ignoreCase = true,
                ) ?: false
    }

    private fun searchedProducts(text: String): List<Product> =
        if (text.isNotBlank()) {
            sampleProducts.filter(constainsInNameOrDescription(text)) + dao.products().filter(
                constainsInNameOrDescription(text)
            )

        } else {
            emptyList()
        }
}
