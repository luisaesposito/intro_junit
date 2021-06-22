package carrinho;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import produto.Produto;
import produto.ProdutoNaoEncontradoException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Classe para teste da calculadora")
public class CarrinhoTest {

    private Carrinho carrinho;
    private final Produto banana = new Produto("banana", 0.79);
    private final Produto morango = new Produto("morango", 7.99);

    @BeforeEach
    public void setup() {
        carrinho = new Carrinho();
    }

    @Test
    public void testAdicionarItem() {
        assertTrue(carrinho.getQtdeItems() == 0);
        carrinho.addItem(new Produto("banana", 0.99));
        assertEquals(1, carrinho.getQtdeItems());
    }

    @Test
    public void testRemoverItem() throws ProdutoNaoEncontradoException {
        carrinho.addItem(banana);
        carrinho.addItem(morango);
        assertEquals(2, carrinho.getQtdeItems());
        carrinho.removeItem(banana);
        assertTrue(carrinho.getQtdeItems() == 1);
    }

    @Test
    public void testRemoverItemCarrinhoVazio() {
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> carrinho.removeItem(new Produto("novo item", 3.0)));
    }

    @Test
    public void testRemoverItemInexistente() {
        carrinho.addItem(morango);
        assertThrows(ProdutoNaoEncontradoException.class,
                () -> carrinho.removeItem(banana));
    }

    @Test
    public void testEsvaziarCarrinho() {
        carrinho.addItem(banana);
        carrinho.addItem(banana);
        carrinho.addItem(morango);
        assertEquals(3, carrinho.getQtdeItems());
        carrinho.esvazia();
        assertTrue(carrinho.getQtdeItems() == 0);
    }

    @Test
    public void testValorTotalCarrinhoVazio() {
        assertTrue(carrinho.getValorTotal() == 0.0);
    }

    @Test
    public void testValorTotal() {
        carrinho.addItem(banana);
        carrinho.addItem(banana);
        assertEquals(2*banana.getPreco(), carrinho.getValorTotal());
    }
}
