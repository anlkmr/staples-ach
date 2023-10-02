
package com.emagia.ach.controller;

import com.emagia.ach.dto.BatchControlDto;
import com.emagia.ach.entity.BatchControlEntity;
import com.emagia.ach.exception.ResourceNotFoundException;
import com.emagia.ach.mapper.BatchControlMapper;
import com.emagia.ach.service.BatchControlService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/batch-control")
@RestController
@Slf4j
@Api("batch-control")
public class BatchControlController {
    private final BatchControlService batchControlService;

    public BatchControlController(BatchControlService batchControlService) {
        this.batchControlService = batchControlService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated BatchControlDto batchControlDto) {
        batchControlService.save(batchControlDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchControlDto> findById(@PathVariable("id") String id) {
        BatchControlDto batchControl = batchControlService.findById(Long.parseLong(id));
        return ResponseEntity.ok(batchControl);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        Optional.ofNullable(batchControlService.findById(Long.parseLong(id))).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        batchControlService.deleteById(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<BatchControlDto>> pageQuery(BatchControlDto batchControlDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BatchControlDto> batchControlPage = batchControlService.findByCondition(batchControlDto, pageable);
        return ResponseEntity.ok(batchControlPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated BatchControlDto batchControlDto, @PathVariable("id") String id) {
        batchControlService.update(batchControlDto, Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}

