package br.com.ricardo.tron.repository.helper.fornecedor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.ricardo.tron.model.Fornecedor;
import br.com.ricardo.tron.repository.filter.FornecedorFilter;
import br.com.ricardo.tron.repository.paginacao.PaginacaoUtil;

public class FornecedoresImpl implements FornecedoresQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Fornecedor.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(FornecedorFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Fornecedor.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(FornecedorFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getId())) {
				criteria.add(Restrictions.eq("id", filtro.getId()));
			}
			
			if (!StringUtils.isEmpty(filtro.getCnpj())) {
				criteria.add(Restrictions.ilike("cnpj", filtro.getCnpj(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
						
			
			if (!StringUtils.isEmpty(filtro.getRazaoSocial())) {
				criteria.add(Restrictions.ilike("razaoSocial", filtro.getRazaoSocial(),  MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.eq("descricao", filtro.getDescricao()));
			}
			
			if (!StringUtils.isEmpty(filtro.getEmail())) {
				criteria.add(Restrictions.eq("email", filtro.getEmail()));
			}
			//testar sem
			if (!StringUtils.isEmpty(filtro.getTelefone())) {
				criteria.add(Restrictions.eq("telefone", filtro.getTelefone()));
			}
			//testar sem
			if (!StringUtils.isEmpty(filtro.getCelular())) {
				criteria.add(Restrictions.eq("celular", filtro.getCelular()));
			}
		}
	}

}
