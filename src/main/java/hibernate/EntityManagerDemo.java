package hibernate;

import hibernate.domain.Event;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class EntityManagerDemo {
    static EntityManager em;
    public static void main(String[] args) {


        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
       em = factory.createEntityManager();

        Event event = findById(1);
        System.out.println(event);
        //Event newEvent = add(new Event("Rock Concert","Berlin"));
      //  event = findById();



      //event.setCity("Prague");
      //event= update(event);
      //System.out.println(event);

        //delete(event);
      // Event delEvent = new Event();
      // delEvent.setId(10);
      // delete(delEvent);


        List<Event> events = findAll();
        for (Event event1 : events) {
            System.out.println(event1);
        }
    }




    static Event findById(int id) {
        return em.find(Event.class, id);
    }
   static Event add(Event event){
       em.getTransaction().begin();
       em.persist(event);
       em.getTransaction().commit();
       return event;
   }
    static Event update(Event event){
        em.getTransaction().begin();
        em.merge(event);
        em.getTransaction().commit();
        return event;
    }
    static void delete (Event event){
        em.getTransaction().begin();
        em.remove(event);
        em.getTransaction().commit();

    }
   static List<Event> findAll(){
        return em.createNamedQuery("findAll",Event.class).getResultList();

   }

}
