package com.akr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/wishbutton")
public class WishMessageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String IST_ZONE = "Asia/Kolkata";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter pw = res.getWriter();

        String greeting = getISTGreeting();
        String currentTime = getISTTime();
        String greetingIcon = getGreetingIcon();

        pw.print(getHtmlPage(greeting, greetingIcon, currentTime));
        pw.close();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }

    private String getHtmlPage(String greeting, String icon, String time) {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greetings - AKR Enterprise Portal</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800;900&family=Orbitron:wght@400;700;900&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Inter', sans-serif; background: #0f0f1a; color: #fff; min-height: 100vh; display: flex; align-items: center; justify-content: center; overflow-x: hidden; }
        .bg-animation { position: fixed; top: 0; left: 0; width: 100%%; height: 100%%; z-index: -1; background: radial-gradient(ellipse at 20%% 80%%, rgba(99,102,241,0.15) 0%%, transparent 50%%), radial-gradient(ellipse at 80%% 20%%, rgba(245,158,11,0.1) 0%%, transparent 50%%), #0f0f1a; animation: bgPulse 8s ease-in-out infinite; }
        @keyframes bgPulse { 0%%, 100%% { opacity: 1; } 50%% { opacity: 0.8; } }
        .container { max-width: 700px; width: 90%%; padding: 3rem; text-align: center; background: rgba(255,255,255,0.03); border: 1px solid rgba(255,255,255,0.1); border-radius: 24px; backdrop-filter: blur(20px); animation: fadeInUp 1s ease; }
        @keyframes fadeInUp { from { opacity: 0; transform: translateY(30px); } to { opacity: 1; transform: translateY(0); } }
        .greeting-icon { font-size: 5rem; margin-bottom: 1.5rem; animation: bounce 2s infinite; }
        @keyframes bounce { 0%%, 100%% { transform: translateY(0); } 50%% { transform: translateY(-15px); } }
        h1 { font-family: 'Orbitron', sans-serif; font-size: clamp(1.8rem, 5vw, 3rem); font-weight: 900; margin-bottom: 1rem; background: linear-gradient(135deg, #f59e0b, #fff); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
        .time-display { color: #a1a1aa; font-size: 1rem; margin-bottom: 2rem; font-weight: 500; }
        .message { color: #a1a1aa; font-size: 1.1rem; line-height: 1.8; margin-bottom: 2rem; padding: 0 1rem; }
        .features { display: flex; justify-content: center; gap: 1.5rem; flex-wrap: wrap; margin-bottom: 2.5rem; }
        .feature-item { display: flex; align-items: center; gap: 0.5rem; padding: 0.75rem 1.25rem; background: rgba(255,255,255,0.05); border: 1px solid rgba(255,255,255,0.1); border-radius: 50px; font-size: 0.9rem; color: #fff; }
        .feature-icon { font-size: 1.2rem; }
        .back-btn { display: inline-flex; align-items: center; gap: 0.75rem; padding: 1rem 2.5rem; background: transparent; color: #f59e0b; border: 2px solid #f59e0b; border-radius: 50px; font-size: 1rem; font-weight: 600; text-decoration: none; cursor: pointer; position: relative; overflow: hidden; transition: all 0.4s ease; animation: glowPulse 2s ease-in-out infinite; }
        .back-btn:hover { background: #f59e0b; color: #0f0f1a; box-shadow: 0 0 40px rgba(245,158,11,0.4), 0 0 80px rgba(245,158,11,0.2); transform: translateY(-2px); }
        @keyframes glowPulse { 0%%, 100%% { box-shadow: 0 0 10px rgba(245,158,11,0.3), 0 0 20px rgba(245,158,11,0.1); } 50%% { box-shadow: 0 0 25px rgba(245,158,11,0.4), 0 0 50px rgba(245,158,11,0.2); } }
        @media (max-width: 600px) { .container { padding: 2rem 1rem; } .features { flex-direction: column; align-items: center; } .feature-item { width: 100%%; justify-content: center; } }
    </style>
</head>
<body>
    <div class="bg-animation"></div>
    <div class="container">
        <div class="greeting-icon">%s</div>
        <h1>%s, Ankit!</h1>
        <div class="time-display">%s</div>
        <div class="message">
            <p>Welcome to the AKR Enterprise Portal. Your journey towards digital transformation starts here.</p>
        </div>
        <div class="features">
            <div class="feature-item"><span class="feature-icon">&#9889;</span><span>Real-time Updates</span></div>
            <div class="feature-item"><span class="feature-icon">&#128274;</span><span>Enterprise Security</span></div>
            <div class="feature-item"><span class="feature-icon">&#128640;</span><span>High Performance</span></div>
        </div>
        <a href="index.html" class="back-btn">
            <span>&#8592;</span> Back to Home
        </a>
    </div>
    <script>
        function updateClock() {
            const now = new Date();
            const istOffset = 330;
            const utc = now.getTime() + (now.getTimezoneOffset() * 60000);
            const istTime = new Date(utc + (istOffset * 60000));
            const hour = istTime.getHours();
            const minutes = istTime.getMinutes().toString().padStart(2, '0');
            const seconds = istTime.getSeconds().toString().padStart(2, '0');
            const ampm = hour >= 12 ? 'PM' : 'AM';
            const displayHour = hour %% 12 || 12;
            const days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
            const months = ['January','February','March','April','May','June','July','August','September','October','November','December'];
            const timeText = document.querySelector('.time-display');
            if (timeText) {
                timeText.innerHTML = days[istTime.getDay()] + ', ' + months[istTime.getMonth()] + ' ' + istTime.getDate() + ', ' + istTime.getFullYear() + ' &#8226; ' + displayHour + ':' + minutes + ':' + seconds + ' ' + ampm + ' IST';
            }
        }
        updateClock();
        setInterval(updateClock, 1000);
    </script>
</body>
</html>
""".formatted(icon, greeting, time);
    }

    private String getISTGreeting() {
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of(IST_ZONE));
        int hour = istTime.getHour();
        if (hour >= 5 && hour < 12) return "Good Morning";
        else if (hour >= 12 && hour < 16) return "Good Afternoon";
        else if (hour >= 16 && hour < 20) return "Good Evening";
        else return "Good Night";
    }

    private String getGreetingIcon() {
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of(IST_ZONE));
        int hour = istTime.getHour();
        if (hour >= 5 && hour < 12) return "&#127749;";
        else if (hour >= 12 && hour < 16) return "&#9728;";
        else if (hour >= 16 && hour < 20) return "&#127751;";
        else return "&#127769;";
    }

    private String getISTTime() {
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of(IST_ZONE));
        String dayOfWeek = istTime.getDayOfWeek().toString();
        dayOfWeek = dayOfWeek.charAt(0) + dayOfWeek.substring(1).toLowerCase();
        String month = istTime.getMonth().toString();
        month = month.charAt(0) + month.substring(1).toLowerCase();
        int hour = istTime.getHour();
        int minute = istTime.getMinute();
        int second = istTime.getSecond();
        String ampm = hour >= 12 ? "PM" : "AM";
        int displayHour = hour % 12;
        if (displayHour == 0) displayHour = 12;
        return String.format("%s, %s %d, %d &#8226; %02d:%02d:%02d %s IST",
            dayOfWeek, month, istTime.getDayOfMonth(), istTime.getYear(),
            displayHour, minute, second, ampm);
    }
}