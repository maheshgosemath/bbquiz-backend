package in.theuniquemedia.brainbout.common.repository;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by mahesh on 2/19/17.
 */
@Repository
public interface IRepository<E, PK extends Serializable> {
    /**
     * Method to create a new object which returns the primary key
     * @param E Instance of entity object to be persisted
     */
    PK save(E newInstance);

    /**
     * Method to merge a persistent object
     * @param E Entity Class to be merged
     */
    E merge(E detachedInstance);

    /**
     * Method to delete a persistent object
     * @param E Entity Class to be deleted
     */
    void delete(E persistentObject);

    /**
     * Method to fetch the persistent object based on the primary key
     * @param E Entity Class to be obtained
     * @param id Primary key of the entity class
     * @return Entity class object
     */
    E findById(Class<E> persistentClass, PK id);

    /**
     * Method to execute a fetch query
     * @param queryName - Named query name
     * @param queryParams - Query parameters
     * @return List of domain objects
     */
    List<E> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * Method to execute a fetch query
     * @param queryName - Named query name
     * @param queryParams - Query parameters
     * @return List of domain objects
     */
    List<E> findByNamedQuery(String queryName);
    Integer findCountByNameQuery(String queryName);

    /**
     * Method to execute a fetch query
     * @param voClass - Value object class
     * @param queryName - Named query name
     * @param queryParams - Query parameters
     * @return List of value objects
     */
    List<?> findVOByNamedQuery(Class<?> voClass, String queryName, Map<String, Object> queryParams);

    /**
     * Method to execute a fetch query with a maximum results parameter
     * @param voClass
     * @param queryName
     * @param queryParams
     * @param maxFetchCount
     * @return
     */
    public List<?> findVOByNamedQuery(Class<?> voClass, String queryName, Map<String, Object> queryParams, int maxFetchCount);

    /**
     * Method to execute a fetch query with a maximum results parameter
     * @param voClass
     * @param queryName
     * @param queryParams
     * @param startIndex - Next start data fetching index parameters
     * @param maxFetchCount
     * @return List of value objects
     */
    public List<?> findVOByNamedQuery(Class<?> voClass, String queryName, Map<String, Object> queryParams,int startIndex, int maxFetchCount);

    /**
     * Method to execute a fetch query
     * @param queryName - Named query name
     * @param queryParams - Query parameters
     * @param startIndex - Next start data fetching index parameters
     * @param maxFetchCount - Maximum number of records fetched at one time parameters
     * @return List of domain objects
     */
    List<E> findByNamedQuery(String queryName, Map<String, Object> queryParams, int startIndex, int maxFetchCount);

    /**
     * Method to execute a fetch query
     * @param queryName - Named query name
     * @param queryParams - Query parameters
     * @return List of Objects
     */
    public List<?> findObjectListByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * Method to execute a fetch query
     * @param queryName - Named query name
     * @param queryParams - Query parameters
     * @param startIndex - Next start data fetching index parameters
     * @param maxFetchCount - Maximum number of records fetched at one time parameters
     * @return List of Objects
     */
    public List<?> findObjectListByNamedQuery(String queryName, Map<String, Object> queryParams, int startIndex, int maxFetchCount);

    public List<?> findVOBySQLQuery(Class<?> voClass, String queryString, Map<String, Object> queryParams, int maxFetchCount);

    public List<?> findBySQLQuery(String queryString, Map<String, Object> queryParams, int maxFetchCount);

    public List findBySQLQueryForEntityMap(String queryString, Map<String, Object> queryParams);

    public void updateBySQLQuery(String queryString);

    public Long findCountByNamedQuery(String queryName, Map<String, Object> queryParams);
}
