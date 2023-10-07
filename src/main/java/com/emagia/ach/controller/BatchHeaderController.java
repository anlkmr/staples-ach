package com.emagia.ach.controller;

import com.emagia.ach.dto.BatchHeaderDto;
import com.emagia.ach.exception.ResourceNotFoundException;
import com.emagia.ach.service.BatchHeaderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/batch-header")
@RestController
@Slf4j
@Api("batch-header")
public class BatchHeaderController {
    private final BatchHeaderService batchHeaderService;

    public BatchHeaderController(BatchHeaderService batchHeaderService) {
        this.batchHeaderService = batchHeaderService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated BatchHeaderDto batchHeaderDto) {
        batchHeaderService.save(batchHeaderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchHeaderDto> findById(@PathVariable("id") Long id) {
        BatchHeaderDto batchHeader = batchHeaderService.findById(id);
        return ResponseEntity.ok(batchHeader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(batchHeaderService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        batchHeaderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<BatchHeaderDto>> pageQuery(BatchHeaderDto batchHeaderDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BatchHeaderDto> batchHeaderPage = batchHeaderService.findByCondition(batchHeaderDto, pageable);
        return ResponseEntity.ok(batchHeaderPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated BatchHeaderDto batchHeaderDto, @PathVariable("id") Long id) {
        batchHeaderService.update(batchHeaderDto, id);
        return ResponseEntity.ok().build();
    }
}
