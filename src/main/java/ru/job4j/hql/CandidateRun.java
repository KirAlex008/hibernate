package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class CandidateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            session.createQuery("delete from Candidate")
                    .executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE IF EXISTS candidate_id_seq RESTART").executeUpdate();

            Candidate one = new Candidate("john", "advanced", 2000);
            Candidate two = new Candidate("ian", "expert", 4000);
            Candidate three = new Candidate("jackie", "beginner", 1000);
            session.save(one);
            session.save(two);
            session.save(three);

            Query query = session.createQuery("from Candidate");
            query.list().forEach(System.out::println);

            query = session.createQuery("from Candidate c where c.id = :fId")
                    .setParameter("fId", 1);
            System.out.println(query.uniqueResult());

            query = session.createQuery(" from Candidate c where c.name = :fName", Candidate.class)
                    .setParameter("fName", "jackie");
            System.out.println(query.uniqueResult());

            session.createQuery("update Candidate c set c.experience = :newExperience, c.salary = :newSalary where c.id = :fId")
                    .setParameter("newExperience", "expert")
                    .setParameter("newSalary", 4000)
                    .setParameter("fId", 3)
                    .executeUpdate();
            session.clear();

            query = session.createQuery("from Candidate");
            query.list().forEach(System.out::println);

            session.createQuery("delete from Candidate c where c.id = :fId")
                    .setParameter("fId", 2)
                    .executeUpdate();
            query = session.createQuery("from Candidate");
            query.list().forEach(System.out::println);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
