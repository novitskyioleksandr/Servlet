import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TimeZone;

@WebFilter(value = "/time/*")
public class TimezoneValidateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {

        String timeValue = req.getParameter("timezone");
        if(timeValue==null){
            chain.doFilter(req, resp);
        }
        switch (timeValue) {

            case "UTC-9:30", "UTC 6:30", "UTC 9:30", "UTC 10:30", "UTC 12:45", "UTC-4:30", "UTC 5:30", "UTC 3:30", "UTC 5:45","UTC " ->
                    chain.doFilter(req, resp);
            case  "UTC 1", "UTC 2", "UTC 3", "UTC 4", "UTC 5", "UTC 6", "UTC 7", "UTC 8", "UTC 9", "UTC 10", "UTC 11", "UTC 12", "UTC 13", "UTC 14" ->
                    chain.doFilter(req, resp);
            case "UTC-1", "UTC-2", "UTC-3", "UTC-4", "UTC-5", "UTC-6", "UTC-7", "UTC-8", "UTC-9", "UTC-10", "UTC-11" ->
                    chain.doFilter(req, resp);
            default -> {
                resp.setStatus(400);
                resp.getWriter().close();
            }
        }
    }
}

