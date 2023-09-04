package co.micol.example.ajax.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.micol.example.ajax.service.ToDoService;
import co.micol.example.ajax.serviceImpl.ToDoServiceImpl;

@WebServlet("/AjaxToDoDel.do")
public class AjaxToDoDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		ToDoService svc = new ToDoServiceImpl();
		int e = Integer.parseInt(no);
		Map<String, String> map = new HashMap<>();
				
		if(svc.delToDo(e)) {
			map.put("retCode", "Success");
			map.put("result", "삭제성공");
		} else {
			map.put("retCode", "Fail");
			map.put("result", "삭제실패");
		}
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(map);
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
