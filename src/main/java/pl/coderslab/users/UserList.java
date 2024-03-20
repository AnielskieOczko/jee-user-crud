package pl.coderslab.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.coderslab.entities.User;
import pl.coderslab.entities.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/list")
public class UserList extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserList.class);

    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User[] users;
        try {
            users = userDao.findAll();

            log.info(users[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/users/list.jsp").forward(req, resp);
    }
}
