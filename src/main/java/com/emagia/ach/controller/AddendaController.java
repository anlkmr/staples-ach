package com.emagia.ach.controller;

import com.emagia.ach.dto.AddendaDto;
import com.emagia.ach.exception.ResourceNotFoundException;
import com.emagia.ach.service.AddendaService;
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

@RequestMapping("/api/addenda")
@RestController
@Slf4j
@Api("addenda")
public class AddendaController {
    private final AddendaService addendaService;

    public AddendaController(AddendaService addendaService) {
        this.addendaService = addendaService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated AddendaDto addendaDto) {
        addendaService.save(addendaDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddendaDto> findById(@PathVariable("id") long id) {
        AddendaDto addenda = addendaService.findById(id);
        return ResponseEntity.ok(addenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        Optional.ofNullable(addendaService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        addendaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<AddendaDto>> pageQuery(AddendaDto addendaDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AddendaDto> addendaPage = addendaService.findByCondition(addendaDto, pageable);
        return ResponseEntity.ok(addendaPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated AddendaDto addendaDto, @PathVariable("id") long id) {
        addendaService.update(addendaDto, id);
        return ResponseEntity.ok().build();
    }
}
