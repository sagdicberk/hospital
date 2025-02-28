package com.example.application.views.adduser;

import com.example.application.presenter.user.UserPresenter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Route("add-user")
@PageTitle("Ekle")
@Getter
@Setter
public class AddUserView extends Div {
    private final TextArea username = new TextArea("Username");
    private final PasswordField password = new PasswordField("Password");
    private final EmailField email = new EmailField("Email");

    private final Button submit = new Button("Kaydet");
    private final FormLayout formLayout = new FormLayout();

    public AddUserView(UserPresenter userPresenter) {
        setFormLayout();
        add(formLayout);
        userPresenter.initAddNewUser(this);

    }

    private void setFormLayout() {
        formLayout.addFormItem(username, "Username");
        formLayout.addFormItem(password, "Password");
        formLayout.addFormItem(email, "Email");
        formLayout.addFormItem(submit, "Kayıt"); // No label for the submit button

        formLayout.getStyle().set("margin", "1rem");
        formLayout.getStyle().set("background-color", "#f9f9f9"); // Arka plan rengi
        formLayout.getStyle().set("padding", "20px"); // Padding
        formLayout.getStyle().set("border-radius", "8px"); // Kenar yuvarlama
        formLayout.getStyle().set("box-shadow", "0 2px 5px rgba(0, 0, 0, 0.1)");
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0px", 1),  // Küçük ekranlar için tek sütun
                new FormLayout.ResponsiveStep("600px", 2) // Büyük ekranlar için 2 sütun
        );
    }

}
