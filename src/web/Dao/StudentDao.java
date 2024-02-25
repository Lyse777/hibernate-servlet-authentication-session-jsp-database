package web.Dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import web.Model.Student;
import web.main.HibernateUtil;
import java.util.List;

public class StudentDao {

    // Registers a new student
    public void registerStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Authenticates a student
    public Student authenticateStudent(String email, String password) {
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Student S WHERE S.email = :email AND S.password = :password";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            student = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    // Retrieves  students
    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Deletes a student by ID
    public void deleteStudent(Long studentId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                session.delete(student);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Updates a student's details
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Finds a student by ID
    public Student findStudentById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Finds a student by Email
    public Student findStudentByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery("FROM Student S WHERE S.email = :email", Student.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Checking if Email Exist
    public boolean emailExists(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select 1 from Student where email = :email");
            query.setParameter("email", email);
        
            List<?> result = query.list();
            return !result.isEmpty(); 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    

}
