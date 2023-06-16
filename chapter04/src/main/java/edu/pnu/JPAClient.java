package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.JPABoard;

public class JPAClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("JPA");

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("chapter04");
		EntityManager em = entityManagerFactory.createEntityManager();

//		//
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		JPABoard b = new JPABoard();
//		b.setTitle("Title");
//		b.setWriter("Writer");
//		b.setContent("Content");
//		b.setCreateDate(new Date());
//		b.setCnt(0L);
//		
//		em.persist(b);
//		tx.commit();

		insertData(em, 10);
		
		updateData(em, 7L);
		
		deleteData(em, 8L);

		//
		em.close();
		entityManagerFactory.close();

	}

	private static void insertData(EntityManager em, int count) {

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			for (int i = 0; i < count; i++) {

				JPABoard b = new JPABoard();
				b.setTitle("Title" + i);
				b.setWriter("Writer" + i);
				b.setContent("Content" + i);
				b.setCreateDate(new Date());
				b.setCnt(0L + i);

				em.persist(b);

			}
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			tx.rollback();
		}

	}
	
	private static void updateData(EntityManager em, long id) {

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			JPABoard b = em.find(JPABoard.class, id);
			b.setTitle("Corrected title");
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			tx.rollback();
		}

	}

	private static void deleteData(EntityManager em, long id) {

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			JPABoard b = em.find(JPABoard.class, id);
			em.remove(b);
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			tx.rollback();
		}

	}



}
