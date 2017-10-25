package com.maxie;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Restrictions;

/**
 * Class which handles all database connections. works by the CRUD standard.
 * 
 * @author Maxie
 *
 */

public class DatabaseConnector {
	private static SessionFactory factory;

	/**
	 * Constructor which creates a new session factory
	 */
	public DatabaseConnector() {
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Receives a list of objects to be transfered and executes a CREATE query.
	 * Clears the linked list after transaction is done
	 * 
	 * @param list
	 *            linked list containing the objects to be saved
	 */
	public void create(LinkedList<Character> list) {

		// create transaction
		Session session = factory.openSession();
		Transaction t = null;

		for (Character c : list) {
			session.persist(c);
		}

		list.clear();
		t = session.beginTransaction();
		t.commit();
		session.close();
	}

	/**
	 * Creates a READ query that lists all entries within the database
	 * 
	 * @return list containing all objects selected from the database
	 */
	public List read() {
		Session session = factory.openSession();
		Transaction t = null;
		List characters = null;
		try {
			t = session.beginTransaction();
			characters = session.createQuery("FROM playercharacter").list();
			if (characters.isEmpty()) {
				characters.add("There's no saved characters");
				return characters;
			}
			for (Iterator iterator = characters.iterator(); iterator.hasNext();) {
				Character character = (Character) iterator.next();
			}
			t.commit();
			return characters;
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return characters;
	}

	/**
	 * Selects a character and updates its experience points with an UPDATE query
	 * 
	 * @param charId
	 *            id of character to update
	 * @param exp
	 *            experience points to add
	 */
	public void updateExp(Integer charId, int exp) {
		Session session = factory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			Character character = (Character) session.get(Character.class, charId);
			character.updateExp(exp);
			session.update(character);
			t.commit();
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Creates a DELETE query to remove specified character from the database
	 * 
	 * @param charId
	 *            specifies which entry that's to be deleted
	 */
	public void delete(Integer charId) {
		Session session = factory.openSession();
		Transaction t = null;

		try {
			t = session.beginTransaction();
			Character character = (Character) session.get(Character.class, charId);
			session.delete(character);
			t.commit();
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * Creates a search query by predefined search options
	 * 
	 * @param type
	 *            specifies which columns to execute query on
	 * @param searchQ
	 *            specifies what to search for, by default values for class and race
	 *            is chosen from enumeration while name uses a wildcard
	 * @return List of all found entries
	 */
	public List search(String type, String searchQ) {
		Session session = factory.openSession();
		Transaction t = null;
		List characters = null;
		try {
			t = session.beginTransaction();
			Criteria cr = session.createCriteria(Character.class);
			if (type.equals("race")) {
				cr.add(Restrictions.eq("charRace", searchQ));
				System.out.println("Executing query where type = " + type + " and search option = " + searchQ);
			} else if (type.equals("class")) {
				cr.add(Restrictions.eq("charClass", searchQ));
			} else if (type.equals("name")) {
				cr.add(Restrictions.like("name", "%" + searchQ + "%"));
			}
			characters = cr.list();
			if (characters.isEmpty()) {
				characters.add("This search returned no results");
				return characters;
			}
			for (Iterator iterator = characters.iterator(); iterator.hasNext();) {
				Character character = (Character) iterator.next();
			}
			t.commit();
			return characters;
		} catch (HibernateException e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return characters;
	}

}
