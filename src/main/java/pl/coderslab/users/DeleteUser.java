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

@WebServlet("/user/delete")
public class DeleteUser extends HttpServlet {
    private static final Logger log = LogManager.getLogger(AddUser.class);
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("doGet from user delete");
        User user;
        int result;

        try {
            result = userDao.delete(Integer.parseInt(req.getParameter("userid")));
//            log.info(users[1]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (result == 1) {
            req.setAttribute("user_removed", "User with id " + req.getParameter("userid") + " removed.");

        } else {
            req.setAttribute("user_removed", "User with id " + req.getParameter("userid") + " not removed.");
        }
        getServletContext().getRequestDispatcher("/users/deleteUser.jsp").forward(req, resp);
    }
}
