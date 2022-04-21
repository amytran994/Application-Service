package com.example.ApplicationService.dao.impl;


import com.example.ApplicationService.dao.AbstractHibernateDAO;
import com.example.ApplicationService.dao.DigitalDocumentDAO;
import com.example.ApplicationService.domain.DigitalDocument;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("digitalDocumentHibernateDao")
//database exceptions to Spring-based unchecked exceptions.
public class DigitalDocumentHibernateDAOImpl extends AbstractHibernateDAO<DigitalDocument> implements DigitalDocumentDAO {

    public DigitalDocumentHibernateDAOImpl() {
        setClazz(DigitalDocument.class);
    }

    @Override
    public DigitalDocument getById(Integer id) {
        return findById(id);
    }

//    @Override
//    public void add(DigitalDocument digitalDocument) {
//        super.add(digitalDocument);
//    }

    @Override
    public List<DigitalDocument> getAll() {
        return getCurrentSession().createQuery("from DigitalDocument").list();
    }


}
