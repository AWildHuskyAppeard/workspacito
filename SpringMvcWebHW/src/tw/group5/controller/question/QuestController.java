package tw.group5.controller.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tw.group5.model.question.QuesBean;
import tw.group5.model.question.QuesService;

@Controller
public class QuestController  {

	@Autowired
	QuesService service;
	
	// 查詢所有會員資料
	@GetMapping("/questions")
	public @ResponseBody List<QuesBean> findAllQuesBean(Model model) {
		List<QuesBean> quesBean = service.findAllQuesBean();
		//因@ResponseBody,所以回傳值為JSON
		return quesBean;
	}
	
	// 讀取並傳回單筆會員資料
	@GetMapping("/questions/{q_ID}")
	// showEditQuestion ??
	public @ResponseBody QuesBean showEditQuestion(@PathVariable Integer q_ID) {
		QuesBean quesBean = service.findQues(q_ID);
		return quesBean;
	}
	
	// 送出顯示所有Questions紀錄的表單
		@GetMapping({ "/showAllQuestionsAjax" })
		public String showAllQuestionsFindView() {
			return "question/showAllQuestionsAjax";
		}

	
	// 依照鍵值刪除單筆會員資料
	@DeleteMapping("/questions/{q_ID}")
	public @ResponseBody Map<String, String> deleteMember(@PathVariable(required = true) Integer q_ID) {
		Map<String, String> map = new HashMap<>();
		try {
			service.deleteQues(q_ID);
			map.put("success", "刪除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "刪除失敗");
			System.out.println("刪除失敗");
		}
		return map;
	}
	
    // 回首頁
	@GetMapping("/goQuesIndex")
	public String goQuesIndex() {
		return "question/quesIndex";
	}
	
	
	    // 送出新增資料的空白表單
		@GetMapping("/insertQuestionForm")
		public String registerFindView() {
			return "question/create";
		}

		// 讀取瀏覽器送出Ajax請求， 呼叫 service.saveMember(member) 新增會員資料
		@PostMapping(value="/questions", consumes = "application/json")
		public @ResponseBody Map<String, String> save(@RequestBody QuesBean quesBean) {
			Map<String, String> map = new HashMap<>();
			int n = 0;
			try {
				n = service.insertQues(quesBean);
				if (n == 1) {
					map.put("success", "新增成功");
				} else if (n == -1) {
					map.put("fail", "帳號重複");
				}
			} catch (Exception e) {
				map.put("fail", e.getMessage());
			}
//			map轉成Json格式後送前端(response)
			return map;
		}

//暫不使用		// 當新增會員資料時, 檢查帳號是否可用
//		@PostMapping(value = "/checkQ_ID", produces = { "application/json" })
//		public @ResponseBody Map<String, String> checkQ_ID(@RequestParam("q_ID") String q_ID) {
//			Map<String, String> map = new HashMap<>();
//			String id = service.checkQuestionId(q_ID);
//			map.put("q_ID", id);
//			return map;
//		}

	
};