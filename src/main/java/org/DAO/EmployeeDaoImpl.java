package org.DAO;

import org.Data.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;
    private final TransactionManager transactionManager;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory, TransactionManager transactionManager) {
        this.sessionFactory = sessionFactory;
        this.transactionManager = transactionManager;
    }


    @Transactional
    public void saveEmployee(Employee employee) {
        // Check if the name is unique before saving
        if (!isNameAlreadyExists(employee.getName())) {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } else {
            throw new IllegalArgumentException("Employee with the same name already exists.");
        }
    }


    @Transactional
    public void updateEmployee(Employee employee) {
        // Check if the name is unique before updating
        if (!isNameAlreadyExists(employee.getName(), employee.getId())) {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } else {
            // Handle the case where the name already exists
            throw new IllegalArgumentException("Employee with the same name already exists.");
        }
    }


    @Transactional
    public void deleteEmployee(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Employee employee = session.get(Employee.class, employeeId);
        if (employee != null) {
            session.delete(employee);
            transaction.commit();
        }
    }


    @Transactional(readOnly=true)
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }



    public Employee getEmployeeById(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, employeeId);
    }

    // Additional methods...

    private boolean isNameAlreadyExists(String name) {
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Employee> root = query.from(Employee.class);

        Predicate condition = builder.equal(builder.lower(root.get("name")), name.toLowerCase());
        query.select(builder.count(root)).where(condition);

        return session.createQuery(query).getSingleResult() > 0;
    }

    private boolean isNameAlreadyExists(String name, int currentEmployeeId) {
        Session session = sessionFactory.getCurrentSession();
        //Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Employee> root = query.from(Employee.class);

        Predicate condition = builder.and(
                builder.equal(builder.lower(root.get("name")), name.toLowerCase()),
                builder.notEqual(root.get("id"), currentEmployeeId)
        );
        query.select(builder.count(root)).where(condition);

        return session.createQuery(query).getSingleResult() > 0;
    }
}



/*
package org.DAO;

import org.Data.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionManager;

import javax.transaction.Transactional;
import java.util.List;

//
@Repository
public class EmployeeDaoImpl  implements EmployeeDao{

    @Autowired
    private EmployeeDao employeeDao;

    */
/*@Autowired
    public EmployeeDaoImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }*//*


    private final SessionFactory sessionFactory;
    private final TransactionManager transactionManager;

    @Autowired
    public EmployeeDaoImpl(SessionFactory sessionFactory, TransactionManager transactionManager) {
        this.sessionFactory = sessionFactory;
        this.transactionManager = transactionManager;
    }


    */
/*public int save(Employee e){
        Integer i = (Integer) this.hibernateTemplate.save(e);
        return i;
    }*//*

    @Transactional
    public void save(Employee e){
        Session session =this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //this.sessionFactory.getCurrentSession().save(e);
        session.save(e);
        transaction.commit();
        session.close();
    }


    public List<Employee> getAll(){
        Session session =this.sessionFactory.openSession();
        try {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            List<Employee> employees = query.list();
            return employees;
        } finally {
            session.close();
        }
    }

    @Transactional
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

   */
/* public void delete(Employee e) {
        Session session =this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //this.sessionFactory.getCurrentSession().save(e);
        session.delete(e);
        transaction.commit();
        session.close();
    }*//*

    public void delete(int id) {
        Session session =this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Employee employeeToDelete = session.get(Employee.class, id);
        if (employeeToDelete != null) {
            session.delete(employeeToDelete);
            transaction.commit();
        }
        session.close();
    }
}
*/
