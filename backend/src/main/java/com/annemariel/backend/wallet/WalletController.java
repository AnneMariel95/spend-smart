package com.annemariel.backend.wallet;

import com.annemariel.backend.wallet.dto.WalletResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
