package ru.job4j.connections;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Tester {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Engine engine1 = Engine.of("BMW_V16");
            session.save(engine1);
            System.out.println(engine1.getId());

            /*Engine engine1 = create(Engine.of("BMW_V16"), sf);
            Car car1 = create(Car.of("BMW X3", engine1), sf);
            for (Car car : findAll(Car.class, sf)) {
                System.out.println(car.getName() + " " + car.getEngine().getName());
            }*/
            Driver driver1 = Driver.of("Ivan");
            session.save(driver1);
            Driver driver2 = Driver.of("John");
            session.save(driver2);
            Car car1 = Car.of("BMW X3", engine1);
            System.out.println(driver1);
            System.out.println(driver2);
            car1.addDriver(driver1);
            System.out.println(car1.getDrivers().size());
            session.save(car1);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> T create(T model, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }

    public static <T> List<T> findAll(Class<T> cl, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + cl.getName(), cl).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
