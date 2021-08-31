package ru.job4j.lazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmRunForCar {
    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            /*Brand brand = Brand.of("Brand");
            Model model1 = Model.of("model_1", brand);
            Model model2 = Model.of("model_2", brand);
            Model model3 = Model.of("model_3", brand);
            Model model4 = Model.of("model_4", brand);
            Model model5 = Model.of("model_5", brand);
            session.save(brand);
            session.save(model1);
            session.save(model2);
            session.save(model3);
            session.save(model4);
            session.save(model5);*/
            list = session.createQuery(
                    "select distinct b from Brand b join fetch b.models"
            ).list();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (Brand brand : list) {
            for (Model model : brand.getModels()) {
                System.out.println(model);
            }
        }

    }
}
