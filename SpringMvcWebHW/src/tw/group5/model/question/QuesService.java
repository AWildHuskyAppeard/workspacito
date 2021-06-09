package tw.group5.model.question;

import java.util.List;

public interface QuesService {

		public int insertQues(QuesBean quesBean) ;
		
		public void deleteQues(int q_ID) ;
		
		public void updateQues(QuesBean quesBean);
		
		public QuesBean findQues(int q_ID);
		
		public List<QuesBean> findAllQuesBean();
		
		public boolean isQuestionExist(QuesBean quesBean);
		
//		public String checkQuestionId(String q_ID);


	}
