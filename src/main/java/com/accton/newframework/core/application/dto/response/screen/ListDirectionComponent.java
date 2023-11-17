package com.accton.newframework.core.application.dto.response.screen;

import com.accton.newframework.core.application.dto.response.screen.type.ComponentEnum;
import com.accton.newframework.core.application.dto.response.screen.type.DirectionEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDirectionComponent extends Component {

   @JsonProperty("children")
   private List<Component> children;

   @JsonProperty("direction")
   private DirectionEnum direction;

   public ListDirectionComponent(List<Component> children, DirectionEnum direction) {
      this.children = children;
      this.direction = direction;
   }
   public ListDirectionComponent(ComponentEnum type, List<Component> children, DirectionEnum direction) {
      this.type =type;
      this.children = children;
      this.direction = direction;
   }
}
