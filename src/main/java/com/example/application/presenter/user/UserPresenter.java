package com.example.application.presenter.user;

import com.example.application.business.abstracts.UserService;
import com.example.application.model.user.User;
import com.example.application.util.modelmapper.ModelMapperService;
import com.example.application.views.adduser.AddUserView;
import com.example.application.views.userlist.UserListView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.data.provider.DataProvider;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;


@Component
public class UserPresenter {

    private final UserService userService;
    private final ModelMapperService modelMapperService;

    public UserPresenter(UserService userService, ModelMapperService modelMapperService) {
        this.userService = userService;
        this.modelMapperService = modelMapperService;
    }



    public void initView(UserListView userListView) {
        userListView.getGrid().setDataProvider(userService.getAllUsers());

        userListView.getAddNewUserButton().addClickListener(event -> {
            userListView.getUI().ifPresent( ui -> ui.navigate(AddUserView.class));
        });
    }

    public void initAddNewUser(AddUserView addUserView) {
        addUserView.getSubmit().addClickListener(event -> {
            try {
                String username = addUserView.getUsername().getValue();
                String password = addUserView.getPassword().getValue();
                String email = addUserView.getEmail().getValue();

                User newUser = User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .build();

                userService.createUser(newUser);

                Notification.show("User created successfully!", 3000, Notification.Position.MIDDLE);
                addUserView.getUI().ifPresent(ui -> ui.navigate(""));
            } catch (ConstraintViolationException e) {
                // Validasyon hatalarını yakala
                Notification.show("Validation error: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            } catch (Exception e) {
                // Diğer hatalar
                Notification.show("Error: " + e.getMessage(), 3000, Notification.Position.MIDDLE);
            }
        });
    }



}
