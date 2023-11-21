package org.DAO;


import org.Data.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionManager;

@Repository
public class EmployeeDao {

    private final SessionFactory sessionFactory;
    private final TransactionManager transactionManager;
    public EmployeeDao(SessionFactory sessionFactory, TransactionManager transactionManager) {
        this.sessionFactory = sessionFactory;
        this.transactionManager = transactionManager;
    }


    /*public int save(Employee e){
        Integer i = (Integer) this.hibernateTemplate.save(e);
        return i;
    }*/
    //@Transactional
    public void save(Employee e){
        Session session =this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //this.sessionFactory.getCurrentSession().save(e);
        session.save(e);
        transaction.commit();
        session.close();
    }

    /*public List<Employee> getAll(){
       List<Employee> employee=this.hibernateTemplate.loadAll(Employee.class);
       return employee;
    }*/

    public Employee update(Employee e) {
        Session session =this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //this.sessionFactory.getCurrentSession().save(e);
        session.saveOrUpdate(e);
        transaction.commit();
        session.close();
        Session newsession = this.sessionFactory.openSession();
        Employee updatedEmployee = newsession.get(Employee.class,e.getId());
        newsession.close();
        return updatedEmployee;
    }

    public void delete(Employee e) {
        Session session =this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //this.sessionFactory.getCurrentSession().save(e);
        session.delete(e);
        transaction.commit();
        session.close();
    }
}
