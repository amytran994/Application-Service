package com.example.ApplicationService.dao;

import com.example.ApplicationService.domain.ApplicationWorkFlow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ApplicationWorkFlowDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public ApplicationWorkFlow createApplication(String employeeId, String status, String comment) {
        Session session = sessionFactory.getCurrentSession();
        ApplicationWorkFlow applicationWorkFlow = ApplicationWorkFlow.builder()
        .employeeID(employeeId)
        .status(status)
        .comment(comment)
        .createDate(LocalDateTime.now().toString()).build();

        session.save(applicationWorkFlow);
        return applicationWorkFlow;
    }

    @Transactional
    public List<ApplicationWorkFlow> getApplicationByStatus(String status) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from ApplicationWorkFlow a where a.status = :status", ApplicationWorkFlow.class);
        return q.setParameter("status", status).getResultList();
    }

    @Transactional
    public ApplicationWorkFlow getApplicationById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(ApplicationWorkFlow.class, id);
    }

    @Transactional
    public ApplicationWorkFlow getApplicationByEmployeeId(String id) {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from ApplicationWorkFlow a where a.employeeID = :employeeId", ApplicationWorkFlow.class);
        List<ApplicationWorkFlow> appList = q.setParameter("employeeId", id).getResultList();
        if (appList.size() == 0) {
        }

        return appList.get(0);
    }


    @Transactional
    public ApplicationWorkFlow update(Integer id, String status, String comment) {
        Session session = sessionFactory.getCurrentSession();
        ApplicationWorkFlow app = session.get(ApplicationWorkFlow.class,id );

        if (status != null) {
            app.setStatus(status);
        }
        if (comment != null) {
            app.setComment(comment);
        }

        app.setLastModificationDate(LocalDateTime.now().toString());

        session.update(app);

        return app;
    }

    public String sendNotification(Integer id){
        return "";
    }
}
