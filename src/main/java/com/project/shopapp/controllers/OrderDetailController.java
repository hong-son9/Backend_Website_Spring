package com.project.shopapp.controllers;
import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order_details")
public class OrderDetailController {
    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO, BindingResult result){
        return ResponseEntity.ok("createOrderDetail ok");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@Valid @PathVariable("id")Long id){
        return ResponseEntity.ok("getOrderDetail" + id);
    }
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderDetails(@Valid @PathVariable("orderId")Long orderId){
            return ResponseEntity.ok("getOrderDetails with orderId = "+orderId);
    }   @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(
            @Valid @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO newOrderDetailData) {
        return ResponseEntity.ok("updateOrderDetail with id="+id
                +",newOrderDetailData: "+newOrderDetailData);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(
            @Valid @PathVariable("id") Long id) {
        return ResponseEntity.noContent().build();
    }
}
