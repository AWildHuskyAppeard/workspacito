package tw.group5.controller.cart;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import tw.group5.model.cart.Order;
import tw.group5.model.cart.OrderService;
import tw.group5.model.cart.old.UserBean;
import tw.group5.model.product.ProductInfo;

@SessionAttributes(names = "cart")
@Controller
@RequestMapping(path = "/cart.controller")
public class CartController {
	@Autowired // SDI ✔
	private OrderService orderService;
//	@Autowired // SDI ✔
	public List<ProductInfo> cart = new ArrayList<ProductInfo>();
	
	public CartController() {
		   System.out.println("=====>	IoC 容器正在建立本類別 (CartController) 的物件	<=====");
	}
	
	@GetMapping(value = {""})
	public String toTestpage() {
		return "/cart/testpage";
	}
	
	@PostMapping(value = {"/TheIndex"})
	public String toTheIndex() {
		return "首頁啦啦啦啊啦";
	}
	
//	@PostMapping(value = {"/cartIndex"})
	@GetMapping(value = {"/cartIndex"})
	public String toCartIndex() {
		refill();
		System.out.print("現在你的購物車 = ");
		cart.forEach(System.out::print);
		return "cart/cartIndex";
	}
	
	@PostMapping(value = {"/cartCheckout"})
	public String toCartCheckout() {
		refill();
		return "cart/cartCheckout";
	}
	
	@GetMapping(value = {"/cartAdmin"})
	public String toCartAdmin() {
		return "cart/cartAdmin";
	}
	

	@GetMapping(value="/showCart", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductInfo> showCart(HttpSession session) {
		session.setAttribute("cart", cart);
		cart = (ArrayList<ProductInfo>) session.getAttribute("cart");
		System.out.println("*** 現在正在showCart()方法內 ***");
		System.out.println("cart = " + cart);
		System.out.println("*** showCart()方法結束 ***");
		return cart;
	}
	
	@GetMapping(value = "/remove", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ProductInfo> removeProductFromCart(HttpSession session, @RequestParam("ckbox") String[] ckbox ) {
		cart = (ArrayList<ProductInfo>) session.getAttribute("cart");
		for (int i = 0; i < ckbox.length; i++) {
			int ckIndex = Integer.parseInt(ckbox[i]);
			cart.remove(ckIndex - i);
		}
		session.setAttribute("cart", cart);
		return cart;
	}
	
	@GetMapping(value = "/index")
	public String backToMainPage() {
		return "/index";
	}
	
	@PostMapping("/pay")
	public String pay() {
		
		// (1) 取得O_ID：查出最新的O_ID ❌
		// (2) 取得U_ID，U_FirstName，U_LastName，U_Email
		ArrayList<UserBean> fakeUserBeans = new ArrayList<UserBean>();
		UserBean fakeUserBean00 = new UserBean("user01", "psww", "1999-12-31", "TKYM", "TMT", "OWOsama@gmail.com", "0987654321", "F", "this.Galaxy");
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
		for(int i = 0; i < cart.size(); i++) {
			Order orderBean = new Order();
//			orderBean.setO_id(0); // PK
			orderBean.setP_id(cart.get(i).getP_ID()); // FK
			orderBean.setP_name(cart.get(i).getP_Name()); // FK
			orderBean.setP_price(cart.get(i).getP_Price()); // FK
			orderBean.setU_id(fakeUserBeans.get(0).getU_ID()); // FK // fake
			orderBean.setU_firstname(fakeUserBeans.get(0).getU_FirstName()); // FK // fake
			orderBean.setU_lastname(fakeUserBeans.get(0).getU_LastName()); // FK // fake
			orderBean.setU_email(fakeUserBeans.get(0).getU_Email()); // FK // fake
			orderBean.setO_status("DONE");
			orderBean.setO_date(now);
			orderBean.setO_amt(O_Amt);
			orderService.insert(orderBean);
		}

		this.cart = new ArrayList<ProductInfo>();
		System.out.println("購買成功，感謝您！");
		return "/cartThanks";
	}

	@GetMapping(value = "/initAdminPageData", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Order> initAdminPageData(){
		return orderService.selectTop20();
	}
	
	// 純粹測試用；最後用不到
	// 每次經過這個Controller都會跑這個block
	// 測試用。cart如果是空的，會自動補3件下列商品作為測試
	
	private void refill() {
		System.out.println("正在檢查你的cart是不是空的...");
		
		if(cart.size() == 0 || cart == null) {
			ProductInfo fakeProductBean1 = new ProductInfo();
			fakeProductBean1.setP_ID(3000);
			fakeProductBean1.setP_Name("EN_Speaking");
			fakeProductBean1.setP_Class("EN");
			fakeProductBean1.setP_Price(500);
			fakeProductBean1.setP_DESC("nice!!!");
			fakeProductBean1.setU_ID("fbk001");
			fakeProductBean1.setP_Img("pic001");
			fakeProductBean1.setP_Video("vid001");
			fakeProductBean1.setCreateDate(new Date());
			
			System.out.println("購物車沒有任何東西，因此管理員塞了一個課程進來✌💀✌");
			if(cart == null) {				
				cart = new ArrayList<ProductInfo>();
			}
			cart.add(fakeProductBean1);
		}
	}
	
}