package com.emagia.ach.controller;

import com.emagia.ach.dto.EntryDetailDto;
import com.emagia.ach.entity.EntryDetailEntity;
import com.emagia.ach.exception.ResourceNotFoundException;
import com.emagia.ach.mapper.EntryDetailMapper;
import com.emagia.ach.service.EntryDetailService;
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

@RequestMapping("/entry-detail")
@RestController
@Slf4j
@Api("entry-detail")
public class EntryDetailController {
    private final EntryDetailService entryDetailService;

    public EntryDetailController(EntryDetailService entryDetailService) {
        this.entryDetailService = entryDetailService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated EntryDetailDto entryDetailDto) {
        entryDetailService.save(entryDetailDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntryDetailDto> findById(@PathVariable("id") Long id) {
        EntryDetailDto entryDetail = entryDetailService.findById(id);
        return ResponseEntity.ok(entryDetail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(entryDetailService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        entryDetailService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<EntryDetailDto>> pageQuery(EntryDetailDto entryDetailDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<EntryDetailDto> entryDetailPage = entryDetailService.findByCondition(entryDetailDto, pageable);
        return ResponseEntity.ok(entryDetailPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated EntryDetailDto entryDetailDto, @PathVariable("id") Long id) {
        entryDetailService.update(entryDetailDto, id);
        return ResponseEntity.ok().build();
    }
}
