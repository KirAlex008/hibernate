package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRun {
    public static void main(String[] args) {
        Student rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
          /*  Student one = Student.of("Alex", 21, "Moscow");
            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
            Student three = Student.of("Nikita", 25, "Kaliningrad");

            session.save(one);
            session.save(two);
            session.save(three);*/
            //Query query = session.createQuery("from Student");
            /*Query query = session.createQuery("from ru.job4j.hql.Student");
            for (Object st : query.list()) {
                System.out.println(st);
            }*/
           /* Query query = session.createQuery("from Student s where s.id = 1");
            System.out.println(query.uniqueResult());*/
            /*Query query = session.createQuery("from Student s where s.id = :fId");
            query.setParameter("fId", 1);
            System.out.println(query.uniqueResult());
            session.getTransaction().commit();
            session.close();*/
            rsl = session.createQuery(
                    "select distinct st from Student st "
                            + "join fetch st.account a "
                            + "join fetch a.books b "
                            + "where st.id = :sId", Student.class
            ).setParameter("sId", 1).uniqueResult();
            /*rsl = session.createQuery(
                    "select s from Student s where s.id = :sId", Student.class
            )
                    .setParameter("sId", 1)
                    .uniqueResult();*/
            System.out.println(rsl);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}
