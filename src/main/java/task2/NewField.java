/**
 * Оновити поле name для всіх записів, які мають довжину значення поля last_name більше ніж 7.
 * У полі name записати значення «1».
 **/


package task2;

import jakarta.persistence.*;

import java.util.List;


public class NewField {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("authorhelper");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

// Отримуємо список авторів
        List<Author> authors = em.createQuery(
                        "SELECT a FROM Author a WHERE LENGTH (a.last_name) > 7", Author.class).getResultList();

// Оновлюємо поле name для кожного автора за допомогою Builder
        for (Author author : authors) {
            Author updatedAuthor = Author.builder()
                    .id(author.getId()) // зберігаємо ідентифікатор
                    .name("1") // записуємо нове значення name
                    .last_name(author.getLast_name()) // зберігаємо прізвище
                    .build();
            em.merge(updatedAuthor); // зливаємо зміни з базою даних
        }

// Завершуємо транзакцію
        transaction.commit();
    }
}




