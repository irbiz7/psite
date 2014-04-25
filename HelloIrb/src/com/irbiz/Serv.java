package com.irbiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Serv extends HttpServlet {
	String dtitle = "serv";
	Calendar calendar = GregorianCalendar.getInstance();
	String[] parts = {"irbiz", "library", "java"};
	public String[] irbiz = {"resume", "photo", "link"};
	public String[] library = {"fund", "human", "tech", "society"};
	public String[] java = {"backsoundplayer", "remind", "node"};
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html; charset=utf8");
		String part =  req.getParameter("part");
		String tab =  req.getParameter("tab");
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<!DOCTYPE html>\n");
		sb.append("\n<html>\n");
		head(sb, part);
		sb.append("\n<body>\n");
		
		div(sb, "sitemap", "width:100px; float:left; border-right:1px solid; border-bottom:1px solid;");
			for(String s : parts){sb.append("<a href='serv?part="+ s +"'>"+ s +"</a> <br/>\n");}
			sb.append("<br/>\n");
			sb.append("<span>now "+ GregorianCalendar.getInstance().get(Calendar.DAY_OF_YEAR) +"</span> <br/>\n");
			sb.append("<span>init "+ calendar.get(Calendar.HOUR) +" "+ calendar.get(Calendar.MINUTE)
					+" "+ calendar.get(Calendar.DAY_OF_YEAR) +"</span> <br/>\n");
			sb.append("<span>deploy - 68</span> <br/>\n");
		sb.append("</div>\n");
		if(part==null){ sb.append("\n</body>\n\n</html>"); resp.getWriter().println(sb.toString()); return; }
		
		div(sb, "sitepart", "margin-left:100px; border-bottom:1px solid;");
			sb.append("<img alt='"+ part +"' src='icon/"+ part +".png' style='float:left; max-width:64px; max-height:64px;'/>");
			sb.append("<div id='name' style='margin-left:66px; height:32px;'>");
			sb.append("<p style='margin:auto; '> "+ part +"</p>"); sb.append("</div>\n");
			sb.append("<div id='tab' style='margin-left:66px; height:32px;'>");
				//sb.append("<a href='serv?part="+ part +"&tab="+ tab +"'>photo</a>\n");
				for(String s : field(part)){sb.append("<span> </span><a href='serv?part="+ part +"&tab="+ s +"'>"+ s +"</a><span> </span> \n");}
			sb.append("</div>");
			//sitepart(sb, part, "photo");
		sb.append("</div>\n");
		if(tab==null){ sb.append("\n</body>\n\n</html>"); resp.getWriter().println(sb.toString()); return; }
		
		div(sb, "content", "margin-left:100px; ");
			content(sb, part, tab);
		sb.append("</div>\n");
		
		sb.append("\n</body>\n\n</html>"); resp.getWriter().println(sb.toString());
	}
	
	void head(StringBuilder sb, String title){
		sb.append("\n<head>\n");
			sb.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'/>\n");
			sb.append("<title>"+ (title==null ? dtitle : title) +"</title>\n");
		sb.append("\n</head>\n");
	}
	void div(StringBuilder sb, String id, String css){ sb.append("<div id='"+ id +"' style='"+ css +"'>\n"); }
	
	void content(StringBuilder sb, String part, String tab){
		switch(part +"_" + tab){
			case"irbiz_resume":
				sb.append("<p>Евгений Андреевич Кочетков</p>");
				sb.append("<p>Одесский национальный политехнический университет</p>");
				sb.append("<p>Программист: Java, JavaScript</p>");
				sb.append("<p>Новые Беляры, Одесса, Украина</p>");
			break;
			case"irbiz_photo":
				//sb.append("<img src='https://www.dropbox.com/s/zekf8qe4urywta1/me.jpg?dl=1'/>");
				sb.append("<img src='https://cloclo11.cloud.mail.ru/weblink/thumb/xw0/33b97f51fdeb/bestoftwistedsifter21.jpg'/>");
				//sb.append("<img src='http://ubuntuone.com/4XOSQ8mLpxJgjUutDtdMhs'/>");
			break;
			case"irbiz_link":
				sb.append("<a href='mailto:kea_17@inbox.ru'>kea_17@inbox.ru</a> <span>- mail.ru</span> <br/> \n");
				sb.append("<a href='mailto:irbiz77@gmail.com'>irbiz77@gmail.com</a> <span>- gmail</span> \n");
				sb.append("<hr/>");
				//sb.append("<a href='callto:+380682559972'>+380682559972</a> <span>- звонить</span> <br/> \n");
				sb.append("<a href='tel:+380682559972'>+380682559972</a> <span>- телефон</span> <br/> \n");
				//sb.append("<a href='callto:irbiz7'>irbiz7</a> <span>- звонить</span> <br/> \n");
				sb.append("<a href='skype:irbiz7'>irbiz7</a> <span>- skype</span> <br/> \n");
				sb.append("<hr/>");
				sb.append("<a href='https://vk.com/irbiz'>VKontakte</a> \n");
				
				
			break;
			
		}
	}
	
	void sitepart(StringBuilder sb, String part, String tab){
		sb.append("<img alt='irbiz' src='greencross.png' width='64' height='64' style='float:left;'/>");
		sb.append("<div id='name' style='margin-left:66px; height:32px;'>");
		sb.append("<p style='margin:auto; '> irbiz</p>");
		sb.append("</div>");
		sb.append("<div id='tab' style='margin-left:66px; height:32px;'>");
		sb.append("<a href='serv?part="+ part +"&tab="+ tab +"'>photo</a>\n");
		sb.append("</div>");
	}
	
	String[] field(String part){
		String[] sm = null;
		try { sm = (String[]) this.getClass().getField(part).get(this);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) { e.printStackTrace(); }
		return sm;
	}
}
