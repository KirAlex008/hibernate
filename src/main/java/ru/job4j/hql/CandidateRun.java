package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class CandidateRun {
    public static void main(String[] args) {
        Student rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Candidate one = Candidate.of("John", "advanced", 2000);
            Candidate two = Candidate.of("Ian", "expert", 4000);
            Candidate three = Candidate.of("Jackie ", "beginner", 1000);
            session.save(one);
            session.save(two);
            session.save(three);
            Query query = session.createQuery("from Candidate");
            query = session.createQuery("from Candidate c where c.id = :fId")
                    .setParameter("fId", 1);
            query = session.createQuery("from Candidate c where c.name = :fName")
                    .setParameter("fName", "Jackie");
            session.createQuery("update Candidate c set c.experience = :newExperience, c.salary = :newSalary where c.id = :fId")
                    .setParameter("newExperience", "expert")
                    .setParameter("newSalary", 4000)
                    .setParameter("fId", 3)
                    .executeUpdate();
            session.createQuery("delete from Candidate c where c.id = :fId")
                    .setParameter("fId", 2)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

    }
}
