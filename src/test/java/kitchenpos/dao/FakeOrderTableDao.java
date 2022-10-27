package kitchenpos.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kitchenpos.domain.OrderTable;

public class FakeOrderTableDao implements OrderTableDao {

    private final List<OrderTable> IN_MEMORY_ORDER_TABLE;

    public FakeOrderTableDao() {
        IN_MEMORY_ORDER_TABLE = new ArrayList<>();
    }

    @Override
    public OrderTable save(OrderTable entity) {
        IN_MEMORY_ORDER_TABLE.add(entity);
        Long id = (long) IN_MEMORY_ORDER_TABLE.size();
        entity.setId(id);
        return entity;
    }

    @Override
    public Optional<OrderTable> findById(Long id) {
        return IN_MEMORY_ORDER_TABLE.stream()
                .filter(orderTable -> orderTable.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<OrderTable> findAll() {
        return new ArrayList<>(IN_MEMORY_ORDER_TABLE);
    }

    @Override
    public List<OrderTable> findAllByIdIn(List<Long> ids) {
        return IN_MEMORY_ORDER_TABLE.stream()
                .filter(orderTable -> ids.contains(orderTable.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderTable> findAllByTableGroupId(Long tableGroupId) {
        return IN_MEMORY_ORDER_TABLE.stream()
                .filter(orderTable -> orderTable.getTableGroupId().equals(tableGroupId))
                .collect(Collectors.toList());
    }
}