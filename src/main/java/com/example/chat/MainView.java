package com.example.chat;

import com.github.rjeschke.txtmark.Processor;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;


@Route("")
public class MainView extends VerticalLayout {
    private final Storage storage;
    public MainView(Storage storage){
        this.storage = storage;

        Grid<Storage.ChatMessage> grid = new Grid<>();
        grid.setItems(storage.getMessages());
        grid.addColumn(new ComponentRenderer<>(message -> new Html(renderRow(message))))
                .setAutoWidth(true);

        TextField field = new TextField();

        add(
                new H3("Vaadin Chat"),
                grid,
                new HorizontalLayout() {{
                    add(
                    field,
                            new Button("→") {{
                                addClickListener(click -> {
                                    storage.addRecord("", field.getValue());
                                    field.clear();
                                });
                                addClickShortcut(Key.ENTER);
                            }}
                    );

                }}

    );
}
    private static String renderRow(Storage.ChatMessage message) {
        return Processor.process(String.format("**%s**: %s", message.getName(), message.getMessage()));
    }
}

