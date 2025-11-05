package org.example.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.server.dto.HistoryRequestDTO;
import org.example.server.dto.HistoryResponseDTO;
import org.example.server.service.HistoryService;
import org.example.server.utill.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_URL + "/history")
@Tag(
        name = "History Controller",
        description = "History management APIs"
)
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @PostMapping
    @Operation(summary = "Create rental history")
    public ResponseEntity<HistoryResponseDTO> create(@RequestBody HistoryRequestDTO dto){
        return new ResponseEntity<>(historyService.createHistory(dto), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user history by user ID")
    public ResponseEntity<List<HistoryResponseDTO>> getUserHistory(@PathVariable UUID userId){
        return ResponseEntity.ok(historyService.getUserHistory(userId));
    }
}
