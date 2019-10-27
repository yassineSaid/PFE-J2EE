package tn.esprit.pfe.interfaces;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.ForumQuestion;
@Remote
public interface QuestionServiceRemote {
	public void addQuestion(ForumQuestion Q);
	public void MetreajourQuestion(boolean Question_resolu,int id_Question);
	public void deleteQuestion(int id_Question);
}
