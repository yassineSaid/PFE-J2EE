package tn.esprit.pfe.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pfe.entities.ForumQuestion;
@Remote
public interface QuestionServiceRemote {
	public String addQuestion(ForumQuestion Q);
	public void MetreajourQuestion(boolean Question_resolu,int id_Question);
	public void deleteQuestion(int id_Question);
	public ForumQuestion getQuestionid(int id);
	public List<ForumQuestion> getAllquestion();
}
