<<<<<<<< HEAD:second-server/src/main/java/com/hanhwa_tae/secondserver/cart/command/application/controller/CartCommandController.java
package com.hanhwa_tae.secondserver.cart.command.application.controller;


import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.secondserver.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.secondserver.cart.command.application.dto.response.CreateCartResponse;
import com.hanhwa_tae.secondserver.cart.command.application.service.CartCommandService;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "장바구니")
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartCommandController {
    private final CartCommandService cartCommandService;
    // 장바구니 품목 등록
    @Operation(summary = "장바구니 품목 등록", description = "회원은 장바구니에 품목을 등록할 수 있다.")
    @PostMapping
    public ResponseEntity<ApiResponse<CreateCartResponse>> createMyCart(
            @RequestBody @Validated CreateCartRequest createCartRequest,
            @AuthenticationPrincipal CustomUserDetail customUserDetail){

        String id = customUserDetail.getUserId();
        int cartId = cartCommandService.registerCart(id,createCartRequest);
        CreateCartResponse response = CreateCartResponse.
                builder().
                cartId(cartId).
                build();

        return ResponseEntity.status(HttpStatus.CREATED).
                body(ApiResponse.success(response));
    }

    // 장바구니 수량 수정
    @Operation(summary = "장바구니 품목 수정", description = "회원은 장바구니에 품목을 수정할 수 있다.")
    @PutMapping("/{cart_id}")
    public ResponseEntity<ApiResponse<Void>> updateMyCart(@PathVariable int cart_id,
                                                          @RequestBody UpdateCartRequest updateCartRequest,
                                                          @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        String id = userDetail.getUserId();
        cartCommandService.updateCart(id, cart_id, updateCartRequest);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 장바구니 내 특정 상품 삭제
    @Operation(summary = "장바구니 품목 삭제", description = "회원은 장바구니에 품목을 삭제할 수 있다.")
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<ApiResponse<Void>> deleteMyCart(@PathVariable int cart_id,
                                                          @AuthenticationPrincipal CustomUserDetail userDetail){
        String id = userDetail.getUserId();
        cartCommandService.deleteCart(id, cart_id);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
    // 장바구니 모든 품목 삭제
    @Operation(summary = "장바구니 품목 전체 삭제", description = "회원은 장바구니에 품목을 전체 삭제할 수 있다.")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllMyCart(@AuthenticationPrincipal CustomUserDetail userDetail){
        String id = userDetail.getUserId();
        cartCommandService.deleteAllCart(id);
        return ResponseEntity.ok(ApiResponse.success(null));

    }
}
========
package com.hanhwa_tae.firstserver.cart.command.application.controller;


import com.hanhwa_tae.firstserver.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.firstserver.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.firstserver.cart.command.application.dto.response.CreateCartResponse;
import com.hanhwa_tae.firstserver.cart.command.application.service.CartCommandService;
import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "장바구니")
@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartCommandController {
    private final CartCommandService cartCommandService;
    // 장바구니 품목 등록
    @Operation(summary = "장바구니 품목 등록", description = "회원은 장바구니에 품목을 등록할 수 있다.")
    @PostMapping
    public ResponseEntity<ApiResponse<CreateCartResponse>> createMyCart(
            @RequestBody @Validated CreateCartRequest createCartRequest,
            @AuthenticationPrincipal CustomUserDetail customUserDetail){

        String id = customUserDetail.getUserId();
        String message = cartCommandService.registerCart(id,createCartRequest);
        CreateCartResponse response = CreateCartResponse.
                builder().
                message(message).
                build();

        if(message.contains("수정")){
            return ResponseEntity.status(HttpStatus.OK).
                    body(ApiResponse.success(response));
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ApiResponse.success(response));
        }

    }

    // 장바구니 수량 수정
    @Operation(summary = "장바구니 품목 수정", description = "회원은 장바구니에 품목을 수정할 수 있다.")
    @PutMapping("/{cart_id}")
    public ResponseEntity<ApiResponse<Void>> updateMyCart(@PathVariable int cart_id,
                                                          @RequestBody UpdateCartRequest updateCartRequest,
                                                          @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        String id = userDetail.getUserId();
        cartCommandService.updateCart(id, cart_id, updateCartRequest);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 장바구니 내 특정 상품 삭제
    @Operation(summary = "장바구니 품목 삭제", description = "회원은 장바구니에 품목을 삭제할 수 있다.")
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<ApiResponse<Void>> deleteMyCart(@PathVariable int cart_id,
                                                          @AuthenticationPrincipal CustomUserDetail userDetail){
        String id = userDetail.getUserId();
        cartCommandService.deleteCart(id, cart_id);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
    // 장바구니 모든 품목 삭제
    @Operation(summary = "장바구니 품목 전체 삭제", description = "회원은 장바구니에 품목을 전체 삭제할 수 있다.")
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllMyCart(@AuthenticationPrincipal CustomUserDetail userDetail){
        String id = userDetail.getUserId();
        cartCommandService.deleteAllCart(id);
        return ResponseEntity.ok(ApiResponse.success(null));

    }
}
>>>>>>>> 8e17fe0a18dc3b229c6cd61df0626e9b66d53c6e:first-server/src/main/java/com/hanhwa_tae/firstserver/cart/command/application/controller/CartCommandController.java
