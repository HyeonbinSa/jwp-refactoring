package kitchenpos.table.domain;

import java.time.LocalDateTime;

public class TableGroup {

    private Long id;
    private LocalDateTime createdDate;

    public TableGroup(Long id, LocalDateTime createdDate) {
        this.id = id;
        this.createdDate = createdDate;
    }

    public TableGroup(LocalDateTime createdDate) {
        this(null, createdDate);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}