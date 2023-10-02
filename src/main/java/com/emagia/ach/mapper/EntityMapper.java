package com.emagia.ach.mapper;

import com.emagia.ach.entity.BatchControlEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

    Set<D> toDto(Set<E> entityList);
}
