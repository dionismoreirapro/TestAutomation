#language: pt

  Funcionalidade: Realizar compra de produto best sellers
    Cenario: Adicionar produtos best sellers no carrinho
      Dado selecionar todos os produtos best sellers
      Quando colocar os produtos dentro do carrinho
      Entao deve exportar um arquivo no formato Json