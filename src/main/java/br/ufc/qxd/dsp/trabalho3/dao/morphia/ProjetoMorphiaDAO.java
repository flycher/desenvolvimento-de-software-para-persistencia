package br.ufc.qxd.dsp.trabalho3.dao.morphia;

import br.ufc.qxd.dsp.trabalho3.dao.ProjetoDAO;
import br.ufc.qxd.dsp.trabalho3.model.Departamento;
import br.ufc.qxd.dsp.trabalho3.model.Projeto;
import br.ufc.qxd.dsp.trabalho3.morphia.MorphiaUtil;

import java.util.List;

public class ProjetoMorphiaDAO extends GenericMorphiaDAO<Projeto> implements ProjetoDAO {

    public ProjetoMorphiaDAO() {
        super(Projeto.class);
    }
    @Override
    public List<Projeto> findByNomeList(String nome) {
        List<Projeto> projs = MorphiaUtil.getDatastore().createQuery(Projeto.class)
                .field("nome")
                .contains(nome).asList();
        return projs;

    }
    @Override
    public Projeto findByNome(String nome) {
        Projeto projs = MorphiaUtil.getDatastore().createQuery(Projeto.class)
                .field("nome")
                .contains(nome).get();
        return projs;

    }

    public List<Departamento> findByNumeroDep(int numero) {
        List<Departamento> dep = MorphiaUtil.getDatastore().createQuery(Departamento.class)
                .field("numero")
                .equal(numero).asList();
        return dep;



    }
}
