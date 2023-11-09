package com.annemariel.backend.wallet;

import com.annemariel.backend.wallet.dto.WalletRequestDto;
import com.annemariel.backend.wallet.dto.WalletResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<WalletResponseDto>> findAllWallets() {
        return ResponseEntity.ok(walletService.findAllWallets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(walletService.findById(id));
    }

    @PostMapping
    public ResponseEntity<WalletResponseDto> createWallet(@RequestBody WalletRequestDto dto) {
        WalletResponseDto wallet = walletService.createWallet(dto);
        return ResponseEntity.created(URI.create("/api/wallets/" + wallet.id())).body(wallet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WalletResponseDto> updateWallet(@PathVariable String id, @RequestBody WalletRequestDto dto) {
        return ResponseEntity.ok(walletService.updateWallet(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWalletById(@PathVariable String id) {
        try {
            walletService.deleteWalletById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Cannot delete wallet with associated expenses");
        }
    }
}
