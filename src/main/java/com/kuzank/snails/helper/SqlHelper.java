package com.kuzank.snails.helper;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author kuzank 2020/1/31
 */
@Service
public class SqlHelper {

    @PersistenceContext
    private EntityManager entityManager;


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void save(Object entity) {
        entityManager.persist(entity);
    }

    public void update(Object entity) {
        entityManager.merge(entity);
    }

    public <T> void delete(Class<T> entityClass, Object entityid) {
        delete(entityClass, new Object[]{entityid});
    }

    public <T> void delete(Class<T> entityClass, Object[] entityids) {
        for (Object id : entityids) {
            entityManager.remove(entityManager.getReference(entityClass, id));
        }
    }

    public List<Map<String, Object>> query(String sql) {

        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }

    public List<Map<String, Object>> query(String sql, Map<String, Object> params) {
        Query query = entityManager.createNativeQuery(sql);
        params.forEach((key, val) ->
                query.setParameter(key, val)
        );
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        return query.getResultList();
    }

    @Modifying
    @Transactional
    public int execute(String sql) {
        Query query = entityManager.createNativeQuery(sql);

        return query.executeUpdate();
    }

    @Modifying
    @Transactional
    public Integer execute(String sql, Map<String, Object> params) {
        Query query = entityManager.createNativeQuery(sql);
        params.forEach((key, val) ->
                query.setParameter(key, val)
        );

        return query.executeUpdate();
    }

}
