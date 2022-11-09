package com.example.Bsp_102_GuestBook;

import com.example.Bsp_102_GuestBook.data.GuestBookEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "guestBookServlet", value = "/GuestBookServlet")
public class GuestBookServlet extends HttpServlet {

    List<GuestBookEntry> bookEntries;

    @Override
    public void init() throws ServletException {
        bookEntries = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Guestbook</h1>");

            generateForm(out);
            generateGuestBookEntries(out);
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<h1>Guestbook</h1>");

            generateForm(out);
            req.setCharacterEncoding("UTF-8");
            Enumeration<String> parameters = req.getParameterNames();

            String nickname = req.getParameter(parameters.nextElement());
            String email = req.getParameter(parameters.nextElement());
            String message = req.getParameter(parameters.nextElement());

            if (nickname.isEmpty() || email.isEmpty() || message.isEmpty()) {
                out.println("<script>");

                if (nickname.isEmpty()) {
                    out.println("document.getElementById(\"nickname\").placeholder = \"************\";");
                    out.println("document.getElementById(\"lblNickname\").style.fontStyle = \"italic\";");
                } else {
                    out.println("document.getElementById(\"nickname\").value = \"" + nickname + "\";");
                }

                if (email.isEmpty()){
                    out.println("document.getElementById(\"email\").placeholder = \"************\";");
                    out.println("document.getElementById(\"lblEmail\").style.fontStyle = \"italic\";");
                } else {
                    out.println("document.getElementById(\"email\").value = \"" + email + "\";");
                }

                if (message.isEmpty()){
                    out.println("document.getElementById(\"message\").placeholder = \"************\";");
                    out.println("document.getElementById(\"lblMessage\").style.fontStyle = \"italic\";");
                } else {
                    out.println("document.getElementById(\"message\").value = \"" + message + "\";");
                }

                out.println("</script>");
            } else {
                GuestBookEntry entry = new GuestBookEntry(nickname, email, message);
                bookEntries.add(entry);
            }
            generateGuestBookEntries(out);
            out.println("</body></html>");
        }
    }

    private void generateForm(PrintWriter out) {
        out.println("<form action=\"\" method=\"post\">\n" +
                "  <label id=\"lblNickname\" for=\"nickname\">Nickname:</label><br>\n" +
                "  <input type=\"text\" id=\"nickname\" name=\"nickname\"><br><br>\n" +
                "  <label id=\"lblEmail\"for=\"email\">E-Mail:</label><br>\n" +
                "  <input type=\"email\" id=\"email\" name=\"email\"><br><br>\n" +
                "  <label id=\"lblMessage\" for=\"message\">Message:</label><br>\n" +
                "  <textarea type=\"text\" id=\"message\" name=\"message\">\n</textarea><br>" +
                "  <input type=\"submit\" value=\"Submit\">" +
                "</form>");
    }

    private void generateGuestBookEntries(PrintWriter out) {
        bookEntries.forEach(guestBookEntry -> {
            out.println("<hr><span>Nickname: " + guestBookEntry.getNickname() + "</span><br>");
            out.println("<span>E-Mail: " + guestBookEntry.getEmail() + "</span><br>");
            out.println("<span>Message: " + guestBookEntry.getMessage() + "</span><br>");
        });
    }
}
