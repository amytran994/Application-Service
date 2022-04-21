//package com.example.ApplicationService.dao.impl;
//
//
//import com.example.ApplicationService.dao.AbstractHibernateDAO;
//import com.example.ApplicationService.dao.ApplicationWorkFlowDAO;
//import com.example.ApplicationService.domain.ApplicationWorkFlow;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository("applicationWorkFlowHibernateDao")
////database exceptions to Spring-based unchecked exceptions.
//public class ApplicationWorkFlowHibernateDAOImpl extends AbstractHibernateDAO<ApplicationWorkFlow> implements ApplicationWorkFlowDAO {
//
//    public ApplicationWorkFlowHibernateDAOImpl() {
//        setClazz(ApplicationWorkFlow.class);
//    }
//
//    @Override
//    public ApplicationWorkFlow getById(Integer id) {
//        return findById(id);
//    }
//    //   @Override
////    public void add(User user) {
////        add(user);
////    }
//
//    @Override
//    public List<ApplicationWorkFlow> getAll() {
//        return getCurrentSession().createQuery("from ApplicationWorkFlow").list();
//    }
//
//
//}
