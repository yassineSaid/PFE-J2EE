package tn.esprit.pfe.services;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import tn.esprit.pfe.entities.ForumQuestion;
import tn.esprit.pfe.interfaces.QuestionServiceRemote;
@Stateless
@LocalBean
public class QuestionServices implements QuestionServiceRemote{
	@PersistenceContext
	EntityManager em;
	@Override
	
	public void addQuestion(ForumQuestion Q) {
		em.persist(Q);
		
	}

	@Override
	public void MetreajourQuestion(boolean question_resolu, int id_Question) {
		ForumQuestion q = em.find(ForumQuestion.class, id_Question);
		q.setQuestion_resolu(question_resolu);
		
	}

	@Override
	public void deleteQuestion(int id_Question) {
		ForumQuestion q = em.find(ForumQuestion.class, id_Question);
		em.remove(q);
		
	}
	
	@Override
	public ForumQuestion getQuestionid(int id) {
		TypedQuery<ForumQuestion> query =
				em.createQuery("SELECT q FROM ForumQuestion q WHERE q.id_Question=:id_Question ",
						ForumQuestion.class);
		query.setParameter("id_Question", id);
		
		ForumQuestion question = null;
		try { question = query.getSingleResult(); }
		catch (Exception e) { System.out.println("Erreur : " + e); }
		return question;
	}

	

}
