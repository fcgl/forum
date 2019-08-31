package com.fcgl.madrid.dev;

import com.fcgl.madrid.forum.model.response.InternalStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/dev/")
public class DevController {

    DevService devService;

    @Autowired
    public void setPostService(DevService devService) {
        this.devService = devService;
    }

    @GetMapping(value="/fallback")
    public ResponseEntity<InternalStatus> failureWithFallback() {
        return devService.failureWithFallback();
    }
}
