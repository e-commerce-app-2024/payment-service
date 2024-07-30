package com.ecommerce.app.payload;

import java.util.List;

public record PageResponse<T>(
        List<T> list,
        boolean isLast,
        int index,
        int size,
        Long totalElements,
        int totalPage
) {
}
