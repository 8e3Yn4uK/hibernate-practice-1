package hibernate;

import hibernate.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Runner {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            // Develop code to save objects.

            session.beginTransaction();
            Employee myEmplotee1 = new Employee("Miralem", "Pjanic", "Juventus");
            Employee myEmplotee2 = new Employee("Arcadiush", "Milic", "Napoli");
            Employee myEmplotee3 = new Employee("Ivan", "Perishich", "Inter");
            Employee myEmplotee4 = new Employee("Alexander", "Coralov", "Roma");
            Employee myEmplotee5 = new Employee("Cristof", "Piontek", "Milan");
            Employee myEmplotee6 = new Employee("Paolo", "Dybala", "Juventus");
            session.save(myEmplotee1);
            session.save(myEmplotee2);
            session.save(myEmplotee3);
            session.save(myEmplotee4);
            session.save(myEmplotee5);
            session.save(myEmplotee6);
            session.getTransaction().commit();

            // Develop code to retrieve an object by primary key.
            session = factory.getCurrentSession();
            session.beginTransaction();
            int id = 1;
            Employee employee = session.get(Employee.class, id);
            System.out.println(employee);
            session.getTransaction().commit();

            // Develop code to query objects to find employees for a given company.
            session = factory.getCurrentSession();
            session.beginTransaction();
            String company = "Juventus";
            List<Employee> list = session.createQuery("from Employee s where s.company='Juventus'").getResultList();
            for(Employee tempEmployer : list){
                System.out.println(tempEmployer);
            }
            session.getTransaction().commit();

            // Develop code to delete an object by primary key.
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createQuery("delete from Employee where id=2").executeUpdate();
            session.getTransaction().commit();
        } finally {
            factory.close();
        }


    }
}
