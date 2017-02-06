package myapp.service.mapper;

import myapp.domain.*;
import myapp.service.dto.ItemDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Item and its DTO ItemDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ItemMapper {

    ItemDTO itemToItemDTO(Item item);

    List<ItemDTO> itemsToItemDTOs(List<Item> items);

    Item itemDTOToItem(ItemDTO itemDTO);

    List<Item> itemDTOsToItems(List<ItemDTO> itemDTOs);
}
