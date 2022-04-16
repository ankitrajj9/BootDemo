/*
 * package com.ankit.demo;
 * 
 * import java.util.List;
 * 
 * import org.hibernate.SessionFactory; import org.hibernate.query.Query; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Repository; import
 * org.springframework.stereotype.Service;
 * 
 * @Repository public class AllService {
 * 
 * @Autowired private SessionFactory sessionFactory;
 * 
 * public List<Object[]> getDataTest() {
 * System.out.println("Inside Repository"); Query q =
 * sessionFactory.getCurrentSession().createQuery("from Student");
 * List<Object[]> lo = q.list(); return lo; }
 * 
 * }
 */