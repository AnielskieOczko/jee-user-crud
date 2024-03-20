package pl.coderslab.users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.coderslab.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import pl.coderslab.entities.UserDao;


@WebServlet("/user/add")
public class AddUser extends HttpServlet {
    private static final Logger log = LogManager.getLogger(UserList.class);
    UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("add user doGet running...");
        log.info(req.getParameter("user_login"));
        getServletContext().getRequestDispatcher("/users/addUserForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("add user doPost running...");
        String userName = req.getParameter("user_name");
        String userEmail = req.getParameter("user_email");
        String userPassword = req.getParameter("user_password");

        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setEmail(userEmail);
        newUser.setPassword(userPassword);
        User createdUser = userDao.create(newUser);
        log.info(createdUser);

        if (createdUser.getId() > 0) {
            resp.sendRedirect("/user/list");
        } else {
            req.setAttribute("error_create_user", "Error during user create, try again");
            getServletContext().getRequestDispatcher("/users/addUserForm.jsp").forward(req, resp);
        }
    }
}
