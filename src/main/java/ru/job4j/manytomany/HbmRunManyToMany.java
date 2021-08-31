package ru.job4j.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRunManyToMany {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Author author1 = Author.of("name_1");
            Author author2 = Author.of("name_2");
            Author author3 = Author.of("name_3");
            Book book1 = Book.of("Book_1");
            Book book2 = Book.of("Book_2");
            author1.getBooks().add(book1);
            author2.getBooks().add(book1);
            author3.getBooks().add(book1);
            author2.getBooks().add(book2);
            author3.getBooks().add(book2);
            session.persist(author1);
            session.persist(author2);
            session.persist(author3);
            Author author = session.get(Author.class, author1.getId());
            session.remove(author);
            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
