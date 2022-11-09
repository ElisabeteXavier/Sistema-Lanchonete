package br.com.elisabete.lanchonete.infraestrutura;

import br.com.elisabete.lanchonete.modelos.Cliente;
import br.com.elisabete.lanchonete.modelos.Estado;
import br.com.elisabete.lanchonete.repositorios.EstadoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Estado> findAll() {
        return manager.createQuery("from Estado",Estado.class).getResultList();
    }
    @Override
    public Estado findById(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    @Transactional
    public Estado save(Estado estado) {
        System.out.println("estado: "+estado.getId());
        return manager.merge(estado);


    }
    @Override
    @Transactional
    public void deleteById(Estado estado) {
        System.out.println("estado: "+estado.getId());
        estado = findById(estado.getId());
        manager.remove(estado);



    }

}
