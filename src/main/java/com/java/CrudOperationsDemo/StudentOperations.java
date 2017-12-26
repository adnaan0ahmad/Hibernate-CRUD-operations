package com.java.CrudOperationsDemo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class StudentOperations implements Operations<Student> {

	public static void dataValidator(Student s) {
		if (s == null || s.getStudAddress().trim().isEmpty() || s.getStudName().trim().isEmpty() || s.getStudId() <= 0
				|| s.getStudRoll() <= 0)
			throw new InvalidDataQueryException("Invalid data passed as an entry for Database.");
	}

	public boolean add(Student t) {
		dataValidator(t);
		if (get(t.getStudId()) == null) {
			SessionFactory sf = HibernateUtil.getSessionFactory();
			Session s = sf.openSession();
			Transaction transaction = s.beginTransaction();
			try {
				s.save(t);
			} catch (Exception e) {
				transaction.rollback();
				throw new InvalidDataQueryException("Error while addition of Student Data.");
			} finally {
				HibernateUtil.resourceCleanup(s, transaction);
				// sf.close();
			}
			return true;
		} else
			throw new DuplicateEntryException("Student with same ID already exists in database.");
	}

	public Student get(int primaryKey) {
		if (primaryKey <= 0)
			throw new InvalidDataQueryException("Invalid identifier passed.");
		Student obj = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			obj = s.get(Student.class, primaryKey);
		} catch (Exception e) {
			transaction.rollback();
			throw new InvalidDataQueryException("Error while retrieving of Student Data.");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return obj;
	}

	public List<Student> getAll() {
		List<Student> l = new ArrayList<Student>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			l = s.createQuery("From Student").getResultList();
		} catch (Exception e) {
			throw new InvalidDataQueryException("Error while acquiring Student Data List.");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return l;
	}

	public Student update(Student t) {
		dataValidator(t);
		if (get(t.getStudId()) == null) {

		}
		// throw new InvalidDataQueryException("No such entry exists in Database.");
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			s.update(t);
		} catch (Exception e) {
			transaction.rollback();
			System.err.println("Error while updating Student Data");
			// throw new InvalidDataQueryException("");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return t;
	}

	public boolean delete(int primaryKey) {
		if (get(primaryKey) == null || primaryKey <= 0)
			throw new InvalidDataQueryException("Invalid entry request in Database.");
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		try {
			s.remove(get(primaryKey));
		} catch (Exception e) {
			transaction.rollback();
			throw new InvalidDataQueryException("Error in deleting the requested entry.");
		} finally {
			HibernateUtil.resourceCleanup(s, transaction);
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public List<Student> searchOnCriteria(Student t, SearchCriteria... sc) {
		dataValidator(t);
		searchParamValidater(sc);
		if (SearchCriteria.ALL.equals(sc[0]))
			return getAll();

		List<Student> l = new ArrayList<Student>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = s.beginTransaction();
		Criteria cr = s.createCriteria(Student.class);

		for (SearchCriteria c : sc) {
			switch (c) {
			case NAME:
				cr.add(Restrictions.eq("studName", t.getStudName()));
				break;
			case ROLLNUMBER:
				cr.add(Restrictions.eq("studRoll", t.getStudRoll()));
				break;
			case ADDRESS:
				cr.add(Restrictions.eq("studAddress", t.getStudAddress()));
				break;
			default:
				throw new InvalidDataQueryException("Invalid search criteria mentioned.");
			}
		}
		l = cr.list();
		HibernateUtil.resourceCleanup(s, transaction);
		return l;
	}

	private void searchParamValidater(SearchCriteria... criteria) {

		if (criteria.length <= 0 || criteria.length > 3)
			throw new InvalidDataQueryException("Invalid number of search parameters passed.");

		for (int i = 0; i < criteria.length - 1; i++) {
			for (int j = i + 1; j < criteria.length; j++) {
				if (criteria[i].equals(criteria[j]))
					throw new InvalidDataQueryException("Same search parameters used.");
			}
		}

	}

}