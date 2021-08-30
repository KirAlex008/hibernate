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

            Model model1 = Model.of("model_1");
            Model model2 = Model.of("model_2");
            Model model3 = Model.of("model_3");
            Model model4 = Model.of("model_4");
            Model model5 = Model.of("model_5");
            session.save(model1);
            session.save(model2);
            session.save(model3);
            session.save(model4);
            session.save(model5);
            Brand brand = Brand.of("Brand");
            brand.addModel(session.load(Model.class, model1.getId()));
            brand.addModel(session.load(Model.class, model2.getId()));
            brand.addModel(session.load(Model.class, model3.getId()));
            brand.addModel(session.load(Model.class, model4.getId()));
            brand.addModel(session.load(Model.class, model5.getId()));
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
