package org.blockchain.controller;

import org.blockchain.entity.User;
import org.blockchain.entity.VoucherEntry;
import org.blockchain.extra.enums.VoucherType;
import org.blockchain.extra.projection.UserMovementProjection;
import org.blockchain.repository.VoucherEntryRepository;
import org.blockchain.repository.VoucherRepository;
import org.blockchain.service.AuthorizationService;
import org.blockchain.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Ghaith Hahi <geath-hahi@hotmail.com>
 */

@RestController
@RequestMapping("/voucher")
public class VoucherController {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    VoucherEntryRepository voucherEntryRepository;

    @Autowired
    VoucherRepository voucherRepository;

    /**
     * Create new user in the system
     * @param voucherEntries containing all voucher entries
     * @param type detect type of accountant voucher
     * @return ResponseEntity<?>
     * @header authorization containing id of logged user
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody(required = true) List<VoucherEntry> voucherEntries,
                                    @RequestParam(required = true) VoucherType type){
        User user = authorizationService.getAuthorizationFromRequest();
        if (user == null){
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        switch (type){
            case PAYMENT_VOUCHER:
                voucherService.addPaymentVoucher(user, voucherEntries);
                break;
            case OPENING_BALANCE_VOUCHER:
                voucherService.addOpeningBalanceVoucher(voucherEntries);
                break;
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * list all transaction of logged user
     * @return ResponseEntity<?>
     * @header authorization containing id of logged user
     */
    @RequestMapping("user-movements")
    public ResponseEntity<?> userMovements(){
        User user = authorizationService.getAuthorizationFromRequest();
        if (user == null){
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }

        List<UserMovementProjection> entries = voucherEntryRepository.findByUser(user);

        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}
