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

@WebServlet("/user/edit")
public class EditUser extends HttpServlet {
    private static final Logger log = LogManager.getLogger(EditUser.class);
    UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("edit user doGet running...");
        log.info(req.getParameter("userid"));
        User user;

        try {
            user = userDao.read(Integer.parseInt(req.getParameter("userid")));

//            log.info(users[1]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("user", user);


        getServletContext().getRequestDispatcher("/users/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("add user doPost running...");
        int userId = Integer.parseInt(req.getParameter("user_id"));
        String userName = req.getParameter("user_name");
        String userEmail = req.getParameter("user_email");
        String userPassword = req.getParameter("user_password");

        User user = new User();
        user.setId(userId);
        user.setUserName(userName);
        user.setEmail(userEmail);
        if (userPassword != null) {
            user.setEmail(userEmail);
        } else {
            log.info("do not change password");
        }
        try {
            userDao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
