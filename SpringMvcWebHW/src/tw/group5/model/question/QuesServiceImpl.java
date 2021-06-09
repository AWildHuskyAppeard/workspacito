package tw.group5.model.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@EnableTransactionManagement
public class QuesServiceImpl implements QuesService {

	@Autowired
	QuesDAO quesDAO;
	
	@Override
	public int insertQues(QuesBean quesBean) {
		int n = quesDAO.insertQues(quesBean);
		return n;
	}

	@Override
	public void deleteQues(int q_ID) {
		 quesDAO.deleteQues(q_ID);
	}

	@Override
	public void updateQues(QuesBean quesBean) {
		quesDAO.updateQues(quesBean);
	}

	@Override
	public QuesBean findQues(int q_ID) {
		return quesDAO.findQues(q_ID);
	}
	
	@Override
	public List<QuesBean> findAllQuesBean() {
		return quesDAO.findAllQuesBean();
	}

	@Override
	public boolean isQuestionExist(QuesBean quesBean) {
		return quesDAO.isQuestionExist(quesBean);
	}
	
//	@Override
//	public String checkQuestionId(String q_ID){
//		return quesDAO.checkQuestionId(q_ID);
//	}
}
