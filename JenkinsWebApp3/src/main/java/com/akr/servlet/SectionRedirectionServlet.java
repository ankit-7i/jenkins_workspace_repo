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

@WebServlet("/section")
public class SectionRedirectionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String SECTION_ABOUT = "about";
    private static final String SECTION_SERVICES = "services";
    private static final String SECTION_PORTFOLIO = "portfolio";
    private static final String SECTION_CONTACT = "contact";
    private static final String IST_ZONE = "Asia/Kolkata";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter pw = res.getWriter();

        String target = req.getParameter("target");
        String greeting = getISTGreeting();
        String currentTime = getISTTime();
        String sectionTitle = getSectionTitle(target);
        String sectionContent = getSectionContent(target);
        String sectionIcon = getSectionIcon(target);
        String sectionColor = getSectionColor(target);
        String sectionNumber = getSectionNumber(target);

        pw.print(getHtmlPage(sectionTitle, sectionNumber, sectionIcon, sectionColor, greeting, currentTime, sectionContent));
        pw.close();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }

    private String getHtmlPage(String title, String number, String icon, String color,
                               String greeting, String time, String content) {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>%s - AKR Enterprise Portal</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800;900&family=Orbitron:wght@400;700;900&display=swap" rel="stylesheet">
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Inter', sans-serif; background: #0f0f1a; color: #fff; min-height: 100vh; display: flex; align-items: center; justify-content: center; overflow-x: hidden; }
        .bg-animation { position: fixed; top: 0; left: 0; width: 100%%; height: 100%%; z-index: -1; background: radial-gradient(ellipse at 20%% 80%%, rgba(99,102,241,0.15) 0%%, transparent 50%%), radial-gradient(ellipse at 80%% 20%%, rgba(245,158,11,0.1) 0%%, transparent 50%%), #0f0f1a; animation: bgPulse 8s ease-in-out infinite; }
        @keyframes bgPulse { 0%%, 100%% { opacity: 1; } 50%% { opacity: 0.8; } }
        .container { max-width: 800px; width: 90%%; padding: 3rem; text-align: center; position: relative; background: rgba(255,255,255,0.03); border: 1px solid rgba(255,255,255,0.1); border-radius: 24px; backdrop-filter: blur(20px); animation: fadeInUp 1s ease; }
        @keyframes fadeInUp { from { opacity: 0; transform: translateY(30px); } to { opacity: 1; transform: translateY(0); } }
        .section-number { font-family: 'Orbitron', sans-serif; font-size: 8rem; font-weight: 900; opacity: 0.08; line-height: 1; position: absolute; top: -20px; left: 50%%; transform: translateX(-50%%); }
        .icon { font-size: 4rem; margin-bottom: 1.5rem; animation: bounce 2s infinite; }
        @keyframes bounce { 0%%, 100%% { transform: translateY(0); } 50%% { transform: translateY(-10px); } }
        h1 { font-family: 'Orbitron', sans-serif; font-size: clamp(2rem, 5vw, 3.5rem); margin-bottom: 1.5rem; background: linear-gradient(135deg, %s, #fff); -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text; }
        .greeting-box { padding: 1.5rem 2rem; background: rgba(255,255,255,0.05); border: 1px solid rgba(255,255,255,0.1); border-radius: 16px; margin-bottom: 2rem; backdrop-filter: blur(10px); }
        .greeting-text { font-size: 1.5rem; font-weight: 700; color: %s; margin-bottom: 0.5rem; }
        .time-text { color: #a1a1aa; font-size: 0.9rem; }
        .content { color: #a1a1aa; font-size: 1.1rem; line-height: 1.8; margin-bottom: 2rem; }
        .content p { margin-bottom: 1.5rem; }
        .feature-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 1rem; margin: 1.5rem 0; }
        .feature { padding: 1rem; background: rgba(255,255,255,0.03); border: 1px solid rgba(255,255,255,0.08); border-radius: 12px; text-align: left; }
        .feature h4 { font-size: 0.95rem; margin-bottom: 0.3rem; color: #fff; }
        .feature p { font-size: 0.8rem; color: #a1a1aa; margin: 0; }
        .back-btn { display: inline-flex; align-items: center; gap: 0.75rem; padding: 1rem 2.5rem; background: transparent; color: #f59e0b; border: 2px solid #f59e0b; border-radius: 50px; font-size: 1rem; font-weight: 600; text-decoration: none; cursor: pointer; position: relative; overflow: hidden; transition: all 0.4s ease; animation: glowPulse 2s ease-in-out infinite; margin-top: 1rem; }
        .back-btn:hover { background: #f59e0b; color: #0f0f1a; box-shadow: 0 0 40px rgba(245,158,11,0.4), 0 0 80px rgba(245,158,11,0.2); transform: translateY(-2px); }
        @keyframes glowPulse { 0%%, 100%% { box-shadow: 0 0 10px rgba(245,158,11,0.3), 0 0 20px rgba(245,158,11,0.1); } 50%% { box-shadow: 0 0 25px rgba(245,158,11,0.4), 0 0 50px rgba(245,158,11,0.2); } }
        @media (max-width: 600px) { .container { padding: 2rem 1rem; } .section-number { font-size: 5rem; } .feature-grid { grid-template-columns: 1fr; } }
    </style>
</head>
<body>
    <div class="bg-animation"></div>
    <div class="container">
        <div class="section-number">%s</div>
        <div class="icon">%s</div>
        <h1>%s</h1>
        <div class="greeting-box">
            <div class="greeting-text">%s, Ankit!</div>
            <div class="time-text">%s</div>
        </div>
        <div class="content">%s</div>
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
            const timeText = document.querySelector('.time-text');
            if (timeText) {
                const dateStr = istTime.toLocaleDateString('en-IN', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' });
                timeText.textContent = dateStr + ' | ' + displayHour + ':' + minutes + ':' + seconds + ' ' + ampm + ' IST';
            }
        }
        updateClock();
        setInterval(updateClock, 1000);
    </script>
</body>
</html>
""".formatted(title, color, color, number, icon, title, greeting, time, content);
    }

    private String getISTGreeting() {
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of(IST_ZONE));
        int hour = istTime.getHour();
        if (hour >= 5 && hour < 12) return "Good Morning";
        else if (hour >= 12 && hour < 16) return "Good Afternoon";
        else if (hour >= 16 && hour < 20) return "Good Evening";
        else return "Good Night";
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
        return String.format("%s, %s %d, %d | %02d:%02d:%02d %s IST",
            dayOfWeek, month, istTime.getDayOfMonth(), istTime.getYear(),
            displayHour, minute, second, ampm);
    }

    private String getSectionTitle(String target) {
        return switch (target != null ? target.toLowerCase() : "") {
            case SECTION_ABOUT -> "About Us";
            case SECTION_SERVICES -> "Our Services";
            case SECTION_PORTFOLIO -> "Our Portfolio";
            case SECTION_CONTACT -> "Contact Us";
            default -> "Welcome to AKR Portal";
        };
    }

    private String getSectionContent(String target) {
        return switch (target != null ? target.toLowerCase() : "") {
            case SECTION_ABOUT -> """
                <p>AKR is a forward-thinking technology company dedicated to delivering enterprise-grade solutions that drive digital transformation. Our team of experts combines deep technical knowledge with business acumen to create solutions that matter.</p>
                <div class="feature-grid">
                    <div class="feature"><h4>Our Mission</h4><p>To empower businesses with innovative technology solutions.</p></div>
                    <div class="feature"><h4>Our Vision</h4><p>Becoming the most trusted technology partner worldwide.</p></div>
                    <div class="feature"><h4>Innovation</h4><p>Pushing boundaries with cutting-edge R&D.</p></div>
                </div>
                """;
            case SECTION_SERVICES -> """
                <p>From cloud architecture to AI integration, we offer a full spectrum of services tailored to your business needs. Our solutions are designed to scale with your growth.</p>
                <div class="feature-grid">
                    <div class="feature"><h4>Cloud Solutions</h4><p>Scalable cloud infrastructure and migration.</p></div>
                    <div class="feature"><h4>AI & ML</h4><p>Intelligent automation and analytics.</p></div>
                    <div class="feature"><h4>Cybersecurity</h4><p>Enterprise-grade security frameworks.</p></div>
                    <div class="feature"><h4>Data Analytics</h4><p>Transform data into insights.</p></div>
                </div>
                """;
            case SECTION_PORTFOLIO -> """
                <p>A showcase of our most impactful projects that have transformed businesses across industries. Each project represents our commitment to excellence.</p>
                <div class="feature-grid">
                    <div class="feature"><h4>FinTech Platform</h4><p>Banking solution serving 2M+ users daily.</p></div>
                    <div class="feature"><h4>HealthTech App</h4><p>AI diagnostic tool with 99.2% accuracy.</p></div>
                    <div class="feature"><h4>E-Commerce Suite</h4><p>Marketplace handling $500M+ annually.</p></div>
                </div>
                """;
            case SECTION_CONTACT -> """
                <p>Ready to transform your business? Let's start a conversation about your next big idea. Our team is here to help you succeed.</p>
                <div class="feature-grid">
                    <div class="feature"><h4>Email</h4><p>hello@akr-enterprise.com</p></div>
                    <div class="feature"><h4>Phone</h4><p>+91 98765 43210</p></div>
                    <div class="feature"><h4>Address</h4><p>Bangalore, Karnataka, India</p></div>
                </div>
                """;
            default -> "<p>Welcome to the AKR Enterprise Portal. Please select a section to explore.</p>";
        };
    }

    private String getSectionIcon(String target) {
        return switch (target != null ? target.toLowerCase() : "") {
            case SECTION_ABOUT -> "&#127970;";
            case SECTION_SERVICES -> "&#9889;";
            case SECTION_PORTFOLIO -> "&#128640;";
            case SECTION_CONTACT -> "&#128222;";
            default -> "&#127775;";
        };
    }

    private String getSectionColor(String target) {
        return switch (target != null ? target.toLowerCase() : "") {
            case SECTION_ABOUT -> "#6366f1";
            case SECTION_SERVICES -> "#f59e0b";
            case SECTION_PORTFOLIO -> "#10b981";
            case SECTION_CONTACT -> "#ec4899";
            default -> "#6366f1";
        };
    }

    private String getSectionNumber(String target) {
        return switch (target != null ? target.toLowerCase() : "") {
            case SECTION_ABOUT -> "01";
            case SECTION_SERVICES -> "02";
            case SECTION_PORTFOLIO -> "03";
            case SECTION_CONTACT -> "04";
            default -> "00";
        };
    }
}