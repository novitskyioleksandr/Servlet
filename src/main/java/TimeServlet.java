import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("${timezone}".replace("${timezone}", parseTime(req)));
        resp.getWriter().close();
    }

    private String parseTime(HttpServletRequest request) {
        String timezone = request.getParameter("timezone");
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dat = dateFormatGmt.format(new Date());

        if (request.getParameterMap().containsKey("timezone") && timezone.contains("UTC-")) {

            SimpleDateFormat dateFormatGmt1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String zoneTimeMinus = timezone.replace("UTC-", "GMT-");
            dateFormatGmt1.setTimeZone(TimeZone.getTimeZone(zoneTimeMinus));
            String resultMinusTime = dateFormatGmt1.format(new Date());
            System.out.println("timezoneMinus = " + zoneTimeMinus);
            System.out.println("dat2 = " + resultMinusTime);
            return resultMinusTime+zoneTimeMinus.replace("GMT-", " UTC-");

        } else if (request.getParameterMap().containsKey("timezone") && timezone.contains("UTC ")) {

            SimpleDateFormat dateFormatGmt1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String zoneTimePlus = timezone.replace("UTC ", "GMT+");
            dateFormatGmt1.setTimeZone(TimeZone.getTimeZone(zoneTimePlus));
            String resultPlusTime = dateFormatGmt1.format(new Date());
            System.out.println("timezonePlus = " + zoneTimePlus);
            System.out.println("dat2 = " + resultPlusTime);
            return resultPlusTime+zoneTimePlus.replace("GMT+"," UTC+");

        } else {
            System.out.println("timezone = " + timezone);
            System.out.println("dat = " + dat);
            return dat+" UTC";

        }
    }
}