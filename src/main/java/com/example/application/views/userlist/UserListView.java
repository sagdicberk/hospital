package com.example.application.views.userlist;

import com.example.application.model.user.User;
import com.example.application.presenter.user.UserPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import lombok.Setter;
import org.vaadin.lineawesome.LineAwesomeIconUrl;


@PageTitle("Users")
@Route("")
@Menu(order = 0, icon = LineAwesomeIconUrl.USER)
@Getter
@Setter
public class UserListView extends Div {
    private final Grid<User> grid = new Grid<>(User.class);
    private final Button addNewUserButton = new Button("Add new User");

    public UserListView(UserPresenter userPresenter) {
        configureCSS();
        grid.setColumns("id", "username", "email");
        grid.setPageSize(10);
        grid.getColumnByKey("id").setSortable(true);
        grid.getColumnByKey("username").setSortable(true);
        grid.getColumnByKey("email").setSortable(true);


        add(grid, addNewUserButton);
        userPresenter.initView(this); // Kullanıcıları yüklemek için presenter'ı çağır
    }

    // CSS
    private void configureCSS() {
        addNewUserButton.getStyle().set("position", "relative");
        addNewUserButton.getStyle().set("right", "5px");
        addNewUserButton.getStyle().set("top", "5px");
    }

}
