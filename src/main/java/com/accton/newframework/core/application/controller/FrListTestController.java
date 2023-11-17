package com.accton.newframework.core.application.controller;

import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.application.dto.response.screen.BodyScreen;
import com.accton.newframework.core.application.dto.response.screen.Component;
import com.accton.newframework.core.application.dto.response.screen.ListDirectionComponent;
import com.accton.newframework.core.application.dto.response.screen.NewFrameWorkScreen;
import com.accton.newframework.core.application.dto.response.screen.action.Action;
import com.accton.newframework.core.application.dto.response.screen.action.OnPress;
import com.accton.newframework.core.application.dto.response.screen.component.ButtonComponent;
import com.accton.newframework.core.application.dto.response.screen.component.FormItemData;
import com.accton.newframework.core.application.dto.response.screen.component.button.ButtonData;
import com.accton.newframework.core.application.dto.response.screen.component.button.ButtonIcon;
import com.accton.newframework.core.application.dto.response.screen.component.tab.TabData;
import com.accton.newframework.core.application.dto.response.screen.component.table.ColumnHeader;
import com.accton.newframework.core.application.dto.response.screen.component.table.TableData;
import com.accton.newframework.core.application.dto.response.screen.filter.DropDownEnum;
import com.accton.newframework.core.application.dto.response.screen.filter.FilterData;
import com.accton.newframework.core.application.dto.response.screen.filter.FilterOption;
import com.accton.newframework.core.application.dto.response.screen.type.ComponentEnum;
import com.accton.newframework.core.application.dto.response.screen.type.DirectionEnum;
import com.accton.newframework.core.application.dto.response.screen.type.SendRequestMethod;
import com.accton.newframework.core.domain.frlist.FrListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/fr-lists")
public class FrListTestController {

    private final FrListService frListService;

    public FrListTestController(FrListService frListService) {
        this.frListService = frListService;
    }

    @GetMapping("/detail/screen")
    public Object get(@RequestParam("parent_id") Long parentId) throws Exception {
        FrListResponse res = frListService.getById(parentId);

        return new NewFrameWorkScreen(

                // Screen Name
                new Component(ComponentEnum.HEADER_TEXT, "Lists: " + res.getName()),
                BodyScreen.builder()
                        .top(
                                new ListDirectionComponent(
                                        Arrays.asList(
                                                Component.builder()
                                                        .type(ComponentEnum.BUTTON)
                                                        .data(ButtonData.builder()
                                                                .icon(ButtonIcon.ADD)
                                                                .onPress(null)
                                                                .build())
                                                        .build(),
                                                Component.builder()
                                                        .type(ComponentEnum.BUTTON)
                                                        .data(ButtonData.builder()
                                                                .icon(ButtonIcon.DELETE)
                                                                .onPress(null)
                                                                .build())
                                                        .build()
                                        ),
                                        DirectionEnum.HORIZONTAL
                                )
                        )
                        .body(
                                new ListDirectionComponent(
                                        ComponentEnum.TAB,
                                        Arrays.asList(
                                                Component.builder()
                                                        .data(TabData.builder()
                                                                .label("General Infomation")
                                                                .build())
                                                        .build(),
                                                Component.builder()
                                                        .data(TabData.builder()
                                                                .label("List")
                                                                .build())
                                                        .build(),
                                                Component.builder()
                                                        .data(TabData.builder()
                                                                .label("Where Used")
                                                                .build())
                                                        .build()
                                        ),
                                        null
                                )
                        )
                        .bottom(
                                new ListDirectionComponent(
                                        Arrays.asList(
                                                Component.builder()
                                                        .type(ComponentEnum.BUTTON)
                                                        .data(ButtonData.builder()
                                                                .icon(ButtonIcon.REFRESH)
                                                                .onPress(OnPress.builder()
                                                                        .action(Action.SEND_REQUEST)
                                                                        .url("/api/fr-lists/detail/screen?parent_id=" + parentId)
                                                                        .method(SendRequestMethod.GET)
                                                                        .build())
                                                                .build())
                                                        .build(),
                                                Component.builder()
                                                        .type(ComponentEnum.BUTTON)
                                                        .data(ButtonData.builder()
                                                                .icon(ButtonIcon.CLOSE)
                                                                .onPress(null)
                                                                .build())
                                                        .build()
                                        ),
                                        DirectionEnum.HORIZONTAL
                                )
                        )
                        .build()
        );
    }


    @GetMapping("/screen")
    public Object getFrListScreen() {
        List<Component> components = new ArrayList<>();


        // filter
        components.add(Component.builder()
                .type(ComponentEnum.FILTER)
                .data(Arrays.asList(
                        new FilterData(
                                Arrays.asList(
                                        new FilterOption(DropDownEnum.ID, DropDownEnum.ID.name()),
//                                        new FilterOption(DropDownEnum.NAME, DropDownEnum.NAME.name()),
                                        new FilterOption(DropDownEnum.DESCRIPTION, DropDownEnum.DESCRIPTION.name())
                                ),
                                "Filter by",
                                "field_type"
                        ),
                        new FilterData(
                                Arrays.asList(
                                        new FilterOption(DropDownEnum.STARTS_WITH, DropDownEnum.STARTS_WITH.name()),
//                                        new FilterOption(DropDownEnum.SHOW_ALL, DropDownEnum.SHOW_ALL.name()),
//                                        new FilterOption(DropDownEnum.CONTAINS, DropDownEnum.CONTAINS.name()),
//                                        new FilterOption(DropDownEnum.END_WITH, DropDownEnum.END_WITH.name()),
                                        new FilterOption(DropDownEnum.EQUALS, DropDownEnum.EQUALS.name())
                                ),
                                "Match if",
                                "match_if"),
                        new FilterData(null, "Value", "content"),

                        // button apply
                        new ButtonComponent(ButtonData.builder()
                                .text("Apply")
                                .onPress(OnPress.builder()
                                        .action(Action.SEND_REQUEST)
                                        .method(SendRequestMethod.POST)
                                        .url("/api/fr-lists/list")
                                        .actionName("setTableData")
                                        .build())
                                .build())
                ))
                .build());

        // table
        components.add(
                buildTableData(new ArrayList<>())
        );

        ListDirectionComponent listDirectionComponent = new ListDirectionComponent(components, DirectionEnum.VERTICAL);
        return new NewFrameWorkScreen(
                // Screen Name
                new Component(ComponentEnum.HEADER_TEXT, "Lists of Marco"),

                BodyScreen.builder()
                        // ui of header
                        .top(new ListDirectionComponent(
                                Arrays.asList(
                                        // add
                                        new ButtonComponent(ButtonData.builder()
                                                .icon(ButtonIcon.ADD)
                                                .onPress(OnPress.builder()
                                                        .action(Action.CLICK)
                                                        .actionName("openCreateNewDialog")
                                                        .data(new NewFrameWorkScreen(
                                                                new Component(ComponentEnum.HEADER_TEXT, "Create List"),
                                                                BodyScreen.builder()
                                                                        .body(Component.builder()
                                                                                .type(ComponentEnum.FORM)
                                                                                .data(new ListDirectionComponent(
                                                                                        Arrays.asList(
                                                                                                new ListDirectionComponent(
                                                                                                                    Arrays.asList(
                                                                                                                            Component.builder()
                                                                                                                                    .type(ComponentEnum.TEXT_INPUT)
                                                                                                                                    .data(FormItemData.builder()
                                                                                                                                            .label("Name")
                                                                                                                                            .key("name")
                                                                                                                                            .build())
                                                                                                                                    .build(),
                                                                                                                            Component.builder()
                                                                                                                                    .type(ComponentEnum.TEXT_INPUT)
                                                                                                                                    .data(FormItemData.builder()
                                                                                                                                            .label("MArco input")
                                                                                                                                            .key("test")
                                                                                                                                            .build())
                                                                                                                                    .build(),
                                                                                                                            Component.builder()
                                                                                                                                    .type(ComponentEnum.TEXT_INPUT)
                                                                                                                                    .data(FormItemData.builder()
                                                                                                                                            .label("Category")
                                                                                                                                            .key("category")
                                                                                                                                            .build())
                                                                                                                                    .build(),
                                                                                                                            Component.builder()
                                                                                                                                    .type(ComponentEnum.TEXT_INPUT)
                                                                                                                                    .data(FormItemData.builder()
                                                                                                                                            .label("Description")
                                                                                                                                            .key("description")
                                                                                                                                            .build())
                                                                                                                                    .build(),
                                                                                                                            Component.builder()
                                                                                                                                    .type(ComponentEnum.TEXT_INPUT)
                                                                                                                                    .data(FormItemData.builder()
                                                                                                                                            .label("Status")
                                                                                                                                            .key("status")
                                                                                                                                            .build())
                                                                                                                                    .build()
                                                                                                                    )
                                                                                                        ,DirectionEnum.VERTICAL),
                                                                                                new ListDirectionComponent(
                                                                                                        Arrays.asList(
                                                                                                                new ButtonComponent(ButtonData.builder()
                                                                                                                        .text("Ok")
                                                                                                                        .onPress(OnPress.builder()
                                                                                                                                .action(Action.SEND_REQUEST)
                                                                                                                                .method(SendRequestMethod.POST)
                                                                                                                                .url("/api/fr-lists/add")
                                                                                                                                .build())
                                                                                                                        .build()),
                                                                                                                new ButtonComponent(ButtonData.builder()
                                                                                                                        .text("Cancel")
                                                                                                                        .onPress(OnPress.builder()
                                                                                                                                .action(Action.CLICK)
                                                                                                                                .actionName("closeCreateNewDialog")
                                                                                                                                .build())
                                                                                                                        .build())
                                                                                                        ),
                                                                                                        DirectionEnum.HORIZONTAL
                                                                                                )
                                                                                        ),
                                                                                        DirectionEnum.VERTICAL
                                                                                ))
                                                                                .build())
                                                                        .build()
                                                        ))
                                                        .build())
                                                .build()),

                                        // delete
                                        new ButtonComponent(ButtonData.builder()
                                                .icon(ButtonIcon.DELETE)
                                                .build())
                                ),
                                DirectionEnum.HORIZONTAL
                        ))
                        // ui of body
                        .body(listDirectionComponent)

                        // List button of bottom
                        .bottom(new ListDirectionComponent(
                                Arrays.asList(
                                        new ButtonComponent(ButtonData.builder()
                                                .icon(ButtonIcon.REFRESH)
                                                .onPress(OnPress.builder()
                                                        .action(Action.SEND_REQUEST)
                                                        .url("/api/fr-lists/list")
                                                        .method(SendRequestMethod.POST)
                                                        .build())
                                                .build()),
                                        new ButtonComponent(
                                                ButtonData.builder()
                                                        .icon(ButtonIcon.CLOSE)
                                                        .onPress(null)
                                                        .build()
                                        )
                                ),
                                DirectionEnum.HORIZONTAL
                        ))
                        .build()

        );
    }

    private Component buildTableData(List<Object> data) {
        return Component.builder()
                .type(ComponentEnum.TABLE)
                .data(new TableData(
                        Arrays.asList(
                                new ColumnHeader("id", "ID"),
                                new ColumnHeader("name", "Name"),
                                new ColumnHeader("description", "Description"),
                                new ColumnHeader("status", "Status")
                        ),
                        data
                ))
                .build();
    }
}
