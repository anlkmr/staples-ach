package com.emagia.ach.controller;

import com.emagia.ach.dto.FileHeaderDto;
import com.emagia.ach.exception.ResourceNotFoundException;
import com.emagia.ach.service.FileHeaderService;
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

@RequestMapping("/file-header")
@RestController
@Slf4j
@Api("file-header")
public class FileHeaderController {
    private final FileHeaderService fileHeaderService;

    public FileHeaderController(FileHeaderService fileHeaderService) {
        this.fileHeaderService = fileHeaderService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated FileHeaderDto fileHeaderDto) {
        fileHeaderService.save(fileHeaderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileHeaderDto> findById(@PathVariable("id") Long id) {
        FileHeaderDto fileHeader = fileHeaderService.findById(id);
        return ResponseEntity.ok(fileHeader);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(fileHeaderService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        fileHeaderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<FileHeaderDto>> pageQuery(FileHeaderDto fileHeaderDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FileHeaderDto> fileHeaderPage = fileHeaderService.findByCondition(fileHeaderDto, pageable);
        return ResponseEntity.ok(fileHeaderPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated FileHeaderDto fileHeaderDto, @PathVariable("id") Long id) {
        fileHeaderService.update(fileHeaderDto, id);
        return ResponseEntity.ok().build();
    }
}
