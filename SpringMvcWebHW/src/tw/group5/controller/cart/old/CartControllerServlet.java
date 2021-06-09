//package tw.group5.controller.cart.old;
//
//import java.io.*;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import javax.sql.DataSource;
//
//import userInfo.UserBean;
///**
// * @Q1. 
// **/
//
//@WebServlet("/CartControllerServlet")
//@SuppressWarnings("unchecked")
//public class CartControllerServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private DataSource ds;
//	private HttpSession session; 
//	private List<ProductBean> cart;
////	private int jjj = (1==2)? 1 : 2; // 挖賽這居然行的通喔...這不是等於塞了一個if else了嗎
//	
//	public static ProductBean testBean1 = new ProductBean("p000003", "EN_Speaking", "EN", 500, "nice", "fbk001", "pic01", "vid01");
//	public static ProductBean testBean2 = new ProductBean("p000015", "RU_Reading", "RU", 650, "awesome", "krn563", "pic02", "vid02");
//	public static ProductBean testBean3 = new ProductBean("p000009", "JP_Translation", "JP", 500, "subarashii", "duck486", "pic03", "vid03");
//	
//    @Override
//	public void init() throws ServletException {
//		super.init();
//		return;
//	}
//    
//    @Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	request.setCharacterEncoding("UTF-8");
//    	response.setContentType("text/html;charset=UTF-8");
//    	response.getWriter().print("<h1>PLEASE USE POST METHOD SIR LULLLLLLLLLLLLLL</h1>");
//    	return;
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	// session範圍：會員點選「加入購物車」(#add) ~ 付款完成 or 登出為止 
//    	this.session = request.getSession(true);
//    	this.cart = (ArrayList<ProductBean>) session.getAttribute("cart");
//    	
//    	// 測試用
//    	if(this.cart == null || this.cart.size() == 0) 
//    	{
//    		this.cart = new ArrayList<ProductBean>();
//    		this.cart.add(testBean1);
//    		this.cart.add(testBean2);
//    		this.cart.add(testBean3);
//    	}
//    	
//    	String todo = request.getParameter("todo");
//
//    	// 1. 右上角購物車圖示 (from 任何頁面) 
//    	if(todo == null) gotoCartIndexPage(request, response); 
//    	// 2. 加入品項 (from 商品頁面)
//    	else if("add".equals(todo)) putProductIntoCart(request, response);
//    	// 3. 移除品項 (from 購物車頁面)
//    	else if("remove".equals(todo)) removeProductFromCart(request, response);
//    	// 4. 去結帳 (from 購物車頁面) 
//    	else if("checkout".equals(todo)) checkout(request, response);
//    	// 5. 回購物車頁面 (from 結帳頁面) 
//    	else if("back".equals(todo)) backToPreviousPage(request, response);
//    	// 6. 確定付款 (from 結帳頁面) 
//    	else if("pay".equals(todo)) pay(request, response);
//    	// 7. [管理員] 刪除
//    	else if("deleteAdmin".equals(todo)) deleteByAdmin(request, response);
//    	// 8. [管理員] 修改
//    	else if("updateAdmin".equals(todo)) updateByAdmin(request, response);
//    	// 9. [管理員] 新增
//    	else if("insertAdmin".equals(todo)) insertByAdmin(request, response);
//    	// debug用
//    	else response.getWriter().print("Something went wrong! "
//    			+ "todo value = " + todo);
//    	return;
//	}
//    /**
//     * @Method #01 todo == null > 導向購物車
//     * @1. 導向購物車頁(CartIndex.jsp)
//     * @2. 六種類別的頁面都可以連過來(只要todo參數不符合或根本沒有todo參數的都符合條件)
//     * @Database_Connection 不涉及
//     **/
//    private void gotoCartIndexPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
////    	req.getRequestDispatcher("/cart/cartIndex.jsp").forward(req, res);
//    	res.sendRedirect("/AwesomeProject/cart/cartIndex.jsp");
//    	return;
//    }
//    
//    /**
//     * @Method #02 add > 購買商品
//	 * @1. 將品項加入購物車
//	 * @2. 返回該商品頁
//	 * @undone 返回原頁的參數
//	 * @Database_Connection 不涉及
//	 **/
//	private void putProductIntoCart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		// 大概要改。這邊是假設課程頁會傳該product的column值過來。
//		// 有沒有辦法抓ProductBean呀？
//		ProductBean addedProduct = new ProductBean();
//		addedProduct.setP_ID ( req.getParameter("P_ID")); 
//		addedProduct.setP_Name ( req.getParameter("P_Name"));
//		addedProduct.setP_Class ( req.getParameter("P_Class"));
//		addedProduct.setP_Price ( Integer.parseInt(req.getParameter("P_Price"))); 
//		addedProduct.setP_DESC ( req.getParameter("P_DESC")); 
//		addedProduct.setU_ID ( req.getParameter("U_ID"));
//		addedProduct.setP_Img ( req.getParameter("P_Img")); 
//		addedProduct.setP_Video ( req.getParameter("P_Video"));
//		
//		this.session.setAttribute("addedProduct", addedProduct);
//		// 這邊不做像老師MVC Demo2的產品數量重疊的判斷和處理迴圈，
//		// 因為單項課程沒有超過一件的概念...吧？
//		this.cart.add(addedProduct);
//		
////		req.getRequestDispatcher("/product/xxxxxxxx.jsp").forward(req, res);	// 返回原頁
//		res.sendRedirect("/product/xxxxxxxx.jsp");
//		return;
//	}
//    /**
//     * @Method #03 remove > 移除商品 ver2
//     * @1. 將checkbox所勾選的商品自購物車移除
//     * @2. 導回購物車頁(CartIndex.jsp)
//     * @Database_Connection 不涉及
//     **/
//	private void removeProductFromCart(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		String[] indexesToBeRmvd = req.getParameterValues("ckbox");
//		
//		for(int i = 0; i < indexesToBeRmvd.length; i++) {
//			// 每當從ArrayList移除一件內容時後面的內容index都會往前退一格，故減 i
//			this.cart.remove(Integer.parseInt(indexesToBeRmvd[i]) - i);
//		}
//		session.setAttribute("cart", this.cart);
////		req.getRequestDispatcher("/cart/cartIndex.jsp").forward(req, res);
//		res.sendRedirect("/AwesomeProject/cart/cartIndex.jsp");
//		return;
//	}
//	
//	@Deprecated
//    /**
//     * @Method [淘汰]#03 remove > 移除商品 ver1
//     * @1. 將指定商品自購物車移除
//     * @2. 導回購物車頁(CartIndex.jsp)
//     * @3. 紀錄本人的智商成長用
//     * @Database_Connection 不涉及
//     **/
//	private void removeProductFromCartV1(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
//		// 用Vector如何？ 
//		// 問題：radio input好像無法取消勾選	
//		// 用checkbox的話要怎麼做啊ˊ<_ˋ
//
//
//		
//		// 對照button狀態ArrayList(btns)和要移除的品項ArrayList(toBeRmvd)
//		// 只取出btn值 == on的 P_ID，亦即那些勾選刪除的選項：
//		// 從購物車移除掉有打圈的課程
//		ArrayList<String> btns = new ArrayList<String>(); 
//		ArrayList<Integer> toBeRmvd = new ArrayList<Integer>();
//	    if(cart != null && cart.size() != 0) {
//		    for(int i = 0; i < cart.size() ; i++) {
//		    	btns.add(req.getParameter("btn" + (i + 1)));
//		        if("on".equals(btns.get(i))) {
//		            for(int j = 0; j < cart.size(); j++) {
//		            	String aa = (String)session.getAttribute("P_ID" + i);
//		                if(cart.get(j).getP_ID().equals(aa)) {
//		                    toBeRmvd.add(j);
//		                }
//		            }
//		        }
//		        session.removeAttribute("P_ID" + i);
//		    }
//		}
//	    if(cart.size() != 0) {    	
//		    for(int i = 0; i < toBeRmvd.size(); i++) {
//		    	cart.remove(cart.get(toBeRmvd.get(i) - i));
//		    }
//	    }
//		
//		System.out.println(cart);
//    	this.session.setAttribute("cart", this.cart); // xxxxxxxxx
//		
//		req.getRequestDispatcher("/cart/cartIndex.jsp").forward(req, res);	// 返回原頁
//		return;
//	}
//	
//    /**
//     * @Method #04 checkout > 結帳
//     * @1. 導向至結帳頁面(checkout.jsp)
//     * @undone 「下一頁」要記得把session invalidate()掉
//     * @Database_Connection 不涉及
//     **/
//	private void checkout(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
////		req.getRequestDispatcher("/cart/cartCheckout.jsp").forward(req, res);	// 去結帳
//		res.sendRedirect("/AwesomeProject/cart/cartCheckout.jsp"); // 去結帳
//		return;
//	}
//    /**
//     * @Method #05 back > 回購物車頁面
//     * @Database_Connection 不涉及
//     **/
//	private void backToPreviousPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
////		req.getRequestDispatcher("/cart/cartIndex.jsp").forward(req, res);	// 回上一頁(購物車首頁)
//		res.sendRedirect("/AwesomeProject/cart/cartIndex.jsp"); // 回上一頁(購物車首頁)
//		return;
//	}
//    /**
//     * @Method #06 pay > 確定付款
//     * @undone O_ID的產生方式錯了，還沒改
//     * @Database_Connection SELECT + INSERT
//     * @Caution 部分SQL語句只適用於MSSQL(TOP(n))
//     **/
//	private void pay(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		
//		// 實際上應該是要到錯誤頁
//		// 前端也做個disabled
//		if(cart.size() == 0) {
//			res.sendRedirect("/AwesomeProject/cart/cartIndex.jsp");
//			return;
//		}
//		
//		Connection conn = getConn();
//		CartDAO crudor = new CartDAO(conn);
//		// ＊生成OrderBean
//		
//		// (1) 取得O_ID：查出最新的O_ID
//		crudor.selectCustom("SELECT TOP(1) [O_ID] FROM [Order_Info] ORDER BY [O_ID] DESC;");
//		ArrayList<ArrayList<String>> dataArrays = CartDAO.dataArrays;
//		String O_IDString = dataArrays.get(0).get(0);
//		// 剝掉非O_ID中非數字的部分取出轉成Integer
//		String pureNum = stripNonDigits(O_IDString);
//		Integer latestO_ID = Integer.parseInt(pureNum);
//		// 找出當前Table裡O_ID最大數字，並+1
//		// 97~122 = a~z; 65~90 = A~Z (ASCII表)
//		// 單筆訂單內容上限 = 26筆
//		ArrayList<String> newO_IDs = new ArrayList<String>();
//		if (cart.size() > 1) {
//			for(int i = 0; i < cart.size(); i++) {			
//				String newO_ID = String.format("order%06d-%s", (latestO_ID + 1), (char)(65 + i));
//				newO_IDs.add(newO_ID);
//			}			
//		} else {	
//			String newO_ID = String.format("order%06d", (latestO_ID + 1));
//			newO_IDs.add(newO_ID);
//		}		
//		
//		// (2) 取得U_ID，U_FirstName，U_LastName，U_Email
//		// 之後請若安把已登入會員的Bean幫我塞進session Attribute內，取出語句如下：
//		// UserBean userBean = (UserBean)this.session.getAttribute("userBean");
//		// 以下為測試用，要換掉
//		ArrayList<UserBean> fakeUserBeans = new ArrayList<UserBean>();
//		UserBean fakeUserBean00 = new UserBean("user01", "psww", "1098-12-31", "TKYM", "TMT", "L@U.L", "0987654321", "F", "this.Galaxy");
//		fakeUserBeans.add(fakeUserBean00);
//		
//		// (3) 取得O_Date (使用SimpleDateFormat)
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		String now = sdf.format(calendar.getTime());
//		
//		// (4) 取得O_Amt
//		 Integer O_Amt = (Integer) session.getAttribute("O_Amt");
//		
//		// 把OrderBean的資料寫進去Database
//		// 之後把下面fakeUserBeans.get(0)改成get(i)
//		for(int i = 0; i < cart.size(); i++) {
//			OrderBean orderBean = new OrderBean(newO_IDs.get(i), cart.get(i).getP_ID(), cart.get(i).getP_Name(), 
//				cart.get(i).getP_Price(), fakeUserBeans.get(0).getU_ID(), fakeUserBeans.get(0).getU_FirstName(), 
//				fakeUserBeans.get(0).getU_LastName(), fakeUserBeans.get(0).getU_Email(), "done", now, O_Amt);
//			crudor.insertOrder(orderBean);
//		}
//
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		this.cart = new ArrayList<ProductBean>();
//		session.setAttribute("cart", this.cart);
//		session.removeAttribute("O_Amt");
//		
////		req.getRequestDispatcher("/cart/cartThanks.jsp").forward(req, res);	 
//		res.sendRedirect("/AwesomeProject/cart/cartThanks.jsp");
//		return;
//	}
//	
//    /**
//     * @Method #07 deleteAdmin 
//	 * @Database_Connection DELETE
//	 **/
//	private void deleteByAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Connection conn = getConn();
//		CartDAO crudor = new CartDAO(conn);
//		
//		String[] O_IDsToBeRmvd = req.getParameterValues("ckbox");
//		for(int i = 0; i < O_IDsToBeRmvd.length; i++) {
//			crudor.deleteOrder(O_IDsToBeRmvd[i]);
//		}
//		
//    	this.session.setAttribute("cart", this.cart); 
//		
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
////		req.getRequestDispatcher("/cart/cartAdmin.jsp").forward(req, res);	
//		res.sendRedirect("/AwesomeProject/cart/cartAdmin.jsp");
//		return;
//	}
//	
//    /**
//     * @Method #08 updateAdmin 
//	 * @Database_Connection UPDATE
//	 * @Problem.1 方法太笨重，資料庫有1000筆11欄資料時就要進行11000次UPDATEs，太扯了。待優化。
//	 * @Solution.1 在前端限制傳回數量 or 一次顯示20筆之類 or 特化查詢 or 改善此方法 
//	 **/		
//	private void updateByAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Connection conn = getConn();
//		CartDAO crudor = new CartDAO(conn);
////		ArrayList<OrderBean> adminBeans = (ArrayList<OrderBean>)session.getAttribute("adminBeans");
////		for(int i = 0; i < adminBeans.size(); i++) {
////			crudor.updateOrder(adminBeans.get(i), "O_ID", adminBeans.get(i).getO_ID());
////		}
//		ArrayList<OrderBean> adminBeans = new ArrayList<OrderBean>();
//		for(int i =0; i < CartDAO.dataArrays.size(); i++) {
//			OrderBean adminBean = new OrderBean();
//			for(int j = 0; j < CartDAO.columnNames.length; j++) {
//				adminBean.assign(j + 1, req.getParameter(String.valueOf(i)+String.valueOf(j)));
//			}
//			System.out.println(adminBean.take(1)); // debug用
//			adminBeans.add(adminBean);
//			crudor.updateOrder(adminBeans.get(i), "O_ID", adminBeans.get(i).getO_ID());
//		}
//		
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//				this.session.removeAttribute("adminBeans");
////				session.removeAttribute(O_ID~~~, e);
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
////		req.getRequestDispatcher("/cart/cartAdmin.jsp").forward(req, res);	
//		res.sendRedirect("/AwesomeProject/cart/cartAdmin.jsp");
//		return;
//	}
//	
//    /**
//     * @Method #09 insertAdmin 
//	 * @Database_Connection INSERT
//	 **/
//	private void insertByAdmin(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Connection conn = getConn();
//		CartDAO crudor = new CartDAO(conn);
//		
////		System.out.println("Start debugging...");
//		String counter = req.getParameter("counter");
//		Integer up = Integer.parseInt(counter);
//		for(int i =0; i < up; i++) {
//			OrderBean adminBean = new OrderBean();
//			for(int j = 0; j < CartDAO.columnNames.length; j++) { // CartDAOImpl.columnNames.length = 11
//				String pmValue = req.getParameter("new" + String.valueOf(i) + String.valueOf(j));
//				adminBean.assign(j + 1, pmValue);
//			}
//			System.out.println(adminBean.take(1)); // debug用
//			crudor.insertOrder(adminBean);
//		}
//		
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
////				session.removeAttribute(O_ID~~~, e);
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		
//		
////		req.getRequestDispatcher("/cart/cartAdmin.jsp").forward(req, res);	
//		res.sendRedirect("/AwesomeProject/cart/cartAdmin.jsp");
//		return;
//	}
//	
//	/********************************************************************************************************************************************/
//	
//	/**
//     * @SubMethod #01 取得連線
//     * @undone 使用此方法的方法要在最後記得關閉
//     * @Database_Connection 取得連線
//     * @Problem1. InitialContext要關嗎？
//     **/
//    private Connection getConn() {
//    	InitialContext ctx;
//    	Connection conn = null;
//    	
//	    	try 
//	    	{
//	    		if (this.ds == null) 
//	    		{	
//		    		ctx = new InitialContext();
//		    		// 改資料庫名稱
//		    		this.ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/DBDB");
//	    		}
//	    		conn = this.ds.getConnection();
//			} catch (NamingException e) 
//	    	{
//				e.printStackTrace();
//			} catch (SQLException e) 
//	    	{
//				e.printStackTrace();
//			}
//    	
//    	return conn;
//    }
//	/**
//     * @SubMethod #02 萃取數字
//     * @抄自Stackoverflow https://reurl.cc/qm7Z8q
//     * @Example public static void main(String[] args) {
//	 *			    String input = "0-123-abc-456-xyz-789";
//	 *			    String result = stripNonDigits(input);
//	 *			    System.out.println(result);
//	 *			}
//	 * @補充 Integar.parseInt("00077")的結果會跑出77
//     **/
//    public static String stripNonDigits(CharSequence input){
//	    StringBuilder sb = new StringBuilder(input.length());
//	    for(int i = 0; i < input.length(); i++){
//	        char c = input.charAt(i);
//	        if(c > 47 && c < 58) {
//	            sb.append(c);
//	        }
//	    }
//	    return sb.toString();
//    }
//	/**
//     * @SubMethod #03 取最大值
//     **/
//    public static int maxNum(ArrayList<Integer> intArrayList) {
//    	// clone()前後ArrayList記憶體位置會不一樣、不會互相影響
//		ArrayList<Integer> cloned = (ArrayList<Integer>)intArrayList.clone();
//    	while (cloned.size() > 1) {
//    		if(cloned.get(0) >= cloned.get(1)) {
//    			cloned.remove(1);
//    		} else {
//				cloned.remove(0);
//			}
//		}
//		int maxNum = cloned.get(0);
//    	return maxNum;
//    }
//}
