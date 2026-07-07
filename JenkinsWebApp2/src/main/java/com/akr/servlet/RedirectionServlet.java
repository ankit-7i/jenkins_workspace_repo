package com.akr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/redirect")
public class RedirectionServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// which social icon was clicked on index.html, e.g. redirect?platform=linkedin
		String platform = req.getParameter("platform");
		if (platform == null) {
			platform = "";
		}

		String targetUrl;
		String displayName;
		String accentColor;
		String iconClass;

		// map the platform key to the real profile link.
		switch (platform.toLowerCase()) {

			case "linkedin":
				targetUrl = "https://www.linkedin.com/in/ankitrout07";
				displayName = "LinkedIn";
				accentColor = "#0A66C2";
				iconClass = "fa-brands fa-linkedin-in";
				break;

			case "github":
				targetUrl = "https://github.com/ankit-7i";
				displayName = "GitHub";
				accentColor = "#f0f6fc";
				iconClass = "fa-brands fa-github";
				break;

			case "instagram":
				targetUrl = "https://www.instagram.com/ankit_rout_7_";
				displayName = "Instagram";
				accentColor = "#dd2a7b";
				iconClass = "fa-brands fa-instagram";
				break;

			case "x":
				targetUrl = "https://x.com/AnkitRout07";
				displayName = "X";
				accentColor = "#f5f7ff";
				iconClass = "fa-brands fa-x-twitter";
				break;

			case "facebook":
				targetUrl = "https://www.facebook.com/ankitrout07/";
				displayName = "Facebook";
				accentColor = "#1877F2";
				iconClass = "fa-brands fa-facebook-f";
				break;

			case "whatsapp":
				targetUrl = "https://wa.me/917682949708"; // TODO: put real WhatsApp number (with country code, no + or spaces)
				displayName = "WhatsApp";
				accentColor = "#25D366";
				iconClass = "fa-brands fa-whatsapp";
				break;

			case "email":
				targetUrl = "mailto:ankitrout513151979@gmail.com"; 
				displayName = "Email";
				accentColor = "#EA4335";
				iconClass = "fa-solid fa-envelope";
				break;

			case "portfolio":
				targetUrl = "https://your-portfolio-website.com"; 
				displayName = "Portfolio";
				accentColor = "#2DD4BF";
				iconClass = "fa-solid fa-globe";
				break;

			default:
				
				res.sendRedirect("index.html");
				return;
		}

		// set response content type
		res.setContentType("text/html");

		// get PrintWriter object
		PrintWriter pw = res.getWriter();

		// write the loading / redirect page
		pw.println("<!DOCTYPE html>");
		pw.println("<html lang='en'>");
		pw.println("<head>");
		pw.println("<meta charset='UTF-8'>");
		pw.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		pw.println("<meta name='theme-color' content='#0b1020'>");
		pw.println("<title>Redirecting to " + displayName + "...</title>");
		pw.println("<link rel='preconnect' href='https://fonts.googleapis.com'>");
		pw.println("<link href='https://fonts.googleapis.com/css2?family=Sora:wght@600;700&family=Inter:wght@400;500&family=JetBrains+Mono:wght@500&display=swap' rel='stylesheet'>");
		pw.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css'>");
		pw.println("<link rel='stylesheet' href='style.css'>");
		pw.println("<noscript><meta http-equiv='refresh' content='0; url=" + targetUrl + "'></noscript>");
		pw.println("</head>");
		pw.println("<body>");

		pw.println("<div class='bg-orbs'>");
		pw.println("<span class='orb orb1'></span>");
		pw.println("<span class='orb orb2'></span>");
		pw.println("<span class='orb orb3'></span>");
		pw.println("</div>");

		pw.println("<main class='loader-card'>");
		pw.println("<div class='loader-ring-wrap'>");
		pw.println("<span class='loader-ring'></span>");
		pw.println("<i class='" + iconClass + " platform-icon' style='color:" + accentColor + "'></i>");
		pw.println("</div>");
		pw.println("<h1 class='loader-title'>Taking you to " + displayName + "</h1>");
		pw.println("<p class='loader-sub'>// please wait a moment</p>");
		pw.println("<div class='progress-track'><span class='progress-fill'></span></div>");
		pw.println("<a class='home-btn' href='index.html'><i class='fa-solid fa-arrow-left'></i> Back to Home</a>");
		pw.println("</main>");

		// redirect to the real profile after the loading animation finishes
		pw.println("<script>");
		pw.println("setTimeout(function(){ window.location.href = '" + targetUrl + "'; }, 2600);");
		pw.println("</script>");

		pw.println("</body>");
		pw.println("</html>");

		// close the stream
		pw.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}