package tw.group5.controller.cart;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.group5.model.cart.old.Order;
import tw.group5.model.cart.old.OrderDao;
import tw.group5.model.product.ProductInfo;

@SessionAttributes(names = "cart")
@Controller
@RequestMapping(path = "/cart.controller")
public class CartController {
	
	public CartController() {
		   System.out.println("=====>	IoC容器正在建立本類別 (CartController) 的物件	<=====");
	}
	
	@GetMapping(value = {"", "/index", "index.html"})
	public String toMyIndex() {
		return "cart/cartIndex";
	} 
	
	public static ArrayList<ProductInfo> cart = new ArrayList<ProductInfo>();
	
	
	@GetMapping(value="/showCart", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductInfo> showCart(HttpSession session) {
		cart = (ArrayList<ProductInfo>) session.getAttribute("cart");
		return cart;
	}

	private void remoteControl() {
		while(true) {
		System.out.println("輸入1以移除商品，2以確定結帳(把資料存進資料庫)，或其他回到主選單");
		String cmd = scanner.nextLine();
			if (cmd == "2" && cart.size() != 0) {
				pay();
				try {wait(1500);} catch(Exception e){}
				backToMainPage();
				return;
			}
			if (cmd == "2" && cart.size() == 0) {
				System.out.println("Nothing in your cart :( ");
				continue;
			}
			else if (cmd == "1") {
				removeProductFromCart();
				continue;
			}
			else {
				backToMainPage();
				return;
			}
		}
	}
	
	private void removeProductFromCart() {
		while(true) {			
			System.out.println("Enter the No of Product you'd like to remove OR other key to leave.");
			String n = scanner.nextLine();
			try {
				int no = Integer.parseInt(n);
				cart.remove(no);
				showCart();
			} catch (Exception e) {
				return;
			}
		}
	}

	private void backToMainPage() {
		return;
	}

	private void pay() {
		OrderDao crudor = new OrderDao(this.session);
		// ＊生成OrderBean
		
		// (1) 取得O_ID：查出最新的O_ID
			// O_ID可能是
		Query<Order> query = this.session.createQuery("FROM OrderBean ob ORDER BY ob.O_ID DESC", Order.class).setMaxResults(1);
		Order uniqueResult = query.uniqueResult();
		String O_IDString = uniqueResult.getO_ID();
		// 剝掉非O_ID中非數字的部分取出轉成Integer
		String pureNum = HibernateUtil.stripNonDigits(O_IDString);
		Integer latestO_ID = Integer.parseInt(pureNum);
		// 找出當前Table裡O_ID最大數字，並+1
		// 97~122 = a~z; 65~90 = A~Z (ASCII表)
		// 單筆訂單內容上限 = 26筆
		ArrayList<String> newO_IDs = new ArrayList<String>();
		if (cart.size() > 1) {
			for(int i = 0; i < cart.size(); i++) {			
				String newO_ID = String.format("order%06d-%s", (latestO_ID + 1), (char)(65 + i));
				newO_IDs.add(newO_ID);
			}			
		} else {	
			String newO_ID = String.format("order%06d", (latestO_ID + 1));
			newO_IDs.add(newO_ID);
		}		
		
		// (2) 取得U_ID，U_FirstName，U_LastName，U_Email
		// 以下為測試用，要換掉
		ArrayList<UserBean> fakeUserBeans = new ArrayList<UserBean>();
		UserBean fakeUserBean00 = new UserBean("user01", "psww", "1098-12-31", "TKYM", "TMT", "L@U.L", "0987654321", "F", "this.Galaxy");
		fakeUserBeans.add(fakeUserBean00);
		
		// (3) 取得O_Date (使用SimpleDateFormat)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		String now = sdf.format(calendar.getTime());
		
		// (4) 取得O_Amt
		 Integer O_Amt = 0;
		 for (int i = 0; i < this.cart.size(); i++) {
			O_Amt += this.cart.get(i).getP_Price();
		}
		
		// 把OrderBean的資料寫進去Database
		// 之後把下面fakeUserBeans.get(0)改成get(i)
		for(int i = 0; i < cart.size(); i++) {
			Order orderBean = new Order(newO_IDs.get(i), cart.get(i).getP_ID(), cart.get(i).getP_Name(), 
				cart.get(i).getP_Price(), fakeUserBeans.get(0).getU_ID(), fakeUserBeans.get(0).getU_FirstName(), 
				fakeUserBeans.get(0).getU_LastName(), fakeUserBeans.get(0).getU_Email(), "done", now, O_Amt);
			crudor.insertOrder(orderBean);
		}

		this.cart = new ArrayList<ProductBean>();
		System.out.println("購買成功，感謝您！");
		return;
	}

	// 純粹測試用；最後用不到
	@GetMapping("/refill")
	@ResponseBody
	public String refill() {
			
				// 測試用。cart如果是空的，會自動補3件下列商品作為測試
				if(cart.size() == 0) {
					ProductInfo fakeProductBean1 = new ProductInfo();
					fakeProductBean1.setP_ID(3);
					fakeProductBean1.setP_Name("EN_Speaking");
					fakeProductBean1.setP_Class("EN");
					fakeProductBean1.setP_Price(500);
					fakeProductBean1.setP_DESC("nice!!!");
					fakeProductBean1.setU_ID("fbk001");
					fakeProductBean1.setP_Img("pic001");
					fakeProductBean1.setP_Video("vid001");
					fakeProductBean1.setCreateDate(new Date());
					
					System.out.println("購物車沒有任何東西，因此管理員塞了一個課程進來✌💀✌");
					cart = new ArrayList<ProductInfo>();
					cart.add(fakeProductBean1);
				}
				return "購物車沒有任何東西，因此管理員塞了一個課程進來✌💀✌";
		}
}