package com.emagia.ach.controller;

import com.emagia.ach.dto.FileControlDto;
import com.emagia.ach.service.FileControlService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.emagia.ach.exception.ResourceNotFoundException;
import java.util.Optional;

@RequestMapping("/file-control")
@RestController
@Slf4j
@Api("file-control")
public class FileControlController {
    private final FileControlService fileControlService;

    public FileControlController(FileControlService fileControlService) {
        this.fileControlService = fileControlService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated FileControlDto fileControlDto) {
        fileControlService.save(fileControlDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileControlDto> findById(@PathVariable("id") Long id) {
        FileControlDto fileControl = fileControlService.findById(id);
        return ResponseEntity.ok(fileControl);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(fileControlService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        fileControlService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<FileControlDto>> pageQuery(FileControlDto fileControlDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<FileControlDto> fileControlPage = fileControlService.findByCondition(fileControlDto, pageable);
        return ResponseEntity.ok(fileControlPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated FileControlDto fileControlDto, @PathVariable("id") Long id) {
        fileControlService.update(fileControlDto, id);
        return ResponseEntity.ok().build();
    }
}
