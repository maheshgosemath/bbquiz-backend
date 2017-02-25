package in.theuniquemedia.brainbout.common.repository.impl;

import in.theuniquemedia.brainbout.common.repository.IRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by mahesh on 2/20/17.
 */
@Repository
public class RepositoryImpl<E, PK extends Serializable> implements IRepository<E, PK> {

    @Autowired
    protected SessionFactory sessionFactory;


    @Override
    @SuppressWarnings("unchecked")
    public PK save(E newInstance)
    {
        PK returnPK = (PK) sessionFactory.getCurrentSession().save(newInstance);
        return returnPK;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E merge(E detachedInstance)
    {
        E returnE = (E) sessionFactory.getCurrentSession().merge(detachedInstance);
        return returnE;
    }

    @Override
    public void delete(E persistentObject)
    {
        sessionFactory.getCurrentSession().delete(persistentObject);
    }

    @Override
    @SuppressWarnings("unchecked")
    public E findById(Class<E> persistentClass, PK id)
    {
        E returnE = (E) sessionFactory.getCurrentSession().get(persistentClass, id);
        return returnE;
    }

    protected void setQueryParams(Query query, Map<String, Object> queryParams)
    {
        String[] params = query.getNamedParameters();
        if (params != null)
        {
            for (String param : params)
            {
                if (queryParams.get(param) instanceof Collection)
                {
                    query.setParameterList(param, (List<?>) queryParams.get(param));
                }
                else
                {
                    query.setParameter(param, queryParams.get(param));
                }

            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByNamedQuery(String queryName, Map<String, Object> queryParams)
    {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        setQueryParams(query, queryParams);
        List<E> returnList = query.list();
        return returnList;
    }



    @Override
    public List<E> findByNamedQuery(String queryName)
    {
        List<E> returnList = findByNamedQuery(queryName, null);
        return returnList;
    }


    public Integer findCountByNameQuery(String queryName)
    {
        Integer count;
        Query query=sessionFactory.getCurrentSession().getNamedQuery(queryName);
        count=33;
        return count;
    }

    @Override
    public Long findCountByNamedQuery(String queryName, Map<String, Object> queryParams)
    {
        Long count;
        Query query=sessionFactory.getCurrentSession().getNamedQuery(queryName);
        setQueryParams(query, queryParams);
        count = (Long)query.uniqueResult();
        return count;
    }

    @Override
    public List<?> findVOByNamedQuery(Class<?> voClass, String queryName, Map<String, Object> queryParams, int maxFetchCount)
    {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        setQueryParams(query, queryParams);

        if (maxFetchCount > 0) {
            query.setMaxResults(maxFetchCount);
        }
        List<?> returnList = query.setResultTransformer(Transformers.aliasToBean(voClass)).list();
        return returnList;
    }

    @Override
    public List<?> findVOByNamedQuery(Class<?> voClass, String queryName, Map<String, Object> queryParams,int startIndex, int maxFetchCount)
    {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        setQueryParams(query, queryParams);
        if (maxFetchCount > 0) {
            query.setMaxResults(maxFetchCount);
            query.setFirstResult(startIndex);
        }

        List<?> returnList = query.setResultTransformer(Transformers.aliasToBean(voClass)).list();
        return returnList;
    }

    @Override
    public List<?> findVOByNamedQuery(Class<?> voClass, String queryName, Map<String, Object> queryParams)
    {
        return findVOByNamedQuery(voClass, queryName, queryParams, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByNamedQuery(String queryName, Map<String, Object> queryParams, int startIndex, int maxFetchCount)
    {
        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        setQueryParams(query, queryParams);
        if (maxFetchCount > 0) {
            query.setMaxResults(maxFetchCount);
            query.setFirstResult(startIndex);
        }

        List<E> returnList = (ArrayList<E>)query.list();
        return returnList;
    }

    @Override
    public List<?> findObjectListByNamedQuery(String queryName, Map<String, Object> queryParams) {

        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        setQueryParams(query, queryParams);
        List<?> returnList = query.list();
        return returnList;

    }


    @Override
    public List<?> findObjectListByNamedQuery(String queryName, Map<String, Object> queryParams, int startIndex, int maxFetchCount) {

        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryName);
        setQueryParams(query, queryParams);
        if (maxFetchCount > 0) {
            query.setMaxResults(maxFetchCount);
            query.setFirstResult(startIndex);
        }

        List<?> returnList = query.list();
        return returnList;

    }


    @Override
    public List<?> findVOBySQLQuery(Class<?> voClass, String queryString, Map<String, Object> queryParams, int maxFetchCount)
    {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
        setQueryParams(query, queryParams);

        if (maxFetchCount > 0) {
            query.setMaxResults(maxFetchCount);
        }
        List<?> returnList = query.setResultTransformer(Transformers.aliasToBean(voClass)).list();
        return returnList;
    }

    @Override
    public List<?> findBySQLQuery(String queryString, Map<String, Object> queryParams, int maxFetchCount)
    {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
        setQueryParams(query, queryParams);

        if (maxFetchCount > 0) {
            query.setMaxResults(maxFetchCount);
        }
        List<?> returnList = query.list();
        return returnList;
    }

    public void updateBySQLQuery(String queryString){

        Query query = sessionFactory.getCurrentSession().getNamedQuery(queryString);
        query.executeUpdate();

    }

    @Override
    public List findBySQLQueryForEntityMap(String queryString, Map<String, Object> queryParams){
        Query query = sessionFactory.getCurrentSession().createSQLQuery(queryString);
        setQueryParams(query, queryParams);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List returnList = query.list();
        return returnList;
    }
}
