package myapp.service.impl;

import myapp.service.ItemService;
import myapp.domain.Item;
import myapp.repository.ItemRepository;
import myapp.service.dto.ItemDTO;
import myapp.service.mapper.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Item.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    private final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);
    
    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    /**
     * Save a item.
     *
     * @param itemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        log.debug("Request to save Item : {}", itemDTO);
        Item item = itemMapper.itemDTOToItem(itemDTO);
        item = itemRepository.save(item);
        ItemDTO result = itemMapper.itemToItemDTO(item);
        return result;
    }

    /**
     *  Get all the items.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ItemDTO> findAll() {
        log.debug("Request to get all Items");
        List<ItemDTO> result = itemRepository.findAll().stream()
            .map(itemMapper::itemToItemDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one item by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ItemDTO findOne(Long id) {
        log.debug("Request to get Item : {}", id);
        Item item = itemRepository.findOne(id);
        ItemDTO itemDTO = itemMapper.itemToItemDTO(item);
        return itemDTO;
    }

    /**
     *  Delete the  item by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Item : {}", id);
        itemRepository.delete(id);
    }
}
