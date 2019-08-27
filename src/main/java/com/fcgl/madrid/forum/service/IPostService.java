package com.fcgl.madrid.forum.service;

import com.fcgl.madrid.forum.model.response.InternalStatus;
import com.fcgl.madrid.forum.model.request.PostRequest;
import org.springframework.http.ResponseEntity;

public interface IPostService {

    /**
     * Saves a forum post
     * @param postRequest Request made by client
     * @return ResponseEntity<InternalStatus>
     */
    public ResponseEntity<InternalStatus> post(PostRequest postRequest);
}
