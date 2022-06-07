package by.tolkach.mailScheduler.service.api;

import by.tolkach.mailScheduler.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mailScheduler.dto.Page;
import by.tolkach.mailScheduler.dto.SimplePageable;

import java.util.List;

public class Pagination<T,Z> {
    
    public static <V,Y> Pagination<V,Y> pageOf(Class<V> vClass, Class<Y> yClass) {
        return new Pagination<V,Y>();
    } 

    public Page<T> properties(List<Z> items, SimplePageable pageable,
                                       long totalElements, IEntityConverter<T,Z> converter) {
        Page<T> page = new Page<>();
        for (Z item: items) {
            page.add(converter.toDto(item));
        }
        int totalPages = (int) totalElements / pageable.getSize();
        return page.createPageProperty(pageable.getPage(), pageable.getSize(), (int) totalElements,
                (int) totalElements / pageable.getSize(), pageable.getPage() == 0, items.size(),
                pageable.getPage() == totalPages);
    }
}
