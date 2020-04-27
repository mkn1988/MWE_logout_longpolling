package org.vaadin.paul.spring;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.Transport;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@Push(transport = Transport.LONG_POLLING)
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        Button button = new Button("Click me",
                e -> Notification.show(bean.getMessage()));
        add(button);

        Button logout = new Button("Logout",
            this::logout);
        add(logout);
    }

    private void logout(ClickEvent<Button> event) {
        event.getSource().getUI()
            .map(UI::getPage)
            .ifPresent(page -> page.setLocation("/logout"));
    }

}
