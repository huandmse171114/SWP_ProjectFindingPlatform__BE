package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.payment.Payment;
import com.findhub.finhubbackend.entity.payment.PaymentStatus;
import com.findhub.finhubbackend.service.payment.PaymentService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PAYMENT)
public class PaymentController extends ApiController<Payment, PaymentService, PaymentStatus> {

    // @PostMapping(ApiPath.ENABLE)
    // public boolean enableEntity(@RequestBody int id) {
    // return service.changeStatus(id, PaymentStatus.);
    // }

    // @PostMapping(ApiPath.DISABLE)
    // public boolean disableEntity(@RequestBody int id) {
    // return service.changeStatus(id, PaymentStatus);
    // }
}
