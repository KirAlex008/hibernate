package ru.job4j.many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRunMany {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Model model_1 = Model.of("model_1");
            Model model_2 = Model.of("model_2");
            Model model_3 = Model.of("model_3");
            Model model_4 = Model.of("model_4");
            Model model_5 = Model.of("model_5");
            session.save(model_1);
            session.save(model_2);
            session.save(model_3);
            session.save(model_4);
            session.save(model_5);
            Brand brand = Brand.of("Brand");
            brand.addModel(session.load(Model.class, model_1.getId()));
            brand.addModel(session.load(Model.class, model_2.getId()));
            brand.addModel(session.load(Model.class, model_3.getId()));
            brand.addModel(session.load(Model.class, model_4.getId()));
            brand.addModel(session.load(Model.class, model_5.getId()));
            session.save(brand);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
