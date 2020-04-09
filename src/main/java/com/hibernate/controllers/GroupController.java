package com.hibernate.controllers;

import com.hibernate.model.dto.GroupDto;
import com.hibernate.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> save(@RequestBody GroupDto groupDto) {
        GroupDto dto = groupService.save(groupDto);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> findById(@PathVariable Long id) {
        GroupDto groupDto = groupService.findById(id);
        return ResponseEntity.ok().body(groupDto);
    }

    @GetMapping(value = "/show", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GroupDto>> findAll() {
        List<GroupDto> groupDtoList = groupService.findAll();
        return ResponseEntity.ok().body(groupDtoList);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
        return ResponseEntity.ok().body("Group with id = " + id + " successfully deleted");
    }
}
