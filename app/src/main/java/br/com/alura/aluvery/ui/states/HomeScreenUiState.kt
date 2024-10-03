package br.com.alura.aluvery.ui.states

import br.com.alura.aluvery.model.Product
//State Holder
//Toda lógica que era necessária dentro da ui migrada para cá
// O que deve ser visivel ou não
//Esses quatro campos representam o estado atual da tela. Eles dizem à interface de usuário
// o que exibir e como exibir com base no que está acontecendo na aplicação.
data class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}