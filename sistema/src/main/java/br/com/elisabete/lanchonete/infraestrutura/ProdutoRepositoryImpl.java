package br.com.elisabete.lanchonete.infraestrutura;

import br.com.elisabete.lanchonete.modelos.Produto;
import br.com.elisabete.lanchonete.repositorios.ProdutoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepository {

    @PersistenceContext
    private EntityManager manager;
  @Override
    public List<Produto> findAll() {
        return manager.createQuery("from Produto",Produto.class).getResultList();
    }
    @Override
    public Produto findById(Long id) {
        return manager.find(Produto.class, id);
    }

    @Override
    @Transactional
    public Produto save(Produto produto) {
        System.out.println("produto: "+produto.getId());
        return manager.merge(produto);
    }
    @Override
    @Transactional
    public void deleteById(Produto produto) {
        System.out.println("produto: "+produto.getId());
        produto = findById(produto.getId());
        manager.remove(produto);
    }


}
