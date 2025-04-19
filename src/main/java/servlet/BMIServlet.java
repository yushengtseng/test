package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Util;

@WebServlet("/bmi")
public class BMIServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.編碼設定
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		// 2.接收參數
		String height = req.getParameter("h");
		String weight = req.getParameter("w");
		
		// 3.驗證參數
		if(!(Util.isDouble(height)&&(Util.isDouble(weight)))) {
			resp.getWriter().print("身高體重輸入錯誤");
			return;
		}
		
		// 4.計算 bmi
		double h = Double.parseDouble(height);
		double w = Double.parseDouble(weight);
		double bmiValue = getBMIValue(h, w);
		String result = getBMIResult(bmiValue);
		
		// 5.印出資訊
		//resp.getWriter().print("BMI 值 = " + bmiValue + " (" + result + ")");
		resp.getWriter().print(String.format("BMI 值 = %.2f (%s)", bmiValue, result));
	}
	
	// 計算 bmi 值
	private double getBMIValue(double h, double w) {
		return w / Math.pow(h/100, 2);
	}
	
	
	// bmi 判讀
	private String getBMIResult(double bmiValue) {
		return bmiValue <= 18 ? "過輕" : (bmiValue > 23) ? "過重" : "正常";
	}
	
	
}