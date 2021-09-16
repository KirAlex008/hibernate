package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CandidateRun {
    public static void main(String[] args) {
        Student rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            /*Student one = Student.of("Alex", 21, "Moscow");
            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
            Student three = Student.of("Nikita", 25, "Kaliningrad");

            session.save(one);
            session.save(two);
            session.save(three);*/
            session.createQuery("insert into Student (name, age, city) "
                    + "select concat(s.name, 'NEW'), s.age + 5, s.city  "
                    + "from Student s where s.id = :fId")
                    .setParameter("fId", 14)
                    .executeUpdate();

            /*System.out.println( session.createQuery("from Student s where s.id = :fId")
                    .setParameter("fId", 13).uniqueResult());*/
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}
