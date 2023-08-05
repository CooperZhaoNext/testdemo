package org.example;

import com.nexttrucking.common2.authorization.AuthReview;
import com.nexttrucking.commonsecurity.resourceserver.NextSecurityContextUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vault")
public class TestResource {



    @GetMapping("/webhook")
    @AuthReview(reviewed = true, reviewDateIso8601 = "2023-02-29")

    public ResponseEntity test(){

//        NextSecurityContextUtil.checkPermissionAndThrow("asss");

        return ResponseEntity.ok(null);
    }

}
